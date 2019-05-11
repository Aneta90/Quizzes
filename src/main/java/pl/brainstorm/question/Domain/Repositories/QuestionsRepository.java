package pl.brainstorm.question.Domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsRepository, Long> {
}
