package pl.brainstorm.question.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {



}
