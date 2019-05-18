package pl.brainstorm.question.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Answer {


    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    @JsonIgnore
    private Boolean isACorrect;
    @JsonIgnore
    private Boolean isBCorrect;
    @JsonIgnore
    private Boolean isCCorrect;
    @JsonIgnore
    private Boolean isDCorrect;

    private String answerFromUser;

    public Answer() {
    }


    public Answer(String answerA, String answerB, String answerC, String answerD,
                  Boolean isACorrect, Boolean isBCorrect, Boolean isCCorrect,
                  Boolean isDCorrect, String answerFromUser) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.isACorrect = isACorrect;
        this.isBCorrect = isBCorrect;
        this.isCCorrect = isCCorrect;
        this.isDCorrect = isDCorrect;
        this.answerFromUser = answerFromUser;
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

    public Boolean getACorrect() {
        return isACorrect;
    }

    public void setACorrect(Boolean ACorrect) {
        isACorrect = ACorrect;
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
        return "Answer{" +
                "answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", isACorrect=" + isACorrect +
                ", isBCorrect=" + isBCorrect +
                ", isCCorrect=" + isCCorrect +
                ", isDCorrect=" + isDCorrect +
                ", answerFromUser='" + answerFromUser + '\'' +
                '}';
    }
}
