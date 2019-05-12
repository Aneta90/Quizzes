package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brainstorm.question.Models.Quiz;
import pl.brainstorm.question.Service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {

    private final static Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping("/getListOfQuiz")
    public ResponseEntity getListOfQuizzes() {
        List<Quiz> quizList = quizService.getListOfQuizzes();
        if (quizList.isEmpty()) {
            logger.info("List of Quiz is empty.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of all Quiz.");
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/getListOfQuizzesByAuthor/{id}")
    public ResponseEntity getListOfQuizzesByAuthorId(@PathVariable Long id) {
        List<Quiz> quizList = quizService.getListOfQuizzesByAuthorId(id);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz made by author with id {}.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quizzes made by author with id {}.", id);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/SingleQuiz/{name}")
    public ResponseEntity getSingleQuizWithGivenName(@PathVariable String name) {
        Quiz quiz = quizService.getSingleQuizWithGivenName(name);
        if (quiz == null) {
            logger.info("There isn't any Quiz with name : {}", name);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("Quiz with name : " + name + " \n Quiz : {}", quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/ListOfQuizzesWithQuestionsGreaterThen/{numberOfQuestions}")
    public ResponseEntity getListOfQuizzesWithNumberOfQuestionsGreaterThen(@PathVariable int numberOfQuestions) {
        List<Quiz> quizList = quizService.getListOfQuizzesWithNumberOfQuestionsGreaterThen(numberOfQuestions);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of questions greater then {}.", numberOfQuestions);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of questions greater then {}.", numberOfQuestions);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/ListOfQuizzesWithQuestionsLessThen/{numberOfQuestions}")
    public ResponseEntity getListOfQuizzesWithNumberOfQuestionsLowerThen(@PathVariable int numberOfQuestions) {
        List<Quiz> quizList = quizService.getListOfQuizzesWithNumberOfQuestionsLowerThen(numberOfQuestions);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of questions less then {}.", numberOfQuestions);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of questions less then {}.", numberOfQuestions);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/solvedMoreThen/{numberOfTries}")
    public ResponseEntity getListOfQuizzesSolvedMoreThen(@PathVariable int numberOfTries) {
        List<Quiz> quizList = quizService.getListOfQuizzesSolvedMoreThen(numberOfTries);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of solved more then {}.", numberOfTries);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of solved more then {}.", numberOfTries);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/solvedLessThen/{numberOfTries}")
    public ResponseEntity getListOfQuizzesSolveLessThen(@PathVariable int numberOfTries) {
        List<Quiz> quizList = quizService.getListOfQuizzesSolveLessThen(numberOfTries);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of solved less then {}.", numberOfTries);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of solved less then {}.", numberOfTries);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/ListOfQuizzesSorted")
    public ResponseEntity getListOfQuizzesByName() {
        List<Quiz> quizList = quizService.getListOfQuizzesByName();
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz in database.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz sorted by name.");
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @PostMapping("/addQuiz")
    public ResponseEntity addQuiz(@RequestBody Quiz quiz) {
        Boolean isInDatabase = quizService.isQuizInDataBase(quiz);
        if (isInDatabase) {
            logger.info("There is quiz on database name like quiz you try to add, name : {}", quiz.getName());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        logger.info("Adding quiz : {}", quiz);
        Long id = quizService.addQuiz(quiz);
        logger.info("added quiz : {}", quiz);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuiz/{id}")
    public ResponseEntity removeQuiz(@PathVariable Long id) {
        boolean isDeleted = quizService.removeQuiz(id);
        if (!isDeleted) {
            logger.info("Quiz with id {}, isn't deleted", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("Quiz with id {}, is deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/EditQuiz/{id}")
    public ResponseEntity editQuiz(@RequestBody Quiz quiz, @PathVariable Long id) {
        Quiz quiz1 = quizService.editQuiz(id, quiz);
        if (!quiz.equals(quiz1)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
