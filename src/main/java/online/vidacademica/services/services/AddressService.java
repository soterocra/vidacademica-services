package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.vidacademica.services.entities.Address;
import online.vidacademica.services.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;

	public List<Address> findAll(){
		return repository.findAll();
	}
	
	public Address findById(Long id) {
		Optional<Address> obj = repository.findById(id);
		return obj.get();
	}
}
