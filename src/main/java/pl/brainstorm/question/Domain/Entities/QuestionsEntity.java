package pl.brainstorm.question.Domain.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Questions")
@Entity
public class QuestionsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    private AnswerEntity answerEntity;

    public QuestionsEntity() {
    }

    public QuestionsEntity(String content, AnswerEntity answerEntity) {
        this.content = content;
        this.answerEntity = answerEntity;
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

    public AnswerEntity getAnswerEntity() {
        return answerEntity;
    }

    public void setAnswerEntity(AnswerEntity answerEntity) {
        this.answerEntity = answerEntity;
    }

    @Override
    public String toString() {
        return "QuestionsEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", answerEntity=" + answerEntity +
                '}';
    }
}
