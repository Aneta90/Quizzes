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

    @GetMapping("/answerList/{id}")
    public ResponseEntity answerListForGivenQuestion(@PathVariable Long id){

        List<Answer> answerList = answerService.answerListForGivenQuestion(id);
        if(answerList.isEmpty()){
            logger.warn("Base is empty. Firstly, add some answers.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all answers");
        return new ResponseEntity<>(answerList,HttpStatus.OK);
    }

    @GetMapping("/correctAnswerInGivenQuiz/{id}")
    public ResponseEntity correctAnswerForGivenQuestionById(@PathVariable Long id){

        String correctAnswerList = answerService.getCorrectAnswersForGivenQuestion(id);
        if(correctAnswerList == null){
            logger.info("There is no correct answer for this question!. Please, correct the quiz or contact the administrator");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("This is correct answers for given question");
        return new ResponseEntity<>(correctAnswerList,HttpStatus.OK);
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

    @PutMapping("/editAnswer/{id}")
    public ResponseEntity editAnswer(@RequestBody Answer answer, @PathVariable Long id){

        logger.info("Edit answer with id[}", id);
        Answer answer1 = answerService.editAnswer(answer,id);
        if(answer1 == null){
            logger.error("Something went wrong. You can not edit the question.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(answer1,HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/removeAnswer/{id}")
    public ResponseEntity<Boolean> removeAnswer(@PathVariable Long id) {
        boolean isRemoved = answerService.removeAnswer(id);
        if (!isRemoved) {
            logger.error("Answer with Id {}, wasn't removed", id);
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
        logger.info("Answer with id {}, is deleted", id);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);

    }

}
