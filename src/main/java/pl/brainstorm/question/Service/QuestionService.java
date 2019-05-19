package pl.brainstorm.question.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Controllers.AuthorController;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final QuestionsRepository questionsRepository;
    private final MappingService mappingService;
    private final AnswerService answerService;

    @Autowired
    public QuestionService(QuestionsRepository questionsRepository, MappingService mappingService,
                           AnswerService answerService) {
        this.questionsRepository = questionsRepository;
        this.mappingService = mappingService;
        this.answerService = answerService;
    }

    public List<Question> getListOfQuestions() {
        List<QuestionsEntity> questionsEntityList = questionsRepository.findAll();//findAllPagable??
        List<Question> questionList = new ArrayList<>();
        for (QuestionsEntity questionsEntity : questionsEntityList) {
            questionList.add(mappingService.map(questionsEntity));
        }
        return questionList;
    }

    public List<Question> getListOfQuestionsInGivenQuizByName(String quizName) {
        List<QuestionsEntity> questionsEntityList = questionsRepository.findAllQuestionsByQuizName(quizName);
        List<Question> questionList = new ArrayList<>();
        for (QuestionsEntity questionsEntity : questionsEntityList) {
            questionList.add(mappingService.map(questionsEntity));
        }

        return questionList;
    }

    public Long addQuestion(Question question) {
        return questionsRepository.save(mappingService.map(question)).getId();
    }

    Long calculateTotalScoreInQuestion(Question question, Long quizId) {
        Long tempToCalcScore = 0L;
        tempToCalcScore += answerService.calculateTotalScoreInAnswer(question.getAnswer(), quizId);
        return tempToCalcScore;
    }

    public Boolean doesQuestionExist(Question question) {
        QuestionsEntity questionsEntity = mappingService.map(question);
        return questionsRepository.existsById(questionsEntity.getId());
    }
}
