package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.vidacademica.services.entities.Phone;
import online.vidacademica.services.repositories.PhoneRepository;

@Service
public class PhoneService {
	
	@Autowired
	private PhoneRepository repository;

	public List<Phone> findAll(){
		return repository.findAll();
	}
	
	public Phone findById(Long id) {
		Optional<Phone> obj = repository.findById(id);
		return obj.get();
	}
}
