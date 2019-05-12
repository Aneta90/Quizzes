package pl.brainstorm.question.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {

    @Query("Select q.questionsList from QuizEntity q where q.id = ?1")
    List<QuestionsEntity> findAllQuestionsByQuizId (Long id);






}
