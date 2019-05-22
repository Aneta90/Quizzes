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
import pl.brainstorm.question.Domain.Repositories.AnswerRepository;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Domain.Repositories.QuizRepository;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuizControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

    AnswerEntity answerEntity = new AnswerEntity();
    AnswerEntity answerEntity2 = new AnswerEntity();
    AuthorEntity authorEntity = new AuthorEntity();

    @Before
    public void init(){

        answerEntity.setAnswerA("answerA");
        answerEntity.setAnswerB("answerB");
        answerEntity.setAnswerC("answerC");
        answerEntity.setAnswerD("answerD");
        answerEntity.setaCorrect(true);
        answerEntity.setbCorrect(false);
        answerEntity.setcCorrect(false);
        answerEntity.setdCorrect(false);
        answerEntity.setAnswerFromUser("A");

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
        questionsEntityList2.add(questionsEntity2);

        questionId = answerEntity.getId();
        question2Id = answerEntity2.getId();

        quizEntity.setName("AnetaQuiz");
        quizEntity.setQuestionsList(questionsEntityList);
        quizEntity.setNumberOfSolved(1);
        quizEntity.setSizeOfQuestionList(1);

        quizEntity2.setName("Ana's Quiz");
        quizEntity2.setQuestionsList(questionsEntityList2);
        quizEntity2.setNumberOfSolved(3);
        quizEntity2.setSizeOfQuestionList(2);

        quizId = quizEntity.getId();
        quiz2Id = quizEntity2.getId();

        List<QuizEntity> quizEntityList = new ArrayList<>();
        quizEntityList.add(quizEntity);
        quizEntityList.add(quizEntity2);

        authorEntity.setName("Anett");
        authorEntity.setEmail("aw22@onet.pl");
        authorEntity.setSurname("Wrobel");
        authorEntity.setQuizEntityList(quizEntityList);
        authorEntity.setQuizListSize(2);
        authorId = authorEntity.getId();

        quizRepository.save(quizEntity);
        quizRepository.save(quizEntity2);
        answerRepository.save(answerEntity);
        answerRepository.save(answerEntity2);
    }


    @Test
    public void getListOfQuizzes() {

        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/getListOfQuiz", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(1).getName(), "Ana's Quiz");
        assertEquals(list.get(0).getName(),"AnetaQuiz");
    }


    @Test
    public void getSingleQuizWithGivenName() {
        ResponseEntity<QuizEntity> response = testRestTemplate.getForEntity(
                "/quiz/singleQuiz/AnetaQuiz", QuizEntity.class
        );
        List<QuizEntity> list = quizRepository.findAllByName("AnetaQuiz");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getNumberOfSolved(),1);
        assertEquals(list.get(0).getName(),"AnetaQuiz");
    }

    @Test
    public void getListOfQuizzesWithNumberOfQuestionsGreaterThen() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/listOfQuizzesWithQuestionsGreaterThen/1", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllBySizeOfQuestionListGreaterThanEqual(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getName(), "Ana's Quiz");
    }

    @Test
    public void getListOfQuizzesWithNumberOfQuestionsLowerThen() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/listOfQuizzesWithQuestionsLessThen/2", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllBySizeOfQuestionListLessThanEqual(2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.size(),2);
    }

    @Test
    public void getListOfQuizzesSolvedMoreThen() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/solvedMoreThen/1", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllByNumberOfSolvedGreaterThanEqual(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.size(),1);
        assertEquals(list.get(0).getName(),"Ana's Quiz");
    }

    @Test
    public void getListOfQuizzesSolveLessThen() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/solvedLessThen/1", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllByNumberOfSolvedLessThanEqual(2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.size(),1);
        assertEquals(list.get(0).getName(),"AnetaQuiz");
    }

    @Test
    public void getListOfQuizzesByName() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/listOfQuizzesSorted", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllByName();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getName(), "Ana's Quiz");
        assertEquals(list.get(1).getName(),"AnetaQuiz");

    }

    @Test
    public void listOfMostPopularQuizzes() {
        ResponseEntity<QuizEntity[]> response = testRestTemplate.getForEntity(
                "/quiz/theMostPopularQuizzes", QuizEntity[].class
        );
        List<QuizEntity> list = quizRepository.findAllOrderByNumberOfSolved();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getName(), "Ana's Quiz");
        assertEquals(list.get(0).getNumberOfSolved(),3);
        assertEquals(list.get(1).getName(),"AnetaQuiz");
        assertEquals(list.get(1).getNumberOfSolved(),1);
    }

    @Test
    public void addQuizToAuthorWithGivenEmail() {

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

        List<Long> totalScore = new ArrayList<>();
        totalScore.add(5L);

        Quiz quiz = new Quiz("JavaTest",questionsEntityList,totalScore,1,0);

        ResponseEntity<Quiz> response = testRestTemplate.postForEntity(
                "/quiz/addQuiz/aw22@onet.pl",
                quiz,
                Quiz.class
        );

        assertNotNull(response.getBody());
        assertEquals(Integer.valueOf(2), authorEntity.getQuizListSize());
    }

    @Test
    public void returnTotalScoreForGivenQuiz() {
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

        List<Long> totalScore = new ArrayList<>();
        totalScore.add(5L);

        Quiz quiz = new Quiz("JavaTest",questionsEntityList,totalScore,1,0);

        ResponseEntity<Long> response = testRestTemplate.postForEntity(
                "/quiz/totalScore",
                quiz,
                Long.class
        );

        assertNotNull(response.getStatusCode());
        assertEquals(quiz.getTotalScore().get(0),totalScore.get(0));

    }

    @Test
    public void editQuiz() {

        Map<String, String> params = new HashMap<>();
        params.put("id", "1");

        Long id = quizEntity.getId();
        String url = "http://localhost:8080/quiz/editQuiz/email/aw22@onet.pl/quiz/AnetaQuiz";
        quizEntity.setName("AnetaQuizzz");

        testRestTemplate.put(url, quizEntity, params);
        quizRepository.findById(id);
        assertEquals(quizEntity.getName(), "AnetaQuizzz");

    }

    @Test
    public void deleteQuizWithGivenNameInGivenAuthor() {

        quizRepository.delete(quizEntity);
        QuizEntity removedQuizEntity = quizRepository.findByName("AnetaQuiz");
        assertNull(removedQuizEntity);

    }


    @After
    public void finish(){

        quizRepository.delete(quizEntity);
        quizRepository.delete(quizEntity2);
        answerRepository.delete(answerEntity);
        answerRepository.delete(answerEntity2);

    }
}
