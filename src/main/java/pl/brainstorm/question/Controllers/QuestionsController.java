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
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questionsList")
    public ResponseEntity questionsList() {

        List<Question> questionsList = questionService.getListOfQuestions();
        if (questionsList.isEmpty()) {
            logger.warn("Base is empty. Firstly, add some questions.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all questions");
        return new ResponseEntity<>(questionsList, HttpStatus.OK);
    }

    @GetMapping("/questionsListInGivenQuiz/{id}")
    public ResponseEntity questionsListInGivenQuizById(@PathVariable Long id) {

        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizById(id);
        if (questionList.isEmpty()) {
            logger.info("There are no questions for given quiz. Firstly, add quiz with given id or add questions to empty quiz!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("List of all questions");
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @GetMapping("/questionsListInGivenQuiz/{name}")
    public ResponseEntity questionsListInGivenQuizByName(@PathVariable String name) {

        List<Question> questionList = questionService.getListOfQuestionsInGivenQuizByName(name);
        if (questionList.isEmpty()) {
            logger.info("There are no questions for given quiz. Firstly, add quiz with given name or add questions to empty quiz!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("List of all questions");
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {

        logger.info("Adding question: {}", question);
        if (questionService.doesQuestionExist(question)) {
            logger.warn("There is exactly the same question in our database!.Question : {}", question);
            return new ResponseEntity<>(String.valueOf(new CustomError("Unable to create question. Question" + question + "already exists")), HttpStatus.CONFLICT);
        }
        Long createdAnswerId = questionService.addQuestion(question);
        return new ResponseEntity<>(createdAnswerId, HttpStatus.CREATED);
    }

    @PutMapping("/editQuestion/{id}")
    public ResponseEntity editQuestion(@RequestBody Question question, @PathVariable Long id) {

        logger.info("Edit question with id[}", id);
        Question question1 = questionService.editQuestion(id, question);
        if (question1 == null) {
            logger.error("Something went wrong. You can not edit the question.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(question1, HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/removeQuestion/{id}")
    public ResponseEntity<Boolean> removeQuestion(@PathVariable Long id) {
        boolean isRemoved = questionService.removeQuestion(id);
        if (!isRemoved) {
            logger.error("Question with Id {}, wasn't removed", id);
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
        logger.info("Question with id {}, is deleted", id);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);

    }
}
