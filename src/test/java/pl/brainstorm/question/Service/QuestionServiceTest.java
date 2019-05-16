package pl.brainstorm.question.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Mock
    QuestionsRepository questionsRepository;

    @Mock
    QuizService quizService;

    @Mock
    MappingService mappingService;

    @InjectMocks
    QuestionService questionService;

    private List<Answer> answerList = new ArrayList<>();
    private List<AnswerEntity> answerEntityList = new ArrayList<>();
    private List<Quiz> quizList = new ArrayList<>();
    private List<QuizEntity> quizEntityList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private List<QuestionsEntity> questionEntityList = new ArrayList<>();

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        questionService = new QuestionService(mappingService,questionsRepository,quizService);

        Answer answer = new Answer();
        answer.setAnswerA("Yes");
        answer.setAnswerB("No");
        answer.setAnswerC("It depends on the case");
        answer.setAnswerD("Only with abstract classes");
        answer.setACorrect(false);
        answer.setBCorrect(true);
        answer.setCCorrect(false);
        answer.setDCorrect(false);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA("Yes");
        answerEntity.setAnswerB("No");
        answerEntity.setAnswerC("It depends on the case");
        answerEntity.setAnswerD("Only with abstract classes");
        answerEntity.setACorrect(false);
        answerEntity.setBCorrect(true);
        answerEntity.setCCorrect(false);
        answerEntity.setDCorrect(false);

        answerList.add(answer);
        answerEntityList.add(answerEntity);

        Author author = new Author();
        author.setName("Aneta");
        author.setEmail("aw22@onet.pl");
        author.setSurname("Wrobel");
        author.setQuizList(quizList);

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("Aneta");
        authorEntity.setEmail("aw22@onet.pl");
        authorEntity.setSurname("Wrobel");
        authorEntity.setQuizEntityList(quizEntityList);

        Quiz quiz =new Quiz();
        quiz.setName("Anett");
        quiz.setNumberOfSolved(1);
        quiz.setQuestionsList(questionList);
        quizList.add(quiz);

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setName("Anett");
        quizEntity.setNumberOfSolved(1);
        quizEntity.setQuestionsList(questionEntityList);
        quizEntityList.add(quizEntity);

        Question question = new Question();
        question.setAnswerList(answerList);
        question.setContent("Does Java support multiple inheritance");

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent("Does Java support multiple inheritance");
        questionsEntity.setAnswerEntityList(answerEntityList);

        questionList.add(question);
        questionEntityList.add(questionsEntity);

        when(questionsRepository.findAll()).thenReturn(questionEntityList);
        when(questionsRepository.findAllQuestionsByQuizId(anyLong())).thenReturn(questionEntityList);

    }

    @Test
    public void getListOfQuestions() {
        List<Question> questionList = questionService.getListOfQuestions();
        assertEquals(questionList.size(),1);
        assertEquals("Does Java support multiple inheritance",questionEntityList.get(0).getContent());
    }

    @Test
    public void getListOfQuestionsInGivenQuizById() {
        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizById(1L);
        assertEquals(questionList.size(),1);
        assertEquals("Does Java support multiple inheritance",questionEntityList.get(0).getContent());
    }

    @Test
    public void getListOfQuestionsInGivenQuizByName() {
        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizByName("Anett");
        assertEquals(questionList.size(),1);
        assertEquals("Does Java support multiple inheritance",questionEntityList.get(0).getContent());
    }

    @Test
    public void addQuestion() {

        Answer answer = new Answer();
        answer.setAnswerA("int");
        answer.setACorrect(true);
        answer.setAnswerB("String");
        answer.setBCorrect(false);
        answer.setAnswerC("Long");
        answer.setCCorrect(false);
        answer.setAnswerD("Integer");
        answer.setDCorrect(false);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA("int");
        answerEntity.setACorrect(true);
        answerEntity.setAnswerB("String");
        answerEntity.setBCorrect(false);
        answerEntity.setAnswerC("Long");
        answerEntity.setCCorrect(false);
        answerEntity.setAnswerD("Integer");
        answerEntity.setDCorrect(false);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        answerEntityList.add(answerEntity);

        Question question = new Question();
        question.setContent("Primitive types are:");
        question.setAnswerList(answerList);

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent("Primitive types are:");
        questionsEntity.setAnswerEntityList(answerEntityList);

        when(questionsRepository.save(questionsEntity)).thenReturn(questionsEntity);
        assertEquals(questionsEntity.getContent(),"Primitive types are:");
    }

    @Test
    public void doesQuestionExist() {

        Answer answer = new Answer();
        answer.setAnswerA("int");
        answer.setACorrect(true);
        answer.setAnswerB("String");
        answer.setBCorrect(false);
        answer.setAnswerC("Long");
        answer.setCCorrect(false);
        answer.setAnswerD("Integer");
        answer.setDCorrect(false);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);

        Question question = new Question();
        question.setContent("Primitive types are:");
        question.setAnswerList(answerList);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA("int");
        answerEntity.setACorrect(true);
        answerEntity.setAnswerB("String");
        answerEntity.setBCorrect(false);
        answerEntity.setAnswerC("Long");
        answerEntity.setCCorrect(false);
        answerEntity.setAnswerD("Integer");
        answerEntity.setDCorrect(false);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        answerEntityList.add(answerEntity);

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setId(1L);
        questionsEntity.setContent("Primitive types are:");
        questionsEntity.setAnswerEntityList(answerEntityList);

        when(questionsRepository.existsById(anyLong())).thenReturn(true);
        assertEquals(Optional.of(questionsEntity.getId()), Optional.of(1L));
    }

    @Test
    public void editQuestion() {

        Answer answer = new Answer();
        answer.setAnswerA("int");
        answer.setACorrect(true);
        answer.setAnswerB("BigInteger");
        answer.setBCorrect(false);
        answer.setAnswerC("BigDecimal");
        answer.setCCorrect(false);
        answer.setAnswerD("Boolean");
        answer.setDCorrect(false);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);

        Question question = new Question();
        question.setContent("Primitive types are:");
        question.setAnswerList(answerList);

        List<Question> questionList = new ArrayList<>();
        questionList.add(question);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA("int");
        answerEntity.setACorrect(true);
        answerEntity.setAnswerB("BigInteger");
        answerEntity.setBCorrect(false);
        answerEntity.setAnswerC("BigDecimal");
        answerEntity.setCCorrect(false);
        answerEntity.setAnswerD("Boolean");
        answerEntity.setDCorrect(false);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        answerEntityList.add(answerEntity);

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent("Primitive types are:");
        questionsEntity.setAnswerEntityList(answerEntityList);

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(questionsEntity);

        QuestionsEntity questionEntity1 = questionsRepository.save(questionsEntity);
        Long id = questionEntity1.getId();
        when(mappingService.map(questionsEntityList.get(0))).thenReturn(question);
        Question question1 = questionService.editQuestion(id,question);
        assertEquals("Primitive types are:", question1.getContent());

    }

    @Test
    public void removeQuestion() {



    }
}