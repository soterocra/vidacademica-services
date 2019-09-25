package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.repositories.SubjectRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repository;

	public List<Subject> findAll() {
		return repository.findAll();
	}

	public Subject findById(Long id) {
		Optional<Subject> obj = repository.findById(id);
		return obj.get();
	}

	public Subject insert(Subject obj) {
		return repository.save(obj);

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
	public Subject update(Long id, Subject obj) {
		try {
			Subject entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Subject entity, Subject obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setWorkload(obj.getWorkload());
		entity.setActive(obj.isActive());
		entity.setCreationDate(obj.getCreationDate());
		entity.setMinimumScore(obj.getMinimumScore());

	}
}
