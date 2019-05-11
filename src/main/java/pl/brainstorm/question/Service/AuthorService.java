package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Models.Author;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> getListOfAuthor() {
        return null;
    }

    public Boolean isAuthorInDatabase() {
        return null;
    }

    public Long saveAuthor(Author author) {
        return null;
    }

    public List<Author> findAuthorsWithNumberOfQuizesGreatenThen(int numberOfQuizes) {
        return null;
    }

    public List<Author> findAuthorsWithNumberOfQuizesLowerThen(int numberOfQuizes) {
        return null;
    }

    public Boolean removeAuthorById(Long id) {
        return null;
    }

    public Boolean removeAuthorByEmail(String email) {
        return null;
    }

    public Author editAuthor(Author author, Long id) {
        return null;
    }
}
