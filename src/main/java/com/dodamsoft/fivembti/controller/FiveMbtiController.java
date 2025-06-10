package com.dodamsoft.fivembti.controller;

import com.dodamsoft.fivembti.entity.MBTIResults;
import com.dodamsoft.fivembti.service.FiveMbtiService;
import com.dodamsoft.fivembti.util.Dimension;
import com.dodamsoft.fivembti.util.MbtiTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FiveMbtiController {

    private final FiveMbtiService fiveMbtiService;

    @GetMapping("/question/{dimension}")
    public ResponseEntity<String> getQuestionByDimension(@PathVariable("dimension") Dimension dimension) {
        String question = fiveMbtiService.getQuestionByDimension(dimension);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/results/{type}")
    public ResponseEntity<MBTIResults> getResultsByType(@PathVariable("type") String type) {
        MbtiTypeEnum mbtiTypeEnum = MbtiTypeEnum.valueOf(type.toUpperCase());
        MBTIResults result = fiveMbtiService.getMBTIResultByType(mbtiTypeEnum);
        return ResponseEntity.ok(result);
    }

}
