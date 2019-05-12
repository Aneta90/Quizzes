package pl.brainstorm.question.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Controllers.AuthorController;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Repositories.AnswerRepository;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Question;


import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MappingService mappingService;
    private final QuestionService questionService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, MappingService mappingService, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.mappingService = mappingService;
        this.questionService = questionService;
    }

    public List<Answer> answerListForGivenQuestion(Long questionId) {
        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizById(questionId);
        if (questionList.size() > 1) {
            return null;
        }
        Question question = questionList.get(0);
        return question.getAnswerList();
    }

    public String getCorrectAnswersForGivenQuestion(Long questionId) {
        List<Answer> answerList = answerListForGivenQuestion(questionId);
        if (answerList.size() > 1) {
            return null;
        }
        Answer answerEntity = answerList.get(0);
        if (answerEntity.getACorrect()) {
            return answerEntity.getAnswerA();
        } else if (answerEntity.getBCorrect()) {
            return answerEntity.getAnswerB();
        } else if (answerEntity.getCCorrect()) {
            return answerEntity.getAnswerC();
        } else {
            return answerEntity.getAnswerD();
        }
    }

    public Long addAnswer(Answer answer) {
        return mappingService.map(answer).getId();
    }

    public Boolean doesAnswerExist(Answer answer) {
        AnswerEntity answerEntity = mappingService.map(answer);
        return answerRepository.existsById(answerEntity.getId());
    }

    public Boolean removeAnswer(Long id) {
        boolean isDeleted = false;
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public Answer editAnswer(Answer answer, Long id) {
        if (answerRepository.existsById(id)) {
            AnswerEntity answerEntity = answerRepository.getOne(id);
            answerEntity.setAnswerA(answer.getAnswerA());
            answerEntity.setACorrect(answer.getACorrect());
            answerEntity.setAnswerB(answer.getAnswerB());
            answerEntity.setBCorrect(answer.getBCorrect());
            answerEntity.setAnswerC(answer.getAnswerC());
            answerEntity.setCCorrect(answer.getCCorrect()); //do przepatrzenia...
            answerEntity.setAnswerD(answer.getAnswerD());
            answerEntity.setDCorrect(answer.getDCorrect());
            answerRepository.save(answerEntity);
            return mappingService.map(answerEntity);
        }
        return null;
    }


}
