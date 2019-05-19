package pl.brainstorm.question.Models;

public class AuthorChart {

    String name;
    int result;

    public AuthorChart() {
    }

    public AuthorChart(String name, int result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
