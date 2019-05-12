package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Models.Author;

import java.util.ArrayList;
import java.util.List;

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

    public List<Author> findAuthorsWithNumberOfQuizesGreatenThen(int numberOfQuizes) {
        return null;
    }

    public List<Author> findAuthorsWithNumberOfQuizesLowerThen(int numberOfQuizes) {
        return null;
    }

    public Boolean removeAuthorById(Long id) {
        authorRepository.deleteById(id);
        return null;
    }

    public Boolean removeAuthorByEmail(String email) {
        return null;
    }

    public Boolean isAuthorInDatabase(Author author) {
        AuthorEntity authorEntity = authorRepository.findByEmail(author.getEmail());
        return authorRepository.existsById(authorEntity.getId());
    }

    public Author editAuthor(Author author, Long id) {
        return null;
    }
}
