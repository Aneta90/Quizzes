package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.ChartEntry;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;
import pl.brainstorm.question.Service.AuthorService;
import pl.brainstorm.question.Service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
class QuizController {

    private final static Logger logger = LoggerFactory.getLogger(QuizController.class);
    private final QuizService quizService;
    private final AuthorService authorService;

    @Autowired
    QuizController(QuizService quizService, AuthorService authorService) {
        this.quizService = quizService;
        this.authorService = authorService;
    }

    //GetMethod
    @GetMapping("/getListOfQuiz")
    ResponseEntity getListOfQuizzes() {
        List<Quiz> quizList = quizService.getListOfQuizzes();
        if (quizList.isEmpty()) {
            logger.info("List of Quiz is empty.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of all Quiz.");
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/singleQuiz/{name}")
    ResponseEntity getSingleQuizWithGivenName(@PathVariable String name) {
        Quiz quiz = quizService.getSingleQuizWithGivenName(name);
        if (quiz == null) {
            logger.info("There isn't any Quiz with name : {}", name);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("Quiz with name : " + name + " \n Quiz : {}", quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/listOfQuizzesWithQuestionsGreaterThen/{numberOfQuestions}")
    ResponseEntity getListOfQuizzesWithNumberOfQuestionsGreaterThen(@PathVariable int numberOfQuestions) {
        List<Quiz> quizList = quizService.getListOfQuizzesWithNumberOfQuestionsGreaterThen(numberOfQuestions);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of questions greater then {}.", numberOfQuestions);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of questions greater then {}.", numberOfQuestions);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/listOfQuizzesWithQuestionsLessThen/{numberOfQuestions}")
    ResponseEntity getListOfQuizzesWithNumberOfQuestionsLowerThen(@PathVariable int numberOfQuestions) {
        List<Quiz> quizList = quizService.getListOfQuizzesWithNumberOfQuestionsLowerThen(numberOfQuestions);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of questions less then {}.", numberOfQuestions);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of questions less then {}.", numberOfQuestions);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/solvedMoreThen/{numberOfTries}")
    ResponseEntity getListOfQuizzesSolvedMoreThen(@PathVariable int numberOfTries) {
        List<Quiz> quizList = quizService.getListOfQuizzesSolvedMoreThen(numberOfTries);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of solved more then {}.", numberOfTries);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of solved more then {}.", numberOfTries);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/solvedLessThen/{numberOfTries}")
    ResponseEntity getListOfQuizzesSolveLessThen(@PathVariable int numberOfTries) {
        List<Quiz> quizList = quizService.getListOfQuizzesSolveLessThen(numberOfTries);
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz with number of solved less then {}.", numberOfTries);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz with number of solved less then {}.", numberOfTries);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/listOfQuizzesSorted")
    ResponseEntity getListOfQuizzesByName() {
        List<Quiz> quizList = quizService.getListOfQuizzesByName();
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz in database.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz sorted by name.");
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/theMostPopularQuizzes")
    ResponseEntity listOfMostPopularQuizzes() {
        List<ChartEntry> quizList = quizService.getListOfMostPopularQuizzes();
        if (quizList.isEmpty()) {
            logger.info("There isn't any quiz in database {}.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of quiz {}.", quizList);
        return new ResponseEntity<>(quizList, HttpStatus.OK);

    }


    //PostMethod
    @PostMapping("/addQuiz/{email}")
    ResponseEntity addQuizToAuthorWithGivenEmail(@RequestBody Quiz quiz, @PathVariable String email) {
        if (quizService.isQuizInDataBase(quiz.getName())) {
            logger.info("There is quiz on database name like quiz you try to add, name : {}", quiz.getName());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        logger.info("Adding quiz : {}, to Author with email {}", quiz, email);

        Author author = authorService.getAuthorByEmail(email);
        author = authorService.addQuizToGivenAuthor(author, quiz);
        authorService.editAuthor(author);
        logger.info("Added quiz : {}", quiz);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/totalScore")
    ResponseEntity returnTotalScoreForGivenQuiz(@RequestBody Quiz quiz) {
        Long totalScore = quizService.calculateTotalScore(quiz);
        if (totalScore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalScore, HttpStatus.OK);
    }

    //PutMethod
    @PutMapping("/editQuiz/email/{email}/quiz/{quizName}")
    ResponseEntity editQuiz(@RequestBody Question question, @PathVariable String email, @PathVariable String quizName) {
        if (authorService.isAuthorInDatabase(email)) {
            Author author = authorService.getAuthorByEmail(email);
            Quiz quiz = quizService.getSingleQuizWithGivenName(quizName);

            quiz = quizService.addNewQuestionToQuiz(quiz, question);
            author = authorService.editQuizInGivenAuthor(author, quiz);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //DeleteMethod
    @DeleteMapping("/editQuiz/email/{email}/quiz/{quizName}")
    ResponseEntity deleteQuizWithGivenNameInGivenAuthor(@PathVariable String email, @PathVariable String quizName) {
        if (authorService.isAuthorInDatabase(email) && quizService.isQuizInDataBase(quizName)) {
            Quiz quiz = quizService.getSingleQuizWithGivenName(quizName);
            Boolean isDeleted = quizService.deleteQuiz(quiz);
            if (isDeleted) {
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}

