package pl.brainstorm.question.Domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Models.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByEmail(String email);
    List <AuthorEntity> findAllByQuizListSizeLessThanEqual(int sizeOfList);
    List <AuthorEntity> findAllByQuizListSizeGreaterThanEqual(int sizeOfList);
}
