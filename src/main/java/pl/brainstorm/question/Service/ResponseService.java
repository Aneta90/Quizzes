package pl.brainstorm.question.Service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.brainstorm.question.Domain.Repositories.ResponseRepository;

public class ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }
}
