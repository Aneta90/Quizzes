package pl.brainstorm.question.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.QuizRepository;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {


    private final QuizRepository quizRepository;
    private final MappingService mappingService;

    @Autowired
    public QuizService(QuizRepository quizRepository, MappingService mappingService) {
        this.quizRepository = quizRepository;
        this.mappingService = mappingService;
    }


    public List<Quiz> getListOfQuizes() {
        List<QuizEntity> quizEntities = quizRepository.findAll();
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntities) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizesByAuthorId(Long id) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByAuthorId(id);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizesWithNumberOfQuestionsGreatenThen(int numberOfQuestions) {
        return null;
    }

    public List<Quiz> getListOfQuizesWithNumberOfQuestionsLowerThen(int numberOfQuestions) {
        return null;
    }

    public List<Quiz> getListOfQuizesSolvedMoreThen(int numberOfTries) {
        return null;
    }

    public List<Quiz> getListOfQuizesByName() {
        return null;
    }

    public Quiz addQuiz(Quiz quiz) {
        return null;
    }

    public Boolean removeQuize(Long id) {
        return null;
    }

    public Quiz editQuiz(Quiz quiz) {
        return null;
    }

}
