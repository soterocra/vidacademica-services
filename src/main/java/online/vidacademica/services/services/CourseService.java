package online.vidacademica.services.services;

import online.vidacademica.services.dto.CourseDTO;
import online.vidacademica.services.entities.Course;
import online.vidacademica.services.repositories.CourseRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

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
		entity.setCreationDate(dto.getCreationDate());
	}
}
