package pl.brainstorm.question.Controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    AuthorRepository authorRepository;

    private AuthorEntity authorEntity = new AuthorEntity();

    Long answerId;
    Long questionId;
    Long quizId;
    Long authorId;

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

        answerId = answerEntity.getId();

        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent("Java");
        questionsEntity.setAnswerEntity(answerEntity);

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        questionsEntityList.add(questionsEntity);

        questionId = answerEntity.getId();

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setName("AnetaQuiz");
        quizEntity.setQuestionsList(questionsEntityList);
        quizEntity.setNumberOfSolved(1);
        quizEntity.setSizeOfQuestionList(1);

        quizId = quizEntity.getId();

        List<QuizEntity> quizEntityList = new ArrayList<>();
        quizEntityList.add(quizEntity);

        authorEntity.setName("Anett");
        authorEntity.setEmail("aw22");
        authorEntity.setSurname("Wrobel");
        authorEntity.setQuizEntityList(quizEntityList);
        authorEntity.setQuizListSize(1L);

        authorId = authorEntity.getId();

    }

    @Test
    public void listOfAllAuthor() {

    }


    @Test
    public void getSingleAuthorByEmail() {
    }

    @Test
    public void saveAuthor() {
    }

    @Test
    public void findAuthorsWithNumberOfQuizzesGreaterThen() {
    }

    @Test
    public void findAuthorsWithNumberOfQuizzesLowerThen() {
    }

    @Test
    public void removeAuthorById() {
    }

    @Test
    public void removeAuthorById1() {
    }

    @Test
    public void isAuthorInDatabase() {
    }

    @Test
    public void listOfMostPopularQuizzes() {
    }
}
