package online.vidacademica.services.services;

import online.vidacademica.services.dto.TestDTO;
import online.vidacademica.services.dto.TestResultDTO;
import online.vidacademica.services.dto.TestResultGetResultsDTO;
import online.vidacademica.services.dto.TestResultInsertDTO;
import online.vidacademica.services.entities.Test;
import online.vidacademica.services.entities.TestResult;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.TestRepository;
import online.vidacademica.services.repositories.TestResultRepository;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.services.exceptions.PostScoreException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TestResultService {

    @Autowired
    private TestResultRepository repository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TestDTO postScore(TestResultInsertDTO dto) {
        User student = userRepository.getOne(dto.getUserId());
        Test test = testRepository.getOne(dto.getTestId());

        if (dto.getScore() > test.getFullScore()){
            throw  new PostScoreException(dto.getTestId());
        }else if(dto.getScore()< 0){
            throw  new PostScoreException("Note entered is invalid.");
        }else{
            TestResult result = new TestResult(student, test, dto.getScore(),Instant.now());
            repository.saveAll(Arrays.asList(result));
        }
       return new TestDTO(test);
  }



}
