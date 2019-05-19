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
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionsEntity> questionsList;

    @Setter(AccessLevel.NONE)
    @ElementCollection
    private List<Long> totalScore;

    private int sizeOfQuestionList;
    private int numberOfSolved;

    public QuizEntity() {
    }

    public QuizEntity(String name, List<QuestionsEntity> questionsList,
                      List<Long> totalScore, int sizeOfQuestionList, int numberOfSolved) {
        this.name = name;
        this.questionsList = questionsList;
        this.totalScore = totalScore;
        this.sizeOfQuestionList = sizeOfQuestionList;
        this.numberOfSolved = numberOfSolved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QuestionsEntity> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionsEntity> questionsList) {
        this.questionsList = questionsList;
    }

    public List<Long> getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(List<Long> totalScore) {
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSizeOfQuestionList() {
        return sizeOfQuestionList;
    }

    public void setSizeOfQuestionList(int sizeOfQuestionList) {
        this.sizeOfQuestionList = sizeOfQuestionList;
    }

    public int getNumberOfSolved() {
        return numberOfSolved;
    }

    public void setNumberOfSolved(int numberOfSolved) {
        this.numberOfSolved = numberOfSolved;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", questionsList=" + questionsList +
                ", totalScore=" + totalScore +
                '}';
    }
}
