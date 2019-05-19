package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Service.QuestionService;
import pl.brainstorm.question.Util.CustomError;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionsController {

    private final static Logger logger = LoggerFactory.getLogger(QuestionsController.class);

    private final QuestionService questionService;

    @Autowired
    private QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questionsList")
    ResponseEntity questionsList() {

        List<Question> questionsList = questionService.getListOfQuestions();
        if (questionsList.isEmpty()) {
            logger.warn("Base is empty. Firstly, add some questions.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all questions");
        return new ResponseEntity<>(questionsList, HttpStatus.OK);
    }

    @GetMapping("/questionsListInGivenQuiz/{name}")
    ResponseEntity questionsListInGivenQuizByName(@PathVariable String name) {

        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizByName(name);
        if (questionList.isEmpty()) {
            logger.info("There are no questions for given quiz. Firstly, add quiz with given name or add questions to empty quiz!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("List of all questions");
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    ResponseEntity<?> addQuestion(@RequestBody Question question) {
        logger.info("Adding question: {}", question);
        if (questionService.doesQuestionExist(question)) {
            logger.warn("There is exactly the same question in our database!.Question : {}", question);
            return new ResponseEntity<>(String.valueOf(new CustomError("Unable to create question. Question" + question + "already exists")), HttpStatus.CONFLICT);
        }
        Long createdAnswerId = questionService.addQuestion(question);
        return new ResponseEntity<>(createdAnswerId, HttpStatus.CREATED);
    }
}
