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
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.*;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    private Long answerId;
    private Long answer2Id;
    private Long questionId;
    private Long question2Id;
    private Long quizId;
    private Long quiz2Id;
    private Long authorId;

    AuthorEntity authorEntity = new AuthorEntity();

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

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent("Java");
        questionsEntity.setAnswerEntity(answerEntity);

        QuestionsEntity questionsEntity2 = new QuestionsEntity();
        questionsEntity2.setContent("Python");
        questionsEntity2.setAnswerEntity(answerEntity2);

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(questionsEntity);

        List<QuestionsEntity> questionsEntityList2 = new ArrayList<>();
        questionsEntityList.add(questionsEntity2);

        questionId = answerEntity.getId();
        question2Id = answerEntity2.getId();

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setName("AnetaQuiz");
        quizEntity.setQuestionsList(questionsEntityList);
        quizEntity.setNumberOfSolved(1);
        quizEntity.setSizeOfQuestionList(1);

        QuizEntity quizEntity2 = new QuizEntity();
        quizEntity2.setName("Ana's Quiz");
        quizEntity2.setQuestionsList(questionsEntityList2);
        quizEntity2.setNumberOfSolved(1);
        quizEntity2.setSizeOfQuestionList(1);

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

        authorRepository.save(authorEntity);

    }

    @Test
    public void listOfAllAuthor() {

        ResponseEntity<AuthorEntity[]> response = testRestTemplate.getForEntity(
                "/author/listOfAll", AuthorEntity[].class
        );
        List<AuthorEntity> list = authorRepository.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getEmail(), "aw22@onet.pl");
    }


    @Test
    public void getSingleAuthorByEmail() {
        ResponseEntity<AuthorEntity> response = testRestTemplate.getForEntity(
                "/author/getAuthor/aw22@onet.pl", AuthorEntity.class
        );
        List<AuthorEntity> list = authorRepository.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getName(), "Anett");
        assertEquals(list.size(),1);
    }

    @Test
    public void saveAuthor() {

        Answer answer = new Answer();
        answer.setAnswerA("answerA");
        answer.setAnswerB("answerB");
        answer.setAnswerC("answerC");
        answer.setAnswerD("answerD");
        answer.setaCorrect(true);
        answer.setbCorrect(false);
        answer.setcCorrect(false);
        answer.setdCorrect(false);
        answer.setAnswerFromUser("A");

        Question question = new Question();
        question.setContent("Java");
        question.setAnswer(answer);

        List<Question> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(question);

        Quiz quiz = new Quiz();
        quiz.setName("Aneta's Quiz");
        quiz.setQuestionsList(questionsEntityList);
        quiz.setNumberOfSolved(1);
        quiz.setSizeOfQuestionList(questionsEntityList.size());

        List<Quiz> quizList2 = new ArrayList<>();
        quizList2.add(quiz);


        Author author = new Author("Tom","Wrobel","aw23@onet.pl",quizList2,1);

            ResponseEntity<Author> response = testRestTemplate.postForEntity(
                    "/author/add",
                    author,
                    Author.class
            );

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals("Tom", author.getName());
        }

    @Test
    public void findAuthorsWithNumberOfQuizzesGreaterThen() {

        ResponseEntity<AuthorEntity[]> response = testRestTemplate.getForEntity(
                "/author/numberQuizzesGreaterThen/2", AuthorEntity[].class
        );
        List<AuthorEntity> list = authorRepository.findAllByQuizListSizeGreaterThanEqual(2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list.get(0).getName(),"Anett");
    }

    @Test
    public void findAuthorsWithNumberOfQuizzesLowerThen() {

        ResponseEntity<AuthorEntity[]> response = testRestTemplate.getForEntity(
                "/author/numberQuizzesLessThen/3", AuthorEntity[].class
        );
        List<AuthorEntity> list = authorRepository.findAllByQuizListSizeLessThanEqual(3);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list.get(0).getEmail(),"aw22@onet.pl");

    }

    @Test
    public void removeAuthorById() {

        Map<String, Long> uriParams = new HashMap<>();
        uriParams.put("id", 1L);
        testRestTemplate.delete("/author/removeAuthorById/{id}",uriParams);

        Optional removedAuthorEntity = authorRepository.findById(1L);
        assertEquals(removedAuthorEntity,Optional.empty());

    }

    @Test
    public void removeAuthorById1() {

        testRestTemplate.delete("/author/removeAuthorByEmail/aw22@onet.pl");
        AuthorEntity removedAuthorEntity = authorRepository.findByEmail("aw22@onet.pl");
        assertNull(removedAuthorEntity);

    }


    @Test
    public void listOfMostPopularQuizzes() {

        ResponseEntity<AuthorEntity[]> response = testRestTemplate.getForEntity(
                "/author/theMostPopularAuthors", AuthorEntity[].class
        );
        List<AuthorEntity> list = authorRepository.findAllByQuizEntityListOOrderByQuizListSize();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.size(), 1);

    }

    @After
    public void finish(){
        authorRepository.delete(authorEntity);
    }
}
