package pl.brainstorm.question.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Controllers.AuthorController;
import pl.brainstorm.question.Domain.Entities.ResponseEntity;
import pl.brainstorm.question.Domain.Repositories.ResponseRepository;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Response;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseService {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final ResponseRepository responseRepository;
    private final MappingService mappingService;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, MappingService mappingService) {
        this.responseRepository = responseRepository;
        this.mappingService = mappingService;
    }

    public List<Response> responseListForGivenQuestion (Long questionId){
        List<ResponseEntity> responseEntityList = responseRepository.findAllResponsesByQuestionId(questionId);
        List<Response> responseList = new ArrayList<>();
        for(ResponseEntity responseEntity:responseEntityList){
            responseList.add(mappingService.map(responseEntity));
        }
        return responseList;
    }

    public String getCorrectAnswersForGivenQuestion(Long questionId){
        List<ResponseEntity> responseEntityList = responseRepository.findAllResponsesByQuestionId(questionId);
        responseEntityList.stream().filter(a->a.equals(true)).findFirst(); //byc może zrobić to w Questions
        ResponseEntity responseEntity= responseRepository.findCorrectResponseByQuestionId(questionId);
        if(responseEntity.getACorrect()){
            return responseEntity.getAnswerA();
        } else if(responseEntity.getBCorrect()){
            return responseEntity.getAnswerB();
        } else if (responseEntity.getCCorrect()){
            return responseEntity.getAnswerC();
        } else {
            return responseEntity.getAnswerD();
        }
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
