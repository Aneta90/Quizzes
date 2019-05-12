package pl.brainstorm.question.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("Select answerA,answerB,answerC,answerD from AnswerEntity r where r.questions.id = ?1") //do sprawdzenia??
    List<AnswerEntity> findAllResponsesByQuestionId (Long id);
    AnswerEntity findCorrectResponseByQuestionId(Long id);

    //najpierw zwrócić wszystkie odp.za pomoca pierwszej metody i potem srawdzic ktora to true

}
