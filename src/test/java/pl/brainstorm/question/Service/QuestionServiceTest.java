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

import static org.junit.Assert.assertEquals;
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

    List<Answer> answerList = new ArrayList<>();
    List<AnswerEntity> answerEntityList = new ArrayList<>();
    List<Quiz> quizList = new ArrayList<>();
    List<QuizEntity> quizEntityList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();
    List<QuestionsEntity> questionEntityList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

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
        List<Question> questionList = questionService.getListOfQuestionsinGivenQuizByName("Anett");
        assertEquals(questionList.size(),1);
        assertEquals("Does Java support multiple inheritance",questionEntityList.get(0).getContent());
    }

    @Test
    public void addQuestion() {

        Question question = new Question();
        que

        QuestionsEntity questionsEntity = new QuestionsEntity();




    }

    @Test
    public void doesQuestionExist() {
    }

    @Test
    public void editQuestion() {
    }

    @Test
    public void removeQuestion() {
    }
}