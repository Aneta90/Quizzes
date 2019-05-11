package pl.brainstorm.question.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Repositories.QuizRepository;
import pl.brainstorm.question.Models.Quiz;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getListOfQuizes (){
        return null;
    }

    public List <Quiz> getListOfQuizesByAuthorId (Long id){
        return null;
    }

    public List<Quiz> getListOfQuizesWithNumberOfQuestionsGreatenThen (int numberOfQuestions){
        return null;
    }

    public List<Quiz> getListOfQuizesWithNumberOfQuestionsLowerThen (int numberOfQuestions){
        return null;
    }

    public List<Quiz> getListOfQuizesSolvedMoreThen (int numberOfTries){
        return null;
    }

    public Quiz addQuiz(Quiz quiz) {
        return null;
    }

    public Boolean removeQuize (Long id){
        return null;
    }

    public Quiz editQuiz (Quiz quiz){
        return null;
    }

}
