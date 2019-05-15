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

    @Autowired
    public QuizService(QuizRepository quizRepository, MappingService mappingService) {
        this.quizRepository = quizRepository;
        this.mappingService = mappingService;
    }


    public List<Quiz> getListOfQuizzes() {
        List<QuizEntity> quizEntities = quizRepository.findAll();
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntities) {
            quizList.add(mappingService.map(quizEntity));
        }
        return quizList;
    }

    public List<Quiz> getListOfQuizzesByAuthorId(Long id) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByAuthorId(id);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntityList) {
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

    public Long addQuiz(Quiz quiz) {
        return quizRepository.save(mappingService.map(quiz)).getId();
    }

    public Boolean removeQuiz(Long id) {
        List<QuizEntity> quizEntityList = quizRepository.findAllById(id);
        if (quizEntityList.size() > 1 || quizEntityList.size() == 0) {
            return false;
            // do zastanowienia sie czy nie przejac to przy kontrolerze
        }
        quizRepository.delete(quizEntityList.get(0));
        return true;
    }

    public Quiz editQuiz(Long id, Quiz quiz) {
        QuizEntity quizEntity = quizRepository.getOne(id);
        quizEntity.setName(quiz.getName());
        quizEntity.setTotalScore(quiz.getTotalScore());
        quizEntity.setSizeOfQuestionList(quiz.getSizeOfQuestionList());
        quizEntity.setNumberOfSolved(quiz.getNumberOfSolved());
        quizRepository.save(quizEntity);
        return mappingService.map(quizEntity);
    }

    public Quiz addNewQuestionToQuiz(Quiz quiz, Question question) {
        QuizEntity quizEntity = quizRepository.findByName(quiz.getName());
       //prawdopodobnie zbedne do sprawdzenia
        quizEntity.setTotalScore(quiz.getTotalScore());
        quizEntity.setNumberOfSolved(quiz.getNumberOfSolved());
        quizEntity.getQuestionsList().add(mappingService.map(question));

        quizEntity.setSizeOfQuestionList(quiz.getQuestionsList().size()+1);
        quizRepository.save(quizEntity);
        return mappingService.map(quizEntity);
    }

    public Long findByNameAndReturnId(String quizName) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByName(quizName);
        if (quizEntityList.size() > 1) {
            // do przemyslenia swoje zycie
        }
        return quizEntityList.get(0).getId();
    }

    public Boolean isQuizInDataBase(Quiz quiz) {
        List<QuizEntity> quizEntityList = quizRepository.findAllByName(quiz.getName());
        if (quizEntityList.size() != 1) {
            return false;
        }
        return true;
    }

    public Boolean isQuizInDataBase(String quizName) { // czy nie lepeiej zrobic szukanie bez listy? bo name unikalny
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


}