package pl.brainstorm.question.Models;

public class Question {

    private String content;
    private Answer answer;

    public Question() {
    }

    public Question(String content, Answer answer) {
        this.content = content;
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", answer=" + answer +
                '}';
    }
}
