package pl.brainstorm.question.Models;

import java.util.List;

public class Question {

    private Quiz quiz;
    private String content;
    private List<Response> responseList;

    public Question() {
    }

    public Question(Quiz quiz, String content, List<Response> responseList) {
        this.quiz = quiz;
        this.content = content;
        this.responseList = responseList;
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

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "quiz=" + quiz +
                ", content='" + content + '\'' +
                ", responseList=" + responseList +
                '}';
    }
}
