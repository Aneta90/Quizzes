package pl.brainstorm.question.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;

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


    @Before
    public void setUp(){

    }

    @Test
    public void getListOfQuestions() {

    }

    @Test
    public void getListOfQuestionsInGivenQuizById() {

    }

    @Test
    public void getListOfQuestionsInGivenQuizByName() {

    }

    @Test
    public void addQuestion() {

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