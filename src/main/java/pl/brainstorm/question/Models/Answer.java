package pl.brainstorm.question.Models;

public class Answer {

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private boolean aCorrect;
    private boolean bCorrect;
    private boolean cCorrect;
    private boolean dCorrect;

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
        this.bCorrect = isBCorrect;
        this.cCorrect = isCCorrect;
        this.dCorrect = isDCorrect;
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
        return "Answer{" +
                "answerA='" + answerA + '\'' +
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
