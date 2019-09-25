package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.ClassRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;

@Service
public class ClasseService {
	
	@Autowired
	private ClassRepository repository;

	public List<ClasseDTO> findAll() {
		List<Classe> list = repository.findAll();
		return list.stream().map(e -> new ClasseDTO(e)).collect(Collectors.toList());
	}
	
	
	public ClasseDTO findById(Long id) {
		Optional<Classe> obj = repository.findById(id);
		Classe entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ClasseDTO(entity);
	}
	
	public ClasseDTO insert(ClasseDTO dto) {
		Classe entity = dto.toEntity();
		entity = repository.save(entity);
		return new ClasseDTO(entity);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e ) {
			throw new DatabaseException("Classe não pode ser excluido!");
		}
	}
	
	public ClasseDTO update(Long id,ClasseDTO dto) {
		try {
			Classe entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new ClasseDTO(entity);
			} catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}	
	}

	private void updateData(Classe entity, ClasseDTO dto) {
		entity.setName(dto.getName());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setActive(dto.isActive());
		entity.setCreationDate(dto.getCreationDate());
		Subject subject = new Subject();
		subject.setId(dto.getSubjectId());
		entity.setSubject(subject);
		
	}
}
