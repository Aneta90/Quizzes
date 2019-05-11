package pl.brainstorm.question.Models;

import java.util.List;

public class Quiz {


    private Author author;
    private List<Question> questionsList;
    private Long totalScore;

    public Quiz() {
    }

    public Quiz(Author author, List<Question> questionsList, Long totalScore) {
        this.author = author;
        this.questionsList = questionsList;
        this.totalScore = totalScore;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
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
        return "Quiz{" +
                "author=" + author +
                ", questionsList=" + questionsList +
                ", totalScore=" + totalScore +
                '}';
    }
}
