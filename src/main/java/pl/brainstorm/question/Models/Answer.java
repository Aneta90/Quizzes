package pl.brainstorm.question.Models;

public class Answer {

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private boolean aCorrect;
    private boolean isBCorrect;
    private boolean isCCorrect;
    private boolean isDCorrect;

    private String answerFromUser;

    public Answer() {
    }


    public Answer(String answerA, String answerB, String answerC, String answerD,
                  boolean isACorrect, boolean isBCorrect, boolean isCCorrect,
                  boolean isDCorrect, String answerFromUser) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.aCorrect = isACorrect;
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

    public boolean getACorrect() {
        return aCorrect;
    }

    public void setACorrect(boolean ACorrect) {
        aCorrect = ACorrect;
    }

    public boolean getBCorrect() {
        return isBCorrect;
    }

    public void setBCorrect(boolean BCorrect) {
        isBCorrect = BCorrect;
    }

    public boolean getCCorrect() {
        return isCCorrect;
    }

    public void setCCorrect(boolean CCorrect) {
        isCCorrect = CCorrect;
    }

    public boolean getDCorrect() {
        return isDCorrect;
    }

    public void setDCorrect(boolean DCorrect) {
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
                ", aCorrect=" + aCorrect +
                ", isBCorrect=" + isBCorrect +
                ", isCCorrect=" + isCCorrect +
                ", isDCorrect=" + isDCorrect +
                ", answerFromUser='" + answerFromUser + '\'' +
                '}';
    }
}
