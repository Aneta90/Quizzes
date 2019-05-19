package pl.brainstorm.question.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    @Query("select a.quizEntityList from AuthorEntity a where  a.id = ?1")
    List<QuizEntity> findAllByAuthorId(Long id);

    @Query("Select q from QuizEntity q where q.numberOfSolved>0 order by q.numberOfSolved")
    List<QuizEntity> findAllOrderByNumberOfSolved();//chart

    List<QuizEntity> findAllBySizeOfQuestionListLessThanEqual(int size);

    List<QuizEntity> findAllBySizeOfQuestionListGreaterThanEqual(int size);

    List<QuizEntity> findAllByNumberOfSolvedGreaterThanEqual(int size);

    List<QuizEntity> findAllByNumberOfSolvedLessThanEqual(int size);

    List<QuizEntity> findAllByName(String name);

    @Query("SELECT q from QuizEntity q order by q.name")
    List<QuizEntity> findAllByName();

    List<QuizEntity> findAllById(Long id);

    QuizEntity findByName(String name);

}
