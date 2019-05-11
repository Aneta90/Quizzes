package pl.brainstorm.question.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.ResponseEntity;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {

    @Query("Select answerA,answerB,answerC,answerD from ResponseEntity r where r.questions.id = ?1") //do sprawdzenia??
    List<ResponseEntity> findAllResponsesByQuestionId (Long id);
    ResponseEntity findCorrectResponseByQuestionId(Long id);

    //najpierw zwrócić wszystkie odp.za pomoca pierwszej metody i potem srawdzic ktora to true

}
