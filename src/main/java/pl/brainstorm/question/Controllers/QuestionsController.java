package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brainstorm.question.Service.QuestionService;

@RestController
@RequestMapping("/Questions")
public class QuestionsController {
    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }
}
