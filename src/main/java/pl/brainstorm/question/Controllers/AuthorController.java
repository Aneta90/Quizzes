package pl.brainstorm.question.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/ListOfAuthor")
    public ResponseEntity listOfAllAuthor() {
        List<Author> authorList = authorService.getListOfAuthor();
        if (authorList.isEmpty()) {
            logger.info("List of Author is empty.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("List of all Author.");
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @PostMapping("/addAuthor")
    public ResponseEntity saveAuthor(@RequestBody Author author) {
        logger.info("Adding new author : {}", author);
        if (authorService.isAuthorInDatabase(author)) {
            logger.info("There is {} in database.", author);
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.saveAuthor(author);
        logger.info("Added author {}.", author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/QuizzesGreaterThen/{numberOfQuizzes}")
    public ResponseEntity findAuthorsWithNumberOfQuizzesGreaterThen(@PathVariable int numberOfQuizzes) {
        List<Author> authorList = authorService.findAuthorsWithNumberOfQuizzesGreaterThen(numberOfQuizzes);
        if (authorList.isEmpty()) {
            logger.info("No Author with number of Quizzes greater then {}. ", numberOfQuizzes);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of Author with number of Quizzes greater then {}. ", numberOfQuizzes);
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/QuizzesLessThen/{numberOfQuizzes}")
    public ResponseEntity findAuthorsWithNumberOfQuizzesLowerThen(@PathVariable int numberOfQuizzes) {
        List<Author> authorList = authorService.findAuthorsWithNumberOfQuizzesLowerThen(numberOfQuizzes);
        if (authorList.isEmpty()) {
            logger.info("No Author with number of Quizzes less then {}. ", numberOfQuizzes);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of Author with number of Quizzes less then {}. ", numberOfQuizzes);
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @DeleteMapping("/removeAuthorById/{id}")
    public ResponseEntity removeAuthorById(@PathVariable Long id) {
        logger.info("Starting removing author with id {}.", id);
        boolean isRemoved = authorService.removeAuthorById(id);
        if (!isRemoved) {
            logger.info("There is no Author with id {}.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Removed Author with id {}.", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/removeAuthorByEmail/{email}")
    public ResponseEntity removeAuthorById(@PathVariable String email) {
        logger.info("Starting removing author with email {}.", email);
        boolean isRemoved = authorService.removeAuthorByEmail(email);
        if (!isRemoved) {
            logger.info("There is no Author with email {}.", email);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Removed Author with email {}.", email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/isAuthorPresent/{email}")
    public ResponseEntity isAuthorInDatabase(@PathVariable String email) {
        logger.info("Checking is author with email {}, is in database", email);
        boolean isInDatabase = authorService.isAuthorWithEmailInDatabase(email);
        if (!isInDatabase) {
            logger.info("There isn't Author with email {}.", email);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        logger.info("Founding Author with email {}.", email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/EditAuthor/{id}")
    public ResponseEntity editAuthor(@RequestBody Author author, @PathVariable Long id) {
        Author author1 = authorService.editAuthor(author, id);
        if (author1 == null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
