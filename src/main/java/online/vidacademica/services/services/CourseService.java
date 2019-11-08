package online.vidacademica.services.services;

import online.vidacademica.services.dto.CourseDTO;
import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.CourseRepository;
import online.vidacademica.services.repositories.SubjectRepository;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.AddUserToSubjectException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CourseDTO> findAll() {
        List<Course> list = repository.findAll();
        return list.stream().map(e -> new CourseDTO(e)).collect(Collectors.toList());
    }

    public CourseDTO findById(Long id) {
        Optional<Course> obj = repository.findById(id);
        Course entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return new CourseDTO(entity);
    }

    public CourseDTO insert(CourseDTO dto) {
        Course entity = dto.toEntity();
        entity.setCreationDate(Instant.now());
        entity = repository.save(entity);
        return new CourseDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Curso não pode ser excluido!");
        }
    }

    @Transactional
    public CourseDTO update(Long id, CourseDTO dto) {
        try {
            Course entity = repository.getOne(id);
            updateData(entity, dto);
            entity = repository.save(entity);
            return new CourseDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Course entity, CourseDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setWorkload(dto.getWorkload());
        entity.setActive(dto.isActive());
    }

    public Page<CourseDTO> findBySubjectPaged(Long subjectId, Pageable pageable) {
        Subject subject = subjectRepository.getOne(subjectId);
        Page<Course> courses = repository.findBySubject(subject, pageable);
        return courses.map(e -> new CourseDTO(e));
    }

    private void setCourseSubject(Course entity, List<SubjectDTO> subjects) {
        entity.getSubjects().clear();
        for (SubjectDTO dto : subjects) {
            Subject subject = subjectRepository.getOne(dto.getId());
            entity.getSubjects().add(subject);
        }
    }

    @Transactional
    public void addSubject(Long id, SubjectDTO dto) {
        Course course = repository.getOne(id);
        Subject subject = subjectRepository.getOne(dto.getId());
        course.getSubjects().add(subject);
        repository.save(course);
    }

    @Transactional
    public void removeSubject(Long id, SubjectDTO dto) {
        Course course = repository.getOne(id);
        Subject subject = subjectRepository.getOne(dto.getId());
        course.getSubjects().remove(subject);
        repository.save(course);
    }

    @Transactional
    public void setSubject(Long id, List<SubjectDTO> dto) {
        Course course = repository.getOne(id);
        setCourseSubject(course, dto);
        repository.save(course);
    }

    @Transactional
    public void setOwner(Long id, UserDTO dto) {
        User user = userRepository.getOne(dto.getId());
        if (user.hasRole("ROLE_STUDENT")) {
            throw new AddUserToSubjectException("User is Student");
        } else {
            Course course = repository.getOne(id);
            course.setCommander(user);
            repository.save(course);
        }
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findByOwnerId(Long ownerId) {
        User owner = userRepository.getOne(ownerId);
        List<Course> list = repository.findByCommander(owner);

        return list.stream().map(e -> new CourseDTO(e)).collect(Collectors.toList());
    }
}
