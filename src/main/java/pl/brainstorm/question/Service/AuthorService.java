package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.AuthorChart;
import pl.brainstorm.question.Models.Quiz;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final MappingService mappingService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, MappingService mappingService) {
        this.authorRepository = authorRepository;
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
        return mappingService.map(authorRepository.findByEmail(email));
    }

    public List<Author> findAuthorsWithNumberOfQuizzesGreaterThen(int numberOfQuizzes) {
        List<Author> authorList = new ArrayList<>();
        List<AuthorEntity> authorEntityList = authorRepository.findAllByQuizListSizeGreaterThanEqual(numberOfQuizzes);

        for (AuthorEntity authorEntity : authorEntityList) {
            authorList.add(mappingService.map(authorEntity));
        }
        return authorList;
    }

    public List<Author> findAuthorsWithNumberOfQuizzesLowerThen(int numberOfQuizzes) {
        List<Author> authorList = new ArrayList<>();
        List<AuthorEntity> authorEntityList = authorRepository.findAllByQuizListSizeLessThanEqual(numberOfQuizzes);

        for (AuthorEntity authorEntity : authorEntityList) {
            authorList.add(mappingService.map(authorEntity));
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
        return authorEntity != null;
    }

    public Boolean isAuthorInDatabase(String email) {
        AuthorEntity authorEntity = authorRepository.findByEmail(email);
        return authorEntity != null;
    }

    public Boolean isAuthorWithEmailInDatabase(String email) {
        AuthorEntity authorEntity = authorRepository.findByEmail(email);
        return authorRepository.existsById(authorEntity.getId());
    }

    @Transactional
    public Author editAuthor(Author author) {
        AuthorEntity authorEntity = authorRepository.findByEmail(author.getEmail());

        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setEmail(author.getEmail());
        authorEntity.setQuizListSize(author.getQuizListSize());
        authorEntity.getQuizEntityList()
                .add(mappingService.map(author.getQuizList().get(author.getQuizList().size() - 1)));
        authorRepository.save(authorEntity);
        return author;
    }

    public Author addQuizToGivenAuthor(Author author, Quiz quiz) {
        quiz.setSizeOfQuestionList(quiz.getQuestionsList().size());
        author.getQuizList().add(quiz);
        author.setQuizListSize(author.getQuizList().size());
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

    public List<AuthorChart> theMostPopularAuthors() {

        List<AuthorEntity> authorEntityList = authorRepository.findAllByQuizEntityListOOrderByQuizListSize();
        List<Author> authorList = new ArrayList<>();
        for (AuthorEntity authorEntity : authorEntityList) {
            authorList.add(mappingService.map(authorEntity));
        }

        return authorList.stream().limit(5)
                .map(author -> new AuthorChart(author.getName(), author.getQuizList().size()))
                .collect(Collectors.toList());
    }
}
