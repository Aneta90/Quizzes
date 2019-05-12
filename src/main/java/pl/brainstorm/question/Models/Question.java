package pl.brainstorm.question.Models;

import java.util.List;

public class Question {

    private Quiz quiz;
    private String content;
    private List<Answer> answerList;

    public Question() {
    }

    public Question(Quiz quiz, String content, List<Answer> answerList) {
        this.quiz = quiz;
        this.content = content;
        this.answerList = answerList;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "quiz=" + quiz +
                ", content='" + content + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}
