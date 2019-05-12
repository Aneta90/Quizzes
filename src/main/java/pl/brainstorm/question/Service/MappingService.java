package pl.brainstorm.question.Service;


import org.springframework.stereotype.Service;
import pl.brainstorm.question.Domain.Entities.AuthorEntity;
import pl.brainstorm.question.Domain.Entities.QuestionsEntity;
import pl.brainstorm.question.Domain.Entities.QuizEntity;
import pl.brainstorm.question.Domain.Entities.ResponseEntity;
import pl.brainstorm.question.Models.Author;
import pl.brainstorm.question.Models.Question;
import pl.brainstorm.question.Models.Quiz;
import pl.brainstorm.question.Models.Response;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    public Author map(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setEmail(authorEntity.getEmail());
        author.setName(authorEntity.getName());
        author.setSurname(authorEntity.getSurname());

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
        question.setQuiz(map(questionsEntity.getQuizEntity()));

        List<Response> responseList = new ArrayList<>();
        for (int i = 0; i < questionsEntity.getResponseEntityList().size(); i++) {
            responseList.add(map(questionsEntity.getResponseEntityList().get(i)));
        }
        question.setResponseList(responseList);
        return question;
    }

    public QuestionsEntity map(Question question) {
        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setContent(question.getContent());
        questionsEntity.setQuizEntity(map(question.getQuiz()));

        List<ResponseEntity> responseEntityList = new ArrayList<>();
        for (int i = 0; i < question.getResponseList().size(); i++) {
            responseEntityList.add(map(question.getResponseList().get(i)));
        }
        questionsEntity.setResponseEntityList(responseEntityList);
        return questionsEntity;
    }

    public Quiz map(QuizEntity quizEntity) {
        Quiz quiz = new Quiz();
        quiz.setAuthor(map(quizEntity.getAuthorId()));
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
        quizEntity.setTotalScore(quiz.getTotalScore());
        quizEntity.setSizeOfQuestionList(quiz.getSizeOfQuestionList());
        quizEntity.setNumberOfSolved(quiz.getNumberOfSolved());
        quizEntity.setAuthorId(map(quiz.getAuthor()));

        List<QuestionsEntity> questionsEntityList = new ArrayList<>();
        for (int i = 0; i < quiz.getQuestionsList().size(); i++) {
            questionsEntityList.add(map(quiz.getQuestionsList().get(i)));

        }
        quizEntity.setQuestionsList(questionsEntityList);
        return quizEntity;
    }

    public Response map(ResponseEntity responseEntity) {
        Response response = new Response();
        response.setAnswerA(responseEntity.getAnswerA());
        response.setAnswerB(responseEntity.getAnswerB());
        response.setAnswerC(responseEntity.getAnswerC());
        response.setAnswerD(responseEntity.getAnswerD());
        response.setACorrect(responseEntity.getACorrect());
        response.setBCorrect(responseEntity.getBCorrect());
        response.setCCorrect(responseEntity.getCCorrect());
        response.setDCorrect(responseEntity.getDCorrect());
        response.setQuestions(map(responseEntity.getQuestions()));
        return response;
    }

    public ResponseEntity map(Response response) {
        return null;
    }

}
