package pl.brainstorm.question.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brainstorm.question.Service.AuthorService;

@RestController
@RequestMapping("/Author")
public class AuthorController {



    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
}
