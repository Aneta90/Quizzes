package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.brainstorm.question.Domain.Repositories.ResponseRepository;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Response;

import java.util.List;

public class ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    List<Response> responseListForGivenQuestion (Long questionId){
        return null;
    }

    List<Response> responseListofCorrectAnswersForGivenQuestion(Long questionId){
        return null;
    }

    Long addResponse(Response response){
        return null;
    }

    Boolean removeResponse(Long id){
        return false;
    }

    Response editResponse(Response response, Long id){
        return null;
    }











}
