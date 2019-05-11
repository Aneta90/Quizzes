package pl.brainstorm.question.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brainstorm.question.Service.QuizService;

@RestController
@RequestMapping("/Quiz")
public class QuizController {



    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
}
