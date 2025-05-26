package com.dodamsoft.fivembti.repository;

import com.dodamsoft.fivembti.entity.Question;
import com.dodamsoft.fivembti.util.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDimension(Dimension dimension);
}
