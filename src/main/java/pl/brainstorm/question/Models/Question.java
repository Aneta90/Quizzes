package pl.brainstorm.question.Models;

import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Entities.ResponseEntity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class Question {

    private QuizEntity quizEntity;
    private String content;
    private List<ResponseEntity> responseEntityList;

    public Question() {
    }

    public Question(QuizEntity quizEntity, String content, List<ResponseEntity> responseEntityList) {
        this.quizEntity = quizEntity;
        this.content = content;
        this.responseEntityList = responseEntityList;
    }

    public QuizEntity getQuizEntity() {
        return quizEntity;
    }

    public void setQuizEntity(QuizEntity quizEntity) {
        this.quizEntity = quizEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ResponseEntity> getResponseEntityList() {
        return responseEntityList;
    }

    public void setResponseEntityList(List<ResponseEntity> responseEntityList) {
        this.responseEntityList = responseEntityList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "quizEntity=" + quizEntity +
                ", content='" + content + '\'' +
                ", responseEntityList=" + responseEntityList +
                '}';
    }
}
