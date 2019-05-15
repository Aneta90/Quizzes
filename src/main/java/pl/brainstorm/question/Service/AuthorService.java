package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final QuizService quizService;
    private final MappingService mappingService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, QuizService quizService, MappingService mappingService) {
        this.authorRepository = authorRepository;
        this.quizService = quizService;
        this.mappingService = mappingService;
    }

    public List<Author> getListOfAuthor() {
        List<AuthorEntity> authorEntityList = authorRepository.findAll();
        List<Author> authorsList = new ArrayList<>();
        for (AuthorEntity authorEntity : authorEntityList) {
            authorsList.add(mappingService.map(authorEntity));
        }
        return authorsList;
    }

    public Long saveAuthor(Author author) {
        return authorRepository.save(mappingService.map(author)).getId();
    }

    public Author getAuthorByEmail(String email) {
        Author author = mappingService.map(authorRepository.findByEmail(email));
        return author;
    }

    public List<Author> findAuthorsWithNumberOfQuizzesGreaterThen(int numberOfQuizzes) {
        List<Author> authorList = new ArrayList<>();
        List<AuthorEntity> authorEntityList = authorRepository.findAllByQuizListSizeGreaterThanEqual(numberOfQuizzes);

        for (int i = 0; i < authorEntityList.size(); i++) {
            authorList.add(mappingService.map(authorEntityList.get(i)));
        }
        return authorList;
    }

    public List<Author> findAuthorsWithNumberOfQuizzesLowerThen(int numberOfQuizzes) {
        List<Author> authorList = new ArrayList<>();
        List<AuthorEntity> authorEntityList = authorRepository.findAllByQuizListSizeLessThanEqual(numberOfQuizzes);

        for (int i = 0; i < authorEntityList.size(); i++) {
            authorList.add(mappingService.map(authorEntityList.get(i)));
        }
        return authorList;
    }

    public Boolean removeAuthorById(Long id) {
        AuthorEntity authorEntity = authorRepository.getOne(id);
        authorRepository.deleteById(authorEntity.getId());
        return true;
    }

    public Boolean removeAuthorByEmail(String email) {
        AuthorEntity authorEntity = authorRepository.findByEmail(email);
        if (authorEntity.getEmail() == null) {
            return false;
        }
        authorRepository.deleteById(authorEntity.getId());
        return true;
    }

    public Boolean isAuthorInDatabase(Author author) {
        AuthorEntity authorEntity = authorRepository.findByEmail(author.getEmail());
        if (authorEntity != null) {
            return true;
        }
        return false;
    }

    public Boolean isAuthorInDatabase(String email) {
        AuthorEntity authorEntity = authorRepository.findByEmail(email);
        if (authorEntity != null) {
            return true;
        }
        return false;
    }

    public Boolean isAuthorWithEmailInDatabase(String email) {
        AuthorEntity authorEntity = authorRepository.findByEmail(email);
        return authorRepository.existsById(authorEntity.getId());
    }

    public Author editAuthor(Author author, Long id) {
        List<AuthorEntity> authorEntityList = authorRepository.findAllById(Collections.singleton(id));
        if (authorEntityList.size() != 1) {
            return null;
        }
        AuthorEntity authorEntity = authorEntityList.get(0);
        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setEmail(author.getEmail());
        authorEntity.setQuizListSize(author.getQuizListSize());
        List<QuizEntity> quizEntityList = new ArrayList<>();

        for (int i = 0; i < author.getQuizList().size(); i++) {
            quizEntityList.add(mappingService.map(author.getQuizList().get(i)));
        }
        authorEntity.setQuizEntityList(quizEntityList);
        authorRepository.save(authorEntity);
        return author;
    }

    public Author editAuthor(Author author) {
        AuthorEntity authorEntity = authorRepository.findByEmail(author.getEmail());

        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setEmail(author.getEmail());
        authorEntity.setQuizListSize(author.getQuizListSize());
        List<QuizEntity> quizEntityList = new ArrayList<>();
        for (int i = 0; i < author.getQuizList().size(); i++) {
            quizEntityList.add(mappingService.map(author.getQuizList().get(i)));
        }
        authorEntity.setQuizEntityList(quizEntityList);
        authorRepository.save(authorEntity);
        return author;
    }

    public Author addQuizToGivenAuthor(Author author, Quiz quiz) {
        quiz.setSizeOfQuestionList(0);
        author.getQuizList().add(quiz);
        return author;
    }

    public Author editQuizInGivenAuthor(Author author, Quiz quiz) {
        for (int i = 0; i < author.getQuizList().size(); i++) {
            if (author.getQuizList().get(i).getName().equals(quiz.getName())) {
                author.getQuizList().set(i, quiz);
            }
        }
        return author;
    }
}
