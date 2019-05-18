package pl.brainstorm.question.Service;


import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AnswerEntity;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Models.Answer;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    public Author map(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setEmail(authorEntity.getEmail());
        author.setName(authorEntity.getName());
        author.setSurname(authorEntity.getSurname());
        author.setQuizListSize(authorEntity.getQuizListSize());
        List<Quiz> quizList = new ArrayList<>();
        for (int i = 0; i < authorEntity.getQuizEntityList().size(); i++) {
            quizList.add(map(authorEntity.getQuizEntityList().get(i)));
        }
        author.setQuizList(quizList);
        return author;
    }

    public AuthorEntity map(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setEmail(author.getEmail());
        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setQuizListSize(author.getQuizListSize());
        List<QuizEntity> quizEntityList = new ArrayList<>();
        for (int i = 0; i < author.getQuizList().size(); i++) {
            quizEntityList.add(map(author.getQuizList().get(i)));
        }
        authorEntity.setQuizEntityList(quizEntityList);

        return authorEntity;
    }

    public Question map(QuestionsEntity questionsEntity) {
        Question question = new Question();
        question.setContent(questionsEntity.getContent());

        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < questionsEntity.getAnswerEntityList().size(); i++) {
            answerList.add(map(questionsEntity.getAnswerEntityList().get(i)));
        }
        question.setAnswerList(answerList);
        return question;
    }

    public QuestionsEntity map(Question question) {
        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent(question.getContent());

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (int i = 0; i < question.getAnswerList().size(); i++) {
            answerEntityList.add(map(question.getAnswerList().get(i)));
        }
        questionsEntity.setAnswerEntityList(answerEntityList);
        return questionsEntity;
    }

    public Quiz map(QuizEntity quizEntity) {
        Quiz quiz = new Quiz();
        quiz.setName(quizEntity.getName());
        quiz.setNumberOfSolved(quizEntity.getNumberOfSolved());
        quiz.setSizeOfQuestionList(quizEntity.getSizeOfQuestionList());
        quiz.setTotalScore(quizEntity.getTotalScore());

        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < quizEntity.getQuestionsList().size(); i++) {
            questionList.add(map(quizEntity.getQuestionsList().get(i)));
        }
        quiz.setQuestionsList(questionList);

        return quiz;
    }

    public QuizEntity map(Quiz quiz) {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setName(quiz.getName());
        quizEntity.setTotalScore(quiz.getTotalScore());
        quizEntity.setSizeOfQuestionList(quiz.getSizeOfQuestionList());
        quizEntity.setNumberOfSolved(quiz.getNumberOfSolved());

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        for (int i = 0; i < quiz.getQuestionsList().size(); i++) {
            questionsEntityList.add(map(quiz.getQuestionsList().get(i)));

        }
        quizEntity.setQuestionsList(questionsEntityList);
        return quizEntity;
    }

    public Answer map(AnswerEntity answerEntity) {
        Answer answer = new Answer();
        answer.setAnswerA(answerEntity.getAnswerA());
        answer.setAnswerB(answerEntity.getAnswerB());
        answer.setAnswerC(answerEntity.getAnswerC());
        answer.setAnswerD(answerEntity.getAnswerD());
        answer.setACorrect(answerEntity.getACorrect());
        answer.setBCorrect(answerEntity.getBCorrect());
        answer.setCCorrect(answerEntity.getCCorrect());
        answer.setDCorrect(answerEntity.getDCorrect());
        answer.setAnswerFromUser(answerEntity.getAnswerFromUser());
        return answer;
    }

    public AnswerEntity map(Answer answer) {

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerA(answer.getAnswerA());
        answerEntity.setACorrect(answer.getACorrect());
        answerEntity.setAnswerB(answer.getAnswerB());
        answerEntity.setBCorrect(answer.getBCorrect());
        answerEntity.setAnswerC(answer.getAnswerC());
        answerEntity.setCCorrect(answer.getCCorrect());
        answerEntity.setAnswerD(answer.getAnswerD());
        answerEntity.setDCorrect(answer.getDCorrect());
        answerEntity.setAnswerFromUser(answer.getAnswerFromUser());
        return answerEntity;
    }

}
