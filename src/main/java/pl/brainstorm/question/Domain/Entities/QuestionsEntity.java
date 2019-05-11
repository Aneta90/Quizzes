package pl.brainstorm.question.Domain.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "Questions")
@Entity
public class QuestionsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private QuizEntity quizEntity;
    private String content;

    @OneToMany
    private List<ResponseEntity> responseEntityList;


    public QuestionsEntity() {
    }

    public QuestionsEntity(QuizEntity quizEntity, String content, List<ResponseEntity> responseEntityList) {
        this.quizEntity = quizEntity;
        this.content = content;
        this.responseEntityList = responseEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "QuestionsEntity{" +
                "id=" + id +
                ", quizEntity=" + quizEntity +
                ", content='" + content + '\'' +
                ", responseEntityList=" + responseEntityList +
                '}';
    }
}
