package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import jdk.jfr.Category;
import online.vidacademica.services.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
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

	public List<SubjectDTO> findAll() {
		List<Subject> list =  repository.findAll();
		return list.stream().map(e -> new SubjectDTO(e)).collect(Collectors.toList());
	}

	public SubjectDTO findById(Long id) {
		Optional<Subject> obj = repository.findById(id);
		Subject entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new SubjectDTO(entity);
	}

	public SubjectDTO insert(SubjectDTO dto) {
		Subject entity = dto.toEntity();
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
			entity =  repository.save(entity);
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
		entity.setCreationDate(dto.getCreationDate());
		entity.setMinimumScore(dto.getMinimumScore());

	}
}
