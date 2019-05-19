package pl.brainstorm.question.Domain.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Answer")
@Entity
public class AnswerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private Boolean aCorrect;
    private Boolean isBCorrect;
    private Boolean isCCorrect;
    private Boolean isDCorrect;

    private String answerFromUser;

    public AnswerEntity() {
    }

    public AnswerEntity(String answerA, String answerB, String answerC,
                        String answerD, Boolean ACorrect, Boolean isBCorrect,
                        Boolean isCCorrect, Boolean isDCorrect, String answerFromUser) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.aCorrect = ACorrect;
        this.isBCorrect = isBCorrect;
        this.isCCorrect = isCCorrect;
        this.isDCorrect = isDCorrect;
        this.answerFromUser = answerFromUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public Boolean getaCorrect() {
        return aCorrect;
    }

    public void setaCorrect(Boolean aCorrect) {
        this.aCorrect = aCorrect;
    }

    public Boolean getBCorrect() {
        return isBCorrect;
    }

    public void setBCorrect(Boolean BCorrect) {
        isBCorrect = BCorrect;
    }

    public Boolean getCCorrect() {
        return isCCorrect;
    }

    public void setCCorrect(Boolean CCorrect) {
        isCCorrect = CCorrect;
    }

    public Boolean getDCorrect() {
        return isDCorrect;
    }

    public void setDCorrect(Boolean DCorrect) {
        isDCorrect = DCorrect;
    }

    public String getAnswerFromUser() {
        return answerFromUser;
    }

    public void setAnswerFromUser(String answerFromUser) {
        this.answerFromUser = answerFromUser;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", aCorrect=" + aCorrect +
                ", isBCorrect=" + isBCorrect +
                ", isCCorrect=" + isCCorrect +
                ", isDCorrect=" + isDCorrect +
                ", answerFromUser='" + answerFromUser + '\'' +
                '}';
    }
}
