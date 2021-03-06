package pl.brainstorm.question.Domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByEmail(String email);
    @Query("select a from AuthorEntity a WHERE a.quizListSize < ?1")
    List <AuthorEntity> findAllByQuizListSizeLessThanEqual(int sizeOfList);
    @Query("select a from AuthorEntity a WHERE SIZE(a.quizEntityList) >= ?1")
    List <AuthorEntity> findAllByQuizListSizeGreaterThanEqual(int sizeOfList);
    @Query("select a from AuthorEntity a WHERE a.quizListSize>0 order by a.quizListSize")
    List <AuthorEntity> findAllByQuizEntityListOOrderByQuizListSize();
}
