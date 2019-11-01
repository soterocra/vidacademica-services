package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;


import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.dto.UserInsertDTO;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	

	
	@Autowired
	private UserRepository repository;

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public List<UserDTO> findAll() {
		List<User> list = repository.findAll();
		return list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
	}
	
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserInsertDTO dto) {
		User entity = dto.toEntity();
		entity.setPassword(passwordEncode.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
		User entity = repository.getOne(id);
		updateData(entity, dto);
		entity =  repository.save(entity);
		return new UserDTO(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, UserDTO dto) {
		entity.setName(dto.getName());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setSocialId(dto.getSocialId());
		entity.setRegistration(dto.getRegistration());
	}

	@Transactional(readOnly = true)
	public List<UserDTO> findBySubject(Long productId) {
		Subject subject = subjectRepository.getOne(productId);
		Set<User> set = subject.getUser();
		return set.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}
