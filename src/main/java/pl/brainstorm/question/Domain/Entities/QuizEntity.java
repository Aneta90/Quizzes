package pl.brainstorm.question.Domain.Entities;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "Quiz")
@Entity
public class QuizEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AuthorEntity authorId;

    @OneToMany(mappedBy = "questions")
    private
    List<QuestionsEntity> questionsList;

    @Setter(AccessLevel.NONE)
    private Long totalScore;

    public QuizEntity() {
    }

    public QuizEntity(AuthorEntity authorId, List<QuestionsEntity> questionsList, Long totalScore) {
        this.authorId = authorId;
        this.questionsList = questionsList;
        this.totalScore = totalScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorEntity getAuthorId() {
        return authorId;
    }

    public void setAuthorId(AuthorEntity authorId) {
        this.authorId = authorId;
    }

    public List<QuestionsEntity> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionsEntity> questionsList) {
        this.questionsList = questionsList;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", questionsList=" + questionsList +
                ", totalScore=" + totalScore +
                '}';
    }
}
