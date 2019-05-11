package pl.brainstorm.question.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Controllers.AuthorController;
import pl.brainstorm.question.Domain.Repositories.ResponseRepository;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Response;

import java.util.List;

@Service
public class ResponseService {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
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
