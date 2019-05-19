package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Repositories.AnswerRepository;
import pl.brainstorm.question.Models.Answer;


@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MappingService mappingService;


    @Autowired
    public AnswerService(AnswerRepository answerRepository, MappingService mappingService) {
        this.answerRepository = answerRepository;
        this.mappingService = mappingService;
    }

    public Long addAnswer(Answer answer) {
        return answerRepository.save(mappingService.map(answer)).getId();
    }

    public Boolean doesAnswerExist(Answer answer) {
        AnswerEntity answerEntity = mappingService.map(answer);
        return answerRepository.existsById(answerEntity.getId());
    }

    Long calculateTotalScoreInAnswer(Answer answer, Long quizId) {
        long temp = 0L;
            if (answer.getACorrect() && answer.getAnswerFromUser().equals("A")) {
                temp += 1L;
            } else if (answer.getBCorrect() && answer.getAnswerFromUser().equals("B")) {
                temp += 1L;
            } else if (answer.getCCorrect() && answer.getAnswerFromUser().equals("C")) {
                temp += 1L;
            } else if (answer.getDCorrect() && answer.getAnswerFromUser().equals("D")) {
                temp += 1L;
        }
        return temp;
    }


}


