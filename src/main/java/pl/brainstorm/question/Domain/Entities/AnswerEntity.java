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

    private boolean aCorrect;
    private boolean bCorrect;
    private boolean cCorrect;
    private boolean dCorrect;

    private String answerFromUser;

    public AnswerEntity() {
    }

    public AnswerEntity(String answerA, String answerB, String answerC, String answerD, boolean aCorrect,
                        boolean bCorrect, boolean cCorrect, boolean dCorrect, String answerFromUser) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.aCorrect = aCorrect;
        this.bCorrect = bCorrect;
        this.cCorrect = cCorrect;
        this.dCorrect = dCorrect;
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

    public boolean isaCorrect() {
        return aCorrect;
    }

    public void setaCorrect(boolean aCorrect) {
        this.aCorrect = aCorrect;
    }

    public boolean isbCorrect() {
        return bCorrect;
    }

    public void setbCorrect(boolean bCorrect) {
        this.bCorrect = bCorrect;
    }

    public boolean iscCorrect() {
        return cCorrect;
    }

    public void setcCorrect(boolean cCorrect) {
        this.cCorrect = cCorrect;
    }

    public boolean isdCorrect() {
        return dCorrect;
    }

    public void setdCorrect(boolean dCorrect) {
        this.dCorrect = dCorrect;
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
                ", bCorrect=" + bCorrect +
                ", cCorrect=" + cCorrect +
                ", dCorrect=" + dCorrect +
                ", answerFromUser='" + answerFromUser + '\'' +
                '}';
    }
}
