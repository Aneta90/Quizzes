package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Repositories.QuestionsRepository;
import pl.brainstorm.question.Models.Question;

import java.util.List;


@Service
public class QuestionService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    List<Question> getListOfQuestion(){ //limit??
        return null;
    }

    List<Question> getListOfQuestionsinGivenQuizById(Long id){
        return null;
    }

    List<Question> getListOfQuestionsinGivenQuizByName(String quizName){
        return null;
    }

}
