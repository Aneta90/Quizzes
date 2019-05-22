package pl.brainstorm.question.Controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Domain.Repositories.QuizRepository;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionsControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private QuizRepository quizRepository;

    private Long answerId;
    private Long answer2Id;
    private Long questionId;
    private Long question2Id;
    private Long quizId;
    private Long quiz2Id;
    private Long authorId;

    QuestionsEntity questionsEntity = new QuestionsEntity();
    QuestionsEntity questionsEntity2 = new QuestionsEntity();
    QuizEntity quizEntity = new QuizEntity();
    QuizEntity quizEntity2 = new QuizEntity();


    @Before
    public void init(){

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA("answerA");
        answerEntity.setAnswerB("answerB");
        answerEntity.setAnswerC("answerC");
        answerEntity.setAnswerD("answerD");
        answerEntity.setaCorrect(true);
        answerEntity.setbCorrect(false);
        answerEntity.setcCorrect(false);
        answerEntity.setdCorrect(false);
        answerEntity.setAnswerFromUser("A");

        AnswerEntity answerEntity2 = new AnswerEntity();
        answerEntity2.setAnswerA("answerA");
        answerEntity2.setAnswerB("answerB");
        answerEntity2.setAnswerC("answerC");
        answerEntity2.setAnswerD("answerD");
        answerEntity2.setaCorrect(true);
        answerEntity2.setbCorrect(false);
        answerEntity2.setcCorrect(false);
        answerEntity2.setdCorrect(false);
        answerEntity2.setAnswerFromUser("A");

        answerId = answerEntity.getId();
        answer2Id = answerEntity2.getId();

        questionsEntity.setContent("Java");
        questionsEntity.setAnswerEntity(answerEntity);

        questionsEntity2.setContent("Python");
        questionsEntity2.setAnswerEntity(answerEntity2);

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(questionsEntity);

        List<QuestionsEntity> questionsEntityList2 = new ArrayList<>();
        questionsEntityList.add(questionsEntity2);

        questionId = answerEntity.getId();
        question2Id = answerEntity2.getId();

        quizEntity.setName("AnetaQuiz");
        quizEntity.setQuestionsList(questionsEntityList);
        quizEntity.setNumberOfSolved(1);
        quizEntity.setSizeOfQuestionList(1);

        quizEntity2.setName("Ana's Quiz");
        quizEntity2.setQuestionsList(questionsEntityList2);
        quizEntity2.setNumberOfSolved(1);
        quizEntity2.setSizeOfQuestionList(1);

        quizId = quizEntity.getId();
        quiz2Id = quizEntity2.getId();

        List<QuizEntity> quizEntityList = new ArrayList<>();
        quizEntityList.add(quizEntity);
        quizEntityList.add(quizEntity2);

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("Anett");
        authorEntity.setEmail("aw22@onet.pl");
        authorEntity.setSurname("Wrobel");
        authorEntity.setQuizEntityList(quizEntityList);
        authorEntity.setQuizListSize(2);
        authorId = authorEntity.getId();

        quizRepository.save(quizEntity);
        quizRepository.save(quizEntity2);
        questionsRepository.save(questionsEntity);
        questionsRepository.save(questionsEntity2);

    }

    @Test
    public void questionsList() {
        ResponseEntity<QuestionsEntity[]> response = testRestTemplate.getForEntity(
                "/questions/questionsList", QuestionsEntity[].class
        );
        List<QuestionsEntity> list = questionsRepository.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(1).getContent(),"Python");

    }

    @Test
    public void questionsListInGivenQuizByName() {

        ResponseEntity<QuestionsEntity> response = testRestTemplate.getForEntity(
                "/questionsListInGivenQuiz/AnetaQuiz", QuestionsEntity.class
        );
        List<QuestionsEntity> list = questionsRepository.findAllQuestionsByQuizName("AnetaQuiz");
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getContent(),"Java");
        assertTrue(list.get(0).getAnswerEntity().isaCorrect());
    }

    @Test
    public void addQuestion() {

        Answer answer1 = new Answer();
        answer1.setAnswerA("answerA");
        answer1.setAnswerB("answerB");
        answer1.setAnswerC("answerC");
        answer1.setAnswerD("answerD");
        answer1.setaCorrect(true);
        answer1.setbCorrect(false);
        answer1.setcCorrect(false);
        answer1.setdCorrect(false);
        answer1.setAnswerFromUser("A");

        Question question1 = new Question("Spring",answer1);

        List<Question> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(question1);

        Quiz quiz = new Quiz();
        quiz.setName("Aneta's Quiz");
        quiz.setQuestionsList(questionsEntityList);
        quiz.setNumberOfSolved(1);
        quiz.setSizeOfQuestionList(questionsEntityList.size());


        ResponseEntity<Question> response = testRestTemplate.postForEntity(
                "/questions/addQuestion",
                question1,
                Question.class
        );

        assertNotNull(response.getBody());
        assertEquals("Spring", question1.getContent());

    }


    @After
    public void finish(){
        quizRepository.delete(quizEntity);
        quizRepository.delete(quizEntity2);
        questionsRepository.delete(questionsEntity);
        questionsRepository.delete(questionsEntity2);
    }
}