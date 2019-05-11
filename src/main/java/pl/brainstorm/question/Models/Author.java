package pl.brainstorm.question.Models;

import java.util.List;

public class Author {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private List<Question> quizList;

    public Author() {
    }

    public Author(Long id, String name, String surname, String email, List<Question> quizList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.quizList = quizList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Question> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Question> quizList) {
        this.quizList = quizList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", quizList=" + quizList +
                '}';
    }
}
