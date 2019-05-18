package pl.brainstorm.question.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.QuizRepository;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {


    private final QuizRepository quizRepository;
    private final MappingService mappingService;
    private final QuestionService questionService;

    @Autowired
    public QuizService(QuizRepository quizRepository, MappingService mappingService, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.mappingService = mappingService;
        this.questionService = questionService;
    }

    public List<Quiz> getListOfQuizzes() {
        List<QuizEntity> quizEntities = quizRepository.findAll();
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntities) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesWithNumberOfQuestionsGreaterThen(int numberOfQuestions) {
        List<QuizEntity> quizEntityList = quizRepository.findAllBySizeOfQuestionListGreaterThanEqual(numberOfQuestions);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesWithNumberOfQuestionsLowerThen(int numberOfQuestions) {
        List<QuizEntity> quizEntityList = quizRepository.findAllBySizeOfQuestionListLessThanEqual(numberOfQuestions);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesSolvedMoreThen(int numberOfTries) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByNumberOfSolvedGreaterThanEqual(numberOfTries);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesSolveLessThen(int numberOfTries) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByNumberOfSolvedLessThanEqual(numberOfTries);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesByName() {
        List<QuizEntity> quizEntityList = quizRepository.findAllByName();

        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public Quiz addNewQuestionToQuiz(Quiz quiz, Question question) {
        QuizEntity quizEntity = quizRepository.findByName(quiz.getName());
        quizEntity.setTotalScore(quiz.getTotalScore());
        quizEntity.setNumberOfSolved(quiz.getNumberOfSolved());
        quizEntity.getQuestionsList().add(mappingService.map(question));

        quizEntity.setSizeOfQuestionList(quiz.getQuestionsList().size() + 1);
        quizRepository.save(quizEntity);
        return mappingService.map(quizEntity);
    }

    public Boolean isQuizInDataBase(String quizName) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByName(quizName);
        if (quizEntityList.size() != 1) {
            return false;
        }
        return true;
    }

    public Quiz getSingleQuizWithGivenName(String name) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByName(name);
        if (quizEntityList.size() != 1) {
            return null;
        }
        return mappingService.map(quizEntityList.get(0));
    }

    public Quiz calculateTotalScore(Quiz quiz) {
        Long tempToCalcScore = 0L;
        for (int i = 0; i < quiz.getQuestionsList().size(); i++) {
            tempToCalcScore += questionService.calculateTotalScoreInQuestion(quiz.getQuestionsList().get(i));

        }
        List<Long> listOfTotalScore = quiz.getTotalScore();
        listOfTotalScore.add(tempToCalcScore);
        return quiz;
    }


    public Boolean deleteGuiz(Quiz quiz) {
        quizRepository.delete(mappingService.map(quiz));
        return !isQuizInDataBase(quiz.getName());
    }
}
