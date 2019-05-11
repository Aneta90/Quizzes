package pl.brainstorm.question.Models;

import pl.brainstorm.question.Domain.Entities.QuestionsEntity;

public class Response {

    private QuestionsEntity questions;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private Boolean isACorrect;
    private Boolean isBCorrect;
    private Boolean isCCorrect;
    private Boolean isDCorrect;

    public Response() {
    }

    public Response(QuestionsEntity questions, String answerA, String answerB, String answerC, String answerD, Boolean isACorrect, Boolean isBCorrect, Boolean isCCorrect, Boolean isDCorrect) {
        this.questions = questions;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.isACorrect = isACorrect;
        this.isBCorrect = isBCorrect;
        this.isCCorrect = isCCorrect;
        this.isDCorrect = isDCorrect;
    }

    public QuestionsEntity getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionsEntity questions) {
        this.questions = questions;
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

    @Override
    public String toString() {
        return "Response{" +
                "questions=" + questions +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", isACorrect=" + isACorrect +
                ", isBCorrect=" + isBCorrect +
                ", isCCorrect=" + isCCorrect +
                ", isDCorrect=" + isDCorrect +
                '}';
    }
}
