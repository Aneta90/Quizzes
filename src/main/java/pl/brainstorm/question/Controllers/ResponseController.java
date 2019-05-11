package pl.brainstorm.question.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brainstorm.question.Service.ResponseService;

@RestController
@RequestMapping("/Response")
public class ResponseController {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }
}
