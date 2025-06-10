package com.dodamsoft.fivembti.entity;

import com.dodamsoft.fivembti.util.MbtiTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mbti_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MBTIResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti_type")
    private MbtiTypeEnum typeEnum;

    private String typeNickName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    //한국 연예인
    private String similarCelebrities;

    // 전 세계 연예인, 운동선수 등 대중적으로 잘 알려진 현대 유명인
    private String famousCelebrities;

    // 역사적 인물, 철학자, 정치인, 과학자 등 시대를 초월한 영향력 있는 인물
    private String historicalFigures;


}
