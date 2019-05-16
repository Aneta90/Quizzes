package pl.brainstorm.question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.brainstorm.question.Domain.Repositories.AuthorRepository;
import pl.brainstorm.question.Service.AuthorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionApplicationTests { //integracyjne testy

	@Autowired
	private AuthorService service;

	private AuthorRepository repository;

	@Test
	public void contextLoads() {
		///repository.save()

	}

}
