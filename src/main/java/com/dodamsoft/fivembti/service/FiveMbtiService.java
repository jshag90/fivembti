package com.dodamsoft.fivembti.service;

import com.dodamsoft.fivembti.entity.Question;
import com.dodamsoft.fivembti.repository.QuestionRepository;
import com.dodamsoft.fivembti.util.Dimension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class FiveMbtiService {

    private final QuestionRepository questionRepository;
    public String getQuestionByDimension(Dimension dimension) {
        List<Question> questionList = questionRepository.findByDimension(dimension);
        int randomIndex = ThreadLocalRandom.current().nextInt(questionList.size());
        return questionList.get(randomIndex).getContent();
    }
}
