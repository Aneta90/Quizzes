package pl.brainstorm.question.Models;

public class ChartEntry {

    String  name;
    int result;

    public ChartEntry() {
    }

    public ChartEntry(String name, int result) {
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

    @Override
    public String toString() {
        return "ChartEntry{" +
                "name='" + name + '\'' +
                ", result=" + result +
                '}';
    }
}
