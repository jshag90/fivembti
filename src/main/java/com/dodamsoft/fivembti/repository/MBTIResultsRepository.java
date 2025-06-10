package com.dodamsoft.fivembti.repository;

import com.dodamsoft.fivembti.entity.MBTIResults;
import com.dodamsoft.fivembti.entity.Question;
import com.dodamsoft.fivembti.util.Dimension;
import com.dodamsoft.fivembti.util.MbtiTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MBTIResultsRepository extends JpaRepository<MBTIResults, Long> {
    Optional<MBTIResults> findByTypeEnum(MbtiTypeEnum typeEnum);
}
