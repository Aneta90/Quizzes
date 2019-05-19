package pl.brainstorm.question.Service;

import org.junit.Before;
import org.junit.Test;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class MappingServiceTest {


    private MappingService mappingService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void mapAnswer() {
        Answer answer = new Answer();
        answer.setAnswerA("A");
        answer.setAnswerB("B");
        answer.setAnswerC("C");
        answer.setAnswerD("D");
        answer.setaCorrect(true);
        answer.setbCorrect(false);
        answer.setcCorrect(false);
        answer.setdCorrect(false);
//        AnswerEntity answerEntity = mappingService.map(answer);
//        assertNotNull(answerEntity.getId());
    }

    @Test
    public void mapAnswerEntity() {
    }

    @Test
    public void mapAuthor() {
    }

    @Test
    public void mapAuthorEntity() {
    }

    @Test
    public void mapQuestion() {
    }

    @Test
    public void mapQuestionEntity() {
    }

    @Test
    public void mapQuiz() {
        List<Long> listOfTotalScore = new ArrayList();
        listOfTotalScore.add(123L);
        List listOfQuestion = new ArrayList();
        Quiz quiz = new Quiz();
        quiz.setName("QuizName");
        quiz.setNumberOfSolved(120);
        quiz.setTotalScore(listOfTotalScore);
        quiz.setQuestionsList(listOfQuestion);
        quiz.setSizeOfQuestionList(1);
       QuizEntity quizEntity=  mappingService.map(quiz);
      // assertNotNull(quizEntity.getId());
    }

    @Test
    public void mapQuizEntity() {
    }
}