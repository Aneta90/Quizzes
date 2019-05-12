package pl.brainstorm.question.Models;

import java.util.List;

public class Author {

    private String name;
    private String surname;
    private String email;
    private List<Quiz> quizList;
    private Long quizListSize;

    public Author() {
    }

    public Author(String name, String surname, String email, List<Quiz> quizList, Long quizListSize) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.quizList = quizList;
        this.quizListSize = quizListSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Long getQuizListSize() {
        return quizListSize;
    }

    public void setQuizListSize(Long quizListSize) {
        this.quizListSize = quizListSize;
    }

    @Override
    public String toString() {
        return "Author{" +

                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", quizList=" + quizList +
                '}';
    }
}
