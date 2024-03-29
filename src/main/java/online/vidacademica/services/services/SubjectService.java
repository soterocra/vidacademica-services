package online.vidacademica.services.services;

import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.ClassRepository;
import online.vidacademica.services.repositories.CourseRepository;
import online.vidacademica.services.repositories.SubjectRepository;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.AddUserToSubjectException;
import online.vidacademica.services.services.exceptions.JWTAuthenticationException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private AuthService authservice;

    public List<SubjectDTO> findAll() {
        List<Subject> list = repository.findAll();
        return list.stream().map(e -> new SubjectDTO(e)).collect(Collectors.toList());
    }

    public SubjectDTO findById(Long id) {
        Optional<Subject> obj = repository.findById(id);
        Subject entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return new SubjectDTO(entity);
    }

    public SubjectDTO insert(SubjectDTO dto) {
        Subject entity = dto.toEntity();
        entity.setCreationDate(Instant.now());
        entity = repository.save(entity);
        return new SubjectDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Matéria não pode ser excluida!");
        }
    }

    @Transactional
    public SubjectDTO update(Long id, SubjectDTO dto) {
        try {
            Subject entity = repository.getOne(id);
            updateData(entity, dto);
            entity = repository.save(entity);
            return new SubjectDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(Subject entity, SubjectDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setWorkload(dto.getWorkload());
        entity.setActive(dto.isActive());
        entity.setMinimumScore(dto.getMinimumScore());

    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> findByCourse(Long productId) {
        Course course = courseRepository.getOne(productId);
        Set<Subject> set = course.getSubjects();
        return set.stream().map(e -> new SubjectDTO(e)).collect(Collectors.toList());
    }


    @Transactional
    public void setTeacher(Long id, UserDTO dto) {
        User user = userRepository.getOne(dto.getId());
        if (user.hasRole("ROLE_STUDENT")) {
            throw new AddUserToSubjectException("User is Student");
        } else {
            Subject subject = repository.getOne(id);
            subject.setTeacher(user);
            repository.save(subject);
        }

    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> findByTeacherId(Long teacherId) {
        User teacher = userRepository.getOne(teacherId);
        List<Subject> list = repository.findByTeacher(teacher);

        return list.stream().map(e -> new SubjectDTO(e)).collect(Collectors.toList());
    }

    public List<SubjectDTO> findSubjectsByTeacher() {
        User user = authservice.authenticated();
        if (user.hasRole("ROLE_STUDENT")) {
            throw new JWTAuthenticationException("Access Denied");
        } else {

            List<Subject> list = repository.findByTeacher(user);

            return list.stream().map(e -> new SubjectDTO(e)).collect(Collectors.toList());
        }

    }
}

