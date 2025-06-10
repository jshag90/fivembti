package com.dodamsoft.fivembti.service;

import com.dodamsoft.fivembti.entity.MBTIResults;
import com.dodamsoft.fivembti.entity.Question;
import com.dodamsoft.fivembti.repository.MBTIResultsRepository;
import com.dodamsoft.fivembti.repository.QuestionRepository;
import com.dodamsoft.fivembti.util.Dimension;
import com.dodamsoft.fivembti.util.MbtiTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class FiveMbtiService {

    private final QuestionRepository questionRepository;
    private final MBTIResultsRepository mbtiResultsRepository;

    public String getQuestionByDimension(Dimension dimension) {
        List<Question> questionList = questionRepository.findByDimension(dimension);
        int randomIndex = ThreadLocalRandom.current().nextInt(questionList.size());
        return questionList.get(randomIndex).getContent();
    }

    public MBTIResults getMBTIResultByType(MbtiTypeEnum type){
        Optional<MBTIResults> byTypeEnum = mbtiResultsRepository.findByTypeEnum(type);
        if(!byTypeEnum.isPresent()) {
            log.info("해당 mbti 유형에 결과가 없음 : {}", type.name());
            return mbtiResultsRepository.findAll().get(0);
        }
        return byTypeEnum.get();
    }

}
