package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Service.AnswerService;
import pl.brainstorm.question.Util.CustomError;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {

    private final static Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/addAnswer")
    public ResponseEntity<?> addAnswer(@RequestBody Answer answer){

        logger.info("Adding answer: {}",answer);
        if(answerService.doesAnswerExist(answer)){
            logger.warn("There is exactly the same answer for given question in our database!.Answer : {}", answer);
            return new ResponseEntity<>(String.valueOf(new CustomError("Unable to create answer. Answer" + answer + "already exists")),HttpStatus.CONFLICT);
        }
        Long createdAnswerId = answerService.addAnswer(answer);
        return new ResponseEntity<>(createdAnswerId, HttpStatus.CREATED);
    }
}
