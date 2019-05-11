package pl.brainstorm.question.Models;

public class User {

    private Long totalScore;

    public User(Long totalScore) {
        this.totalScore = totalScore;
    }

    public User() {
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }
}
