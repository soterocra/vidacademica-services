package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.vidacademica.services.entities.Course;
import online.vidacademica.services.repositories.CourseRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	public List<Course> findAll() {
		return repository.findAll();
	}

	public Course findById(Long id) {
		Optional<Course> obj = repository.findById(id);
		return obj.get();
	}

	public Course insert(Course obj) {
		return repository.save(obj);

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
	public Course update(Long id, Course obj) {
		try {
			Course entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Course entity, Course obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setWorkload(obj.getWorkload());
		entity.setActive(obj.isActive());
		entity.setCreationDate(obj.getCreationDate());
	}
}
