package pl.brainstorm.question.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Controllers.AuthorController;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Models.Question;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final QuestionsRepository questionsRepository;
    private final MappingService mappingService;
    private final QuizService quizService;

    @Autowired
    public QuestionService(MappingService mappingService, QuestionsRepository questionsRepository,QuizService quizService) {
        this.questionsRepository = questionsRepository;
        this.mappingService = mappingService;
        this.quizService = quizService;
    }

    public List<Question> getListOfQuestions(){
        List<QuestionsEntity> questionsEntityList = questionsRepository.findAll();//findAllPagable??
        List<Question> questionList = new ArrayList<>();
        for(QuestionsEntity questionsEntity:questionsEntityList){
            questionList.add(mappingService.map(questionsEntity));
        }
        return questionList;
    }

    public List<Question> getListOfQuestionsInGivenQuizById(Long id){
        List<QuestionsEntity> questionsEntityList = questionsRepository.findAllQuestionsByQuizId(id);
        List<Question> questionList = new ArrayList<>();
        for(QuestionsEntity questionsEntity:questionsEntityList){
            questionList.add(mappingService.map(questionsEntity));
        }
        return questionList;
    }


    public List<Question> getListOfQuestionsInGivenQuizByName(String quizName){
        Long quizId = quizService.findByNameAndReturnId(quizName);
        List<QuestionsEntity> questionsEntityList = questionsRepository.findAllQuestionsByQuizId(quizId);
        List<Question> questionList = new ArrayList<>();
        for(QuestionsEntity questionsEntity:questionsEntityList){
            questionList.add(mappingService.map(questionsEntity));
        }

        return questionList;
    }

    public Long addQuestion(Question question){//zastanowić się jak dodać konkretne pytanie do konkretnego quizu
    return questionsRepository.save(mappingService.map(question)).getId();
    }

    public Boolean doesQuestionExist(Question question){
        QuestionsEntity questionsEntity = mappingService.map(question);
        return questionsRepository.existsById(questionsEntity.getId());
    }

    public Question editQuestion(Long id,Question question){
        if(questionsRepository.existsById(id)) {
            QuestionsEntity questionsEntity = questionsRepository.getOne(id);
            questionsEntity.setContent(question.getContent());
            questionsRepository.save(questionsEntity);
            return mappingService.map(questionsEntity);
        }
        return null;
    }

    public Boolean removeQuestion(Long id){

        boolean isDeleted = false;
        if(questionsRepository.existsById(id)){
            questionsRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
