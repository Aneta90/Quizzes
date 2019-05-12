package pl.brainstorm.question.Domain.Entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Table(name = "Author")
@Entity
public class AuthorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @Email
    private String email;

    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL)
    private List <QuizEntity> quizEntityList;
    private Long quizListSize;
    public AuthorEntity() {
    }

    public AuthorEntity(String name, String surname, @Email String email,
                        List<QuizEntity> quizEntityList, Long quizListSize) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.quizEntityList = quizEntityList;
        this.quizListSize = quizListSize;
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

    public List<QuizEntity> getQuizEntityList() {
        return quizEntityList;
    }

    public void setQuizEntityList(List<QuizEntity> quizEntityList) {
        this.quizEntityList = quizEntityList;
    }

    public Long getQuizListSize() {
        return quizListSize;
    }

    public void setQuizListSize(Long quizListSize) {
        this.quizListSize = quizListSize;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", quizEntityList=" + quizEntityList +
                '}';
    }
}
