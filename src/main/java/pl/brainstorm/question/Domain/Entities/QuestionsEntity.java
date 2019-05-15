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

    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerEntity> answerEntityList;


    public QuestionsEntity() {
    }

    public QuestionsEntity( String content, List<AnswerEntity> answerEntityList) {
        this.content = content;
        this.answerEntityList = answerEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AnswerEntity> getAnswerEntityList() {
        return answerEntityList;
    }

    public void setAnswerEntityList(List<AnswerEntity> answerEntityList) {
        this.answerEntityList = answerEntityList;
    }

    @Override
    public String toString() {
        return "QuestionsEntity{" +
                "id=" + id +

                ", content='" + content + '\'' +
                ", answerEntityList=" + answerEntityList +
                '}';
    }
}
