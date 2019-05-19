package pl.brainstorm.question.Models;

import java.util.List;

public class Quiz {

    private String name;
    private List<Question> questionsList;
    private List<Long> totalScore;
    private int sizeOfQuestionList;
    private int numberOfSolved;

    public Quiz() {
    }


    public Quiz(String name, List<Question> questionsList,
                List<Long> totalScore, int sizeOfQuestionList, int numberOfSolved) {
        this.name = name;
        this.questionsList = questionsList;
        this.totalScore = totalScore;
        this.sizeOfQuestionList = sizeOfQuestionList;
        this.numberOfSolved = numberOfSolved;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
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
        return "Quiz{" +
                ", questionsList=" + questionsList +
                ", totalScore=" + totalScore +
                '}';
    }
}
