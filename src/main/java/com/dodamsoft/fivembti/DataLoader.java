package com.dodamsoft.fivembti;

import com.dodamsoft.fivembti.entity.MBTIResults;
import com.dodamsoft.fivembti.entity.Question;
import com.dodamsoft.fivembti.repository.MBTIResultsRepository;
import com.dodamsoft.fivembti.repository.QuestionRepository;
import com.dodamsoft.fivembti.util.Dimension;
import com.dodamsoft.fivembti.util.MbtiTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final QuestionRepository questionRepository;
    private final MBTIResultsRepository mbtiResultsRepository;

    @Override
    public void run(String... args) {
        initQuestions();
        initMBTIResults();
    }

    private void initQuestions() {
        if (questionRepository.count() > 0)
            return;

        // Define question templates for each dimension
        List<String> eiQuestions = generateEIQuestions();
        System.out.println(eiQuestions.size());
        List<String> nsQuestions = generateNSQuestions();
        System.out.println(nsQuestions.size());
        List<String> ftQuestions = generateFTQuestions();
        System.out.println(ftQuestions.size());
        List<String> pjQuestions = generatePJQuestions();
        System.out.println(pjQuestions.size());

        // Save questions for each dimension
        for (int i = 0; i < eiQuestions.size(); i++) {
            questionRepository.save(Question.builder()
                    .content(eiQuestions.get(i))
                    .dimension(Dimension.EI)
                    .build());

            questionRepository.save(Question.builder()
                    .content(nsQuestions.get(i))
                    .dimension(Dimension.NS)
                    .build());

            questionRepository.save(Question.builder()
                    .content(ftQuestions.get(i))
                    .dimension(Dimension.FT)
                    .build());

            questionRepository.save(Question.builder()
                    .content(pjQuestions.get(i))
                    .dimension(Dimension.PJ)
                    .build());
        }

        System.out.println("✅ 질문 400개 자동 생성 완료");
    }

    private void initMBTIResults() {
        if (mbtiResultsRepository.count() > 0)
            return;

        List<MBTIResults> mbtiResultsList = new ArrayList<>();

        for (MbtiTypeEnum mbtiTypeEnum : MbtiTypeEnum.values()) {
            switch (mbtiTypeEnum) {
                case ISTJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ISTJ)
                        .typeNickName("청렴결백한 논리주의자")
                        .description("신뢰할 수 있고 책임감이 강하며, 전통과 질서를 중시하는 현실주의자입니다.")
                        .similarCelebrities("아이린, 성훈, 쯔위, 김성규, 시우민")
                        .famousCelebrities("톰 행크스, 앤절리나 졸리, 톰 브래디, 엠마 왓슨, 제프 베조스")
                        .historicalFigures("조지 워싱턴, 에이브러햄 링컨, 나폴레옹, 베토벤, 뉴턴")
                        .build());
                case ISFJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ISFJ)
                        .typeNickName("용감한 수호자")
                        .description("성실하고 인내심이 강하며, 타인을 도우려는 의지가 강한 헌신적인 사람입니다.")
                        .similarCelebrities("비욘세, 나재민, 스티븐 연, 조던 피터슨, 메그 비셔스")
                        .famousCelebrities("오프라 윈프리, 윌 스미스, 비욘세, 레오나르도 디카프리오, 샤론 스톤")
                        .historicalFigures("마더 테레사, 간디, 플라톤, 셰익스피어, 링컨")
                        .build());
                case INFJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.INFJ)
                        .typeNickName("통찰력 있는 조언자")
                        .description("직관적이고 깊이 있는 사고를 지닌 이상주의자로, 타인의 감정에 민감합니다.")
                        .similarCelebrities("김이나, 남주혁, 태양, 차은우, 태연")
                        .famousCelebrities("오프라 윈프리, 엘론 머스크, 앨버트 아인슈타인, 말랄라 유사프자이, 빌 게이츠")
                        .historicalFigures("요한 세바스티안 바흐, 마틴 루터 킹 주니어, 간디, 프로이트, 칸트")
                        .build());
                case INTJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.INTJ)
                        .typeNickName("전략적인 계획가")
                        .description("분석적이고 전략적인 사고를 바탕으로 목표를 향해 나아가는 리더형 인물입니다.")
                        .similarCelebrities("강동원, 윤하, 손나은, 이경규, 미노이")
                        .famousCelebrities("스티브 잡스, 일론 머스크, 마크 저커버그, 힐러리 클린턴, 오프라 윈프리")
                        .historicalFigures("레오나르도 다 빈치, 아이작 뉴턴, 찰스 다윈, 마키아벨리, 토마스 제퍼슨")
                        .build());
                case ISTP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ISTP)
                        .typeNickName("만능 재주꾼")
                        .description("논리적이며 분석적인 사고를 가진 실용적인 문제 해결사입니다.")
                        .similarCelebrities("박명수, 슈가, 은지원, 주우재, 홍진경")
                        .famousCelebrities("클린트 이스트우드, 해리슨 포드, 마이클 조던, 드웨인 존슨, 톰 크루즈")
                        .historicalFigures("벤저민 프랭클린, 알렉산더 그레이엄 벨, 나폴레옹, 윈스턴 처칠, 갈릴레이")
                        .build());
                case ISFP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ISFP)
                        .typeNickName("호기심 많은 예술가")
                        .description("감성적이고 겸손하며, 조용한 환경에서 자신만의 방식으로 세상을 즐깁니다.")
                        .similarCelebrities("유재석, 정국, 주현영, 홍은채, 권정열")
                        .famousCelebrities("마릴린 먼로, 프레디 머큐리, 비욘세, 크리스티나 아길레라, 레이디 가가")
                        .historicalFigures("빈센트 반 고흐, 모차르트, 에디트 피아프, 클레오파트라, 프리다 칼로")
                        .build());
                case INFP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.INFP)
                        .typeNickName("열정적인 중재자")
                        .description("자신의 신념에 충실하고, 이상을 추구하는 감성적이며 내성적인 성격입니다.")
                        .similarCelebrities("아이유, 선미, 존박, 조이, 예리")
                        .famousCelebrities("윌리엄 셰익스피어, 조니 뎁, 조앤 롤링, 프레드릭 니체, 버락 오바마")
                        .historicalFigures("예수 그리스도, 간디, 레프 톨스토이, 에밀리 디킨슨, 카를 융")
                        .build());
                case INTP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.INTP)
                        .typeNickName("논리적인 사색가")
                        .description("호기심이 많고 혁신적인 사고를 가진 분석가이며, 지적 탐구를 즐깁니다.")
                        .similarCelebrities("유재석, 김종국, 고아성, 권나라, 서인국")
                        .famousCelebrities("빌 게이츠, 스티브 잡스, 알베르트 아인슈타인, 칼 세이건, 리처드 파인만")
                        .historicalFigures("소크라테스, 데카르트, 아리스토텔레스, 뉴턴, 갈릴레오")
                        .build());
                case ESTP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ESTP)
                        .typeNickName("모험을 즐기는 사업가")
                        .description("에너지 넘치고 현실적인 감각이 뛰어나며, 즉각적인 행동을 선호합니다.")
                        .similarCelebrities("김종국, 이효리, 박재범, 제시, 현아")
                        .famousCelebrities("도널드 트럼프, 드웨인 존슨, 샤를리즈 테론, 비욘세, 셀레나 고메즈")
                        .historicalFigures("줄리어스 시저, 알렉산더 대왕, 나폴레옹, 카이사르, 클레오파트라")
                        .build());
                case ESFP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ESFP)
                        .typeNickName("자유로운 연예인")
                        .description("사교적이고 유쾌하며, 타인과 함께하는 활동을 즐깁니다.")
                        .similarCelebrities("박보영, 장원영, 유아, 산다라박, 하니")
                        .famousCelebrities("마일리 사이러스, 리한나, 저스틴 비버, 케이티 페리, 브루노 마스")
                        .historicalFigures("찰리 채플린, 아멜리아 이어하트, 마리 퀴리, 헬렌 켈러, 안네 프랑크")
                        .build());
                case ENFP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ENFP)
                        .typeNickName("재기발랄한 활동가")
                        .description("창의적이고 열정적이며, 새로운 아이디어와 가능성에 매료되는 낙천주의자입니다.")
                        .similarCelebrities("박보검, 수지, 박나래, 유노윤호, 김세정")
                        .famousCelebrities("로빈 윌리엄스, 짐 캐리, 엘렌 드제너러스, 오프라 윈프리, 윌 스미스")
                        .historicalFigures("벤자민 프랭클린, 헨리 데이비드 소로우, 마야 안젤루, 갈릴레이, 니체")
                        .build());
                case ENTP -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ENTP)
                        .typeNickName("뜨거운 논쟁을 즐기는 변론가")
                        .description("재치 있고 분석적인 성격으로, 도전과 논쟁을 즐기며 창의적인 해결책을 제시합니다.")
                        .similarCelebrities("김구라, 박명수, 장도연, 유병재, 김희철")
                        .famousCelebrities("마크 트웨인, 벤자민 프랭클린, 토머스 에디슨, 스티브 잡스, 엘론 머스크")
                        .historicalFigures("소크라테스, 플라톤, 아리스토텔레스, 갈릴레이, 뉴턴")
                        .build());
                case ESTJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ESTJ)
                        .typeNickName("엄격한 관리자")
                        .description("현실적이고 체계적인 사고를 지니며, 조직 내 질서를 유지하는 데 능숙합니다.")
                        .similarCelebrities("차승원, 김희애, 박진영, 김성주, 이경규")
                        .famousCelebrities("조지 워싱턴, 마거릿 대처, 앙겔라 메르켈, 힐러리 클린턴, 잭 웰치")
                        .historicalFigures("나폴레옹, 윈스턴 처칠, 아브라함 링컨, 세종대왕, 칭기즈 칸")
                        .build());
                case ESFJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ESFJ)
                        .typeNickName("사교적인 외교관")
                        .description("다정하고 친절하며, 타인을 도우려는 성향이 강한 협력적인 인물입니다.")
                        .similarCelebrities("정해인, 수영, 이승기, 박서준, 소이현")
                        .famousCelebrities("테일러 스위프트, 앤 해서웨이, 휴 그랜트, 제니퍼 가너, 윌 스미스")
                        .historicalFigures("마더 테레사, 엘리자베스 여왕, 알버트 슈바이처, 플로렌스 나이팅게일, 넬슨 만델라")
                        .build());
                case ENFJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ENFJ)
                        .typeNickName("정의로운 사회운동가")
                        .description("타인을 이끄는 강한 리더십과 공감 능력을 지닌 열정적인 이상주의자입니다.")
                        .similarCelebrities("이효리, 김연아, 차은우, 송혜교, 하정우")
                        .famousCelebrities("버락 오바마, 엠마 왓슨, 윌 스미스, 마틴 루터 킹, 넬슨 만델라")
                        .historicalFigures("간디, 플라톤, 칸트, 루소, 세종대왕")
                        .build());
                case ENTJ -> mbtiResultsList.add(MBTIResults.builder()
                        .typeEnum(MbtiTypeEnum.ENTJ)
                        .typeNickName("대담한 통솔자")
                        .description("결단력 있고 통솔력 있는 지도자형 인물로, 전략적으로 목표를 달성합니다.")
                        .similarCelebrities("정우성, 김혜수, 손석희, 박찬호, 한가인")
                        .famousCelebrities("고든 램지, 스티브 잡스, 마거릿 대처, 프랭클린 루즈벨트, 베토벤")
                        .historicalFigures("줄리어스 시저, 알렉산더 대왕, 아이작 뉴턴, 칭기즈 칸, 에이브러햄 링컨")
                        .build());
            }
        }

        mbtiResultsRepository.saveAll(mbtiResultsList);

    }


    private List<String> generateEIQuestions() {
        List<String> questions = new ArrayList<>();
        String[] templates = {
                "사람들과 어울리는 것을 즐기시나요?",
                "모임에서 먼저 말을 거는 편인가요?",
                "여러 사람과 함께 있는 것이 편하다고 느끼시나요?",
                "새로운 사람을 만나는 것이 기대되시나요?",
                "소규모보다는 큰 모임이 더 좋으신가요?",
                "대화를 통해 에너지를 얻는 편인가요?",
                "혼자 있는 것보다 사람들과 있을 때 더 활력이 느껴지시나요?",
                "사람들이 많은 곳에서 쉽게 적응하시나요?",
                "친구들과 자주 연락하고 만나시나요?",
                "단체 활동에 적극적으로 참여하시나요?",
                "낯선 사람과 대화하는 것이 어렵지 않으신가요?",
                "사교적인 모임에서 주로 말하는 편이신가요?",
                "함께 일하는 것이 더 즐겁다고 느끼시나요?",
                "혼자 있는 시간보다 사람들과 함께 있는 시간이 좋으신가요?",
                "활동적인 모임에 자주 참석하시나요?",
                "팀으로 일할 때 더 능률이 오르시나요?",
                "낯선 환경에서도 쉽게 친구를 사귀시나요?",
                "조용한 공간보다 활기찬 공간에서 더 잘 쉬시나요?",
                "사람들과 상호작용할 때 에너지가 생기시나요?",
                "혼자보다 함께 일할 때 더 효율적이라고 느끼시나요?",
                "여러 사람 앞에서 발표하는 것이 익숙하신가요?",
                "혼자보다는 사람들과 함께 있을 때 더 활력을 느끼시나요?",
                "대화 중에 주도적으로 이야기하는 편인가요?",
                "혼자보다는 친구들과 활동하는 시간이 더 즐거우신가요?",
                "새로운 모임에서도 쉽게 어울리시나요?",
                "사람들이 많은 환경에서 편안함을 느끼시나요?",
                "친구들과 외출하는 것을 자주 즐기시나요?",
                "혼자 있는 시간이 길면 답답함을 느끼시나요?",
                "사교적인 상황이 편안하게 느껴지시나요?",
                "여럿이 함께 일할 때 더 능률이 오르시나요?",
                "다른 사람들과 의견을 나누는 것이 즐거우신가요?",
                "혼자 있는 것보다 사람들과 있는 것이 좋으신가요?",
                "모임에서 대화에 적극적으로 참여하시나요?",
                "활동적인 환경에서 더 집중이 잘 되시나요?",
                "사람들과 함께 시간을 보내는 것이 즐겁다고 느끼시나요?",
                "혼자보다는 함께 있을 때 아이디어가 더 잘 떠오르시나요?",
                "파티나 이벤트에 참여하는 것을 좋아하시나요?",
                "사람들과 함께 일할 때 더 집중이 잘 되시나요?",
                "다른 사람들과 있을 때 활력이 느껴지시나요?",
                "사람들과 함께할 때 창의력이 더 발휘되시나요?",
                "협업할 때 더 재미를 느끼시나요?",
                "혼자보다는 다른 사람들과 함께 있을 때 더 자유롭게 느껴지시나요?",
                "사교적인 모임에서 중심이 되는 경우가 많으신가요?",
                "조용한 환경보다 활기찬 곳이 더 생산적이라고 느끼시나요?",
                "친구들과 자주 만나 대화하는 것을 좋아하시나요?",
                "사람들과 함께 있을 때 휴식을 더 잘 느끼시나요?",
                "낯선 사람들과 쉽게 친해지시나요?",
                "사람들과 어울릴 때 내면이 더 열리는 느낌이 드시나요?",
                "단체 활동에서 리더 역할을 자주 맡으시나요?",
                "사람들과 함께 있을 때 더 창의적인 생각이 떠오르시나요?",
                "사람들과 모일 때 아이디어가 잘 떠오르시나요?",
                "사람들과 있을 때 명상이나 사색보다 활동을 선호하시나요?",
                "모임에서 공감대를 쉽게 형성하시는 편인가요?",
                "함께 계획 세우는 것을 더 선호하시나요?",
                "사교적인 환경에서 에너지가 넘치시나요?",
                "사람들과 함께 있을 때 더 편안하게 느끼시나요?",
                "대화 중 활력을 얻는 편인가요?",
                "사람들과 함께 일하는 것이 더 즐거우신가요?",
                "행사나 모임에 자주 참여하시나요?",
                "사람들과 함께 있는 시간이 소중하다고 느끼시나요?",
                "활동적인 환경에서 즐거움을 느끼시나요?",
                "다른 사람들과 함께할 때 집중이 더 잘 되시나요?",
                "모임에서 의견을 내는 것이 자연스럽게 느껴지시나요?",
                "사람들과 함께할 때 자신을 돌아보는 계기가 많으신가요?",
                "여럿이 함께 있을 때 자신감이 생기시나요?",
                "사람들과 함께일 때 창의력이 높아진다고 느끼시나요?",
                "친구들과 보내는 시간이 활력소라고 느끼시나요?",
                "사람들과 함께할 때 더 좋은 아이디어가 나오시나요?",
                "사교적인 자리에서 주목받는 것을 편하게 느끼시나요?",
                "여럿이 함께하는 시간이 평화를 준다고 느끼시나요?",
                "사람들과 교류할 때 삶의 의미를 느끼시나요?",
                "사람들과 함께할 때 더 생산적이라고 느끼시나요?",
                "모임에서 분위기를 주도하는 경우가 많으신가요?",
                "사람들과 몰입해서 작업하는 것을 선호하시나요?",
                "사람들과의 만남이 에너지의 원천이라고 생각하시나요?",
                "사람들과 함께 있을 때 스트레스가 해소되시나요?",
                "여럿이 함께하는 활동이 더 재미있다고 느끼시나요?",
                "사람들과 함께할 때 안정감을 느끼시나요?",
                "사교적인 모임에서 쉽게 적응하시나요?",
                "함께 있을 때 생각이 더 정리되시나요?",
                "여럿이 함께 있을 때 활발해지시나요?",
                "활동적인 환경에서 창의성이 더 발휘되시나요?",
                "친구들과 보내는 시간을 자주 가지시나요?",
                "사람들과 함께할 때 더 집중력이 생기시나요?",
                "대화 속에서 새로운 아이디어를 떠올리는 편인가요?",
                "사람들과 함께 있을 때 편안함을 느끼시나요?",
                "모임에서 자연스럽게 참여하시나요?",
                "함께 있을 때 더 만족감을 느끼시나요?",
                "사람들과 함께할 때 행복감을 더 느끼시나요?",
                "사람들과 있는 시간 동안 재충전이 되시나요?",
                "사람들과의 상호작용이 즐거우신가요?",
                "여럿이 있을 때 더 자유롭다고 느끼시나요?",
                "사교적인 환경에서 활기차게 행동하시나요?",
                "사람들과 함께할 때 성취감을 느끼시나요?",
                "친구들과의 모임이 삶의 활력소라고 생각하시나요?",
                "사람들과 함께할 때 창의력이 더 자극되시나요?",
                "사람들과 함께할 때 에너지가 넘치시나요?",
                "활동적인 환경이 마음을 편안하게 해주시나요?",
                "사람들과의 교류를 통해 성장한다고 느끼시나요?",
                "사람들과 함께 있을 때 평온함을 느끼시나요?"
        };
        questions.addAll(Arrays.asList(templates));
        return questions;
    }

    //TODO 질문 100가지 작성 수정 필요
    private List<String> generateNSQuestions() {
        List<String> questions = new ArrayList<>();
        String[] templates = {
                "현실적인 정보에 주의를 기울이는 편인가요?",
                "현재의 사실에 집중하는 것이 더 편한가요?",
                "세부사항을 꼼꼼하게 살피는 편인가요?",
                "구체적인 지침을 따르는 것이 편한가요?",
                "실제 경험을 통해 배우는 것을 선호하나요?",
                "아이디어보다는 실질적인 예시에 더 끌리나요?",
                "추상적인 개념보다는 구체적인 데이터가 더 이해되나요?",
                "과거 경험에 근거해 판단을 내리는 편인가요?",
                "상상보다는 눈에 보이는 사실이 중요하다고 느끼나요?",
                "미래보다는 현재에 집중하는 편인가요?",
                "실제로 확인된 정보에 더 신뢰가 가나요?",
                "계획보다는 지금 당장의 실행이 중요하다고 생각하나요?",
                "기존 방식이나 관습을 따르는 것을 선호하나요?",
                "문제를 해결할 때 구체적인 자료를 먼저 찾나요?",
                "복잡한 이론보다는 실용적인 방법이 더 낫다고 느끼나요?",
                "아이디어보다 실천이 더 중요하다고 생각하나요?",
                "큰 그림보다는 세부 사항이 더 중요하다고 느끼나요?",
                "경험을 통해 축적된 지식이 더 신뢰된다고 생각하나요?",
                "새로운 가능성보다는 현재의 문제에 집중하나요?",
                "현실적인 결과를 중요하게 생각하나요?",
                "정확한 사실에 기반한 설명을 선호하나요?",
                "직관보다는 실제 수치를 중요하게 생각하나요?",
                "상상보다 실제 적용 가능한 정보를 선호하나요?",
                "이론보다는 실무적인 예가 더 유익하다고 느끼나요?",
                "실제 사례나 경험을 듣는 것을 더 좋아하나요?",
                "아이디어보다는 눈에 보이는 성과가 중요하다고 생각하나요?",
                "실질적인 문제 해결에 집중하는 편인가요?",
                "새로운 개념보다는 익숙한 방법이 편한가요?",
                "현실적인 목표 설정을 선호하나요?",
                "정확하고 측정 가능한 정보를 중요하게 여기나요?"
        };
        questions.addAll(Arrays.asList(templates));
        return questions;
    }


    private List<String> generateFTQuestions() {
        List<String> questions = new ArrayList<>();
        String[] templates = {
                "결정을 내릴 때 감정을 우선시하나요?",
                "타인의 감정을 고려해 행동하나요?",
                "공감하는 능력이 뛰어나다고 느끼나요?",
                "사람들의 감정에 민감하게 반응하나요?",
                "상대방의 기분을 중요하게 생각하나요?",
                "논리보다 사람 간의 조화를 더 중시하나요?",
                "감정적인 상황에서도 타인을 배려하나요?",
                "타인의 아픔에 공감하는 편인가요?",
                "결정 시 다른 사람의 입장을 먼저 생각하나요?",
                "논쟁에서 감정보다 관계 유지를 더 신경 쓰나요?",
                "감정적인 대화에서 편안함을 느끼나요?",
                "감정에 따라 말투나 행동을 조절하나요?",
                "누군가의 감정을 상하게 했을 때 쉽게 신경 쓰이나요?",
                "공정함보다는 따뜻함이 더 중요하다고 느끼나요?",
                "문제를 해결할 때 인간관계를 우선시하나요?",
                "타인의 감정에 쉽게 공감하나요?",
                "자신의 가치관이나 신념에 따라 결정을 내리나요?",
                "감정적으로 힘들어하는 사람을 그냥 지나치기 어렵나요?",
                "갈등을 피하기 위해 감정을 숨기는 편인가요?",
                "결정을 내릴 때 상대방이 상처받을까 걱정하나요?",
                "감정적인 이야기나 영화에 쉽게 울컥하나요?",
                "상대의 감정에 영향을 받아 기분이 변하나요?",
                "논리보다는 사람의 마음을 얻는 것이 더 중요하다고 느끼나요?",
                "감정을 표현하는 것이 중요하다고 생각하나요?",
                "사람들의 행복이나 만족이 의사결정에 영향을 주나요?",
                "갈등 상황에서 감정 조율을 먼저 생각하나요?",
                "타인의 감정에 민감하게 반응하는 편인가요?",
                "의사결정에서 감정이 배제되면 부자연스럽게 느끼나요?",
                "누군가의 고통이나 감정 상태를 빨리 알아차리나요?",
                "감정적으로 다가오는 말에 더 설득되나요?",
                "감정에 따라 결정을 바꾸는 경우가 많나요?",
                "논리보다는 따뜻한 말 한마디가 더 중요하다고 느끼나요?",
                "갈등 중에도 상대방 기분을 상하게 하지 않으려 하나요?",
                "타인을 배려하지 않는 말은 쉽게 받아들이지 못하나요?",
                "감정 표현이 솔직한 사람을 더 신뢰하나요?",
                "타인의 감정을 무시하는 행동이 불편하게 느껴지나요?",
                "감정이 격한 상황에서도 다른 사람을 먼저 생각하나요?",
                "감정적인 위로가 논리적인 해결책보다 더 중요하다고 느끼나요?",
                "타인의 감정에 따라 자신의 행동을 조정하나요?",
                "감정적인 공감을 통해 관계가 깊어진다고 생각하나요?",
                "사람들과 조화를 이루는 것이 논리적 일관성보다 중요하다고 느끼나요?",
                "갈등을 피하기 위해 감정을 우선적으로 고려하나요?",
                "타인의 감정 표현에 쉽게 감정이 이입되나요?",
                "감정적인 반응을 존중받는 것이 중요하다고 느끼나요?",
                "자신의 감정을 잘 표현하려고 노력하나요?",
                "결정이 옳더라도 타인을 불편하게 하면 다시 생각하나요?",
                "감정에 휘둘리는 것 같더라도 사람 중심의 접근이 더 옳다고 느끼나요?",
                "논리적으로 옳은 말도 감정적으로 상처 주면 피하려 하나요?",
                "타인의 감정을 헤아리는 것이 더 중요하다고 생각하나요?",
                "의사결정에서 감정과 관계를 우선시하나요?"
        };

        Collections.addAll(questions, templates);
        return questions;
    }


    private List<String> generatePJQuestions() {
        List<String> questions = new ArrayList<>();
        String[] templates = {
                "계획 없이 일을 시작하는 편인가요?",
                "갑작스러운 상황 변화에 잘 적응하나요?",
                "즉흥적인 결정을 자주 하나요?",
                "정해진 일정보다 유연성을 선호하나요?",
                "마감 기한을 엄격히 지키는 편인가요?",
                "계획을 세우는 것이 편안하게 느껴지나요?",
                "갑작스러운 변화를 즐기는 편인가요?",
                "체계적인 일 처리가 중요하다고 느끼나요?",
                "일정을 미리 계획하는 것을 좋아하나요?",
                "즉흥적인 여행을 즐기는 편인가요?",
                "정리된 환경에서 더 잘 일하나요?",
                "유연한 스케줄이 더 편안한가요?",
                "일을 시작하기 전에 계획을 세우나요?",
                "변화에 빠르게 적응하는 편인가요?",
                "결정을 내리기 전에 모든 옵션을 탐색하나요?",
                "계획된 일정에 따라 움직이는 것을 선호하나요?",
                "갑작스러운 제안에 쉽게 응하나요?",
                "체계적인 접근이 더 효율적이라고 느끼나요?",
                "즉흥적인 활동이 더 재미있다고 생각하나요?",
                "미리 정해진 루틴을 따르는 편인가요?",
                "새로운 가능성을 탐색하는 것을 즐기나요?",
                "일정을 변경하는 것이 불편하게 느껴지나요?",
                "계획 없이도 일을 잘 처리할 수 있나요?",
                "정해진 목표를 향해 체계적으로 일하나요?",
                "예상치 못한 상황에서도 편안함을 느끼나요?",
                "일을 마무리하는 것이 중요하다고 생각하나요?",
                "유연하게 계획을 조정하는 편인가요?",
                "정리정돈된 공간에서 더 생산적이라고 느끼나요?",
                "즉흥적인 아이디어를 선호하나요?",
                "미리 계획된 일정이 더 안정적이라고 생각하나요?",
                "변화에 빠르게 대응하는 것이 쉬운가요?",
                "일을 시작하기 전에 상세한 계획을 세우나요?",
                "갑작스러운 변동에 스트레스를 받나요?",
                "자유로운 일정에서 더 창의적이라고 느끼나요?",
                "정해진 절차를 따르는 것이 편안한가요?",
                "새로운 기회를 탐색하는 것이 흥미롭나요?",
                "계획이 틀어지면 불안함을 느끼나요?",
                "즉흥적인 결정을 내리는 것이 자연스러운가요?",
                "체계적인 일 처리가 더 효과적이라고 생각하나요?",
                "예정에 없는 이벤트를 즐기는 편인가요?",
                "일정을 엄격히 따르는 것이 중요하다고 보나요?",
                "유연한 태도로 문제를 해결하나요?",
                "계획을 세울 때 세부사항까지 고려하나요?",
                "갑작스러운 요청에 쉽게 적응하나요?",
                "정리된 일정표가 없으면 불편한가요?",
                "즉흥적인 행동이 더 자유롭다고 느끼나요?",
                "일을 체계적으로 마무리하는 것을 선호하나요?",
                "변화에 열려 있는 태도를 유지하나요?",
                "계획된 일정에서 벗어나는 것이 불편한가요?",
                "새로운 아이디어를 즉시 시도해보고 싶나요?",
                "정해진 일정에 따라 일하는 것이 효율적이라고 보나요?",
                "갑작스러운 상황에서도 침착하게 대응하나요?",
                "일을 시작하기 전에 목표를 명확히 설정하나요?",
                "유연한 일정에서 더 편안함을 느끼나요?",
                "체계적인 환경이 생산성을 높인다고 생각하나요?",
                "즉흥적인 계획 변경을 즐기는 편인가요?",
                "정해진 루틴이 없으면 불안한가요?",
                "새로운 가능성에 열려 있는 편인가요?",
                "계획을 세울 때 장기적인 목표를 고려하나요?",
                "갑작스러운 변화를 기회로 여기나요?",
                "정리된 작업 환경이 중요하다고 느끼나요?",
                "즉흥적인 활동에서 더 에너지를 얻나요?",
                "계획된 일정을 따르는 것이 더 편리하다고 보나요?",
                "변화에 유연하게 대처하는 편인가요?",
                "일을 시작하기 전에 모든 세부사항을 점검하나요?",
                "예기치 않은 상황에서도 즐거움을 느끼나요?",
                "정해진 일정에 따라 일하는 것이 더 효과적이라고 생각하나요?",
                "즉흥적인 결정을 내리는 데 자신 있나요?",
                "체계적인 계획이 없으면 불편함을 느끼나요?",
                "새로운 경험을 시도하는 것을 좋아하나요?",
                "계획이 변경되면 쉽게 적응하나요?",
                "정리된 일정표가 일을 더 쉽게 만든다고 보나요?",
                "즉흥적인 행동이 더 창의적이라고 느끼나요?",
                "일을 마무리하기 전에 모든 가능성을 탐색하나요?",
                "정해진 절차를 따르는 것이 더 안전하다고 생각하나요?",
                "갑작스러운 제안에도 긍정적으로 반응하나요?",
                "계획을 세울 때 세밀한 부분까지 신경 쓰나요?",
                "유연한 일정에서 더 자유롭게 일하나요?",
                "정리된 환경에서 더 집중할 수 있나요?",
                "즉흥적인 활동이 더 흥미롭다고 느끼나요?",
                "계획된 일정을 따르는 것이 스트레스를 줄인다고 보나요?",
                "변화에 빠르게 적응하는 것이 자연스러운가요?",
                "일을 시작하기 전에 목표와 계획을 세우나요?",
                "예상치 못한 상황에서도 긍정적으로 대응하나요?",
                "체계적인 접근이 더 신뢰할 만하다고 생각하나요?",
                "즉흥적인 결정을 내리는 것이 편안한가요?",
                "정해진 일정에서 벗어나면 불편함을 느끼나요?",
                "새로운 기회를 탐색하는 것이 즐거운가요?",
                "계획을 세울 때 모든 가능성을 고려하나요?",
                "갑작스러운 변화에 쉽게 적응하나요?",
                "정리된 일정표가 없으면 혼란스럽다고 느끼나요?",
                "즉흥적인 행동이 더 재미있다고 생각하나요?",
                "일을 체계적으로 처리하는 것이 중요하다고 보나요?",
                "유연한 태도로 새로운 상황에 대처하나요?",
                "계획된 일정에 따라 움직이는 것이 더 편안한가요?",
                "갑작스러운 제안에 긍정적으로 반응하나요?",
                "정리된 환경이 더 효율적이라고 느끼나요?",
                "즉흥적인 활동에서 더 큰 만족감을 느끼나요?",
                "계획된 루틴을 따르는 것이 더 생산적이라고 생각하나요?",
                "정해진 계획이 없어도 일을 잘 마무리하나요?"
        };
        questions.addAll(Arrays.asList(templates));
        return questions;
    }
}
