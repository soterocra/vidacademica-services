package online.vidacademica.services.services;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.dto.TestDTO;
import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import online.vidacademica.services.services.exceptions.UpdateDateTestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository repository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public List<TestDTO> findAll() {
        List<Test> list = repository.findAll();
        return list.stream().map(e -> new TestDTO(e)).collect(Collectors.toList());
    }

    public TestDTO findById(Long id) {
        Optional<Test> obj = repository.findById(id);
        Test entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return new TestDTO(entity);
    }

    public TestDTO insert(TestDTO dto) {
        Test entity = dto.toEntity();
        entity = repository.save(entity);
        return new TestDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Teste não pode ser excluido!");
        }
    }

    @Transactional
    public TestDTO update(Long id, TestDTO dto) {
        try {
            Test entity = repository.getOne(id);
            updateData(entity, dto);
            entity = repository.save(entity);
            return new TestDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(Test entity, TestDTO dto) {
        Date now = new Date();
        Date newDateTest = Date.from(dto.getDate());
        if (newDateTest.before(now)) {
            throw new UpdateDateTestException(entity.getId());

        } else {
            entity.setName(dto.getName());
            entity.setFullScore(dto.getFullScore());
            entity.setDate(dto.getDate());
            entity.setCreationDate(dto.getCreationDate());
        }
    }

    @Transactional
    public void setClass(Long id, ClasseDTO dto) {
        Classe classe = classRepository.getOne(dto.getId());
        Test test = repository.getOne(id);
        test.setClasse(classe);
        repository.save(test);

    }

    @Transactional(readOnly = true)
    public List<TestDTO> findByClassId(Long classId) {
        Classe classe = classRepository.getOne(classId);
        List<Test> list = repository.findByClasse(classe);

        return list.stream().map(e -> new TestDTO(e)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TestDTO> findTestsUserLogged() {
        User user = authService.authenticated();
        List<Test> list = repository.findByUser(user);

        return list.stream().map(e -> new TestDTO(e)).collect(Collectors.toList());
    }


}

