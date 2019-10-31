package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.vidacademica.services.entities.City;
import online.vidacademica.services.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;

	public List<City> findAll(){
		return repository.findAll();
	}
	
	public City findById(Long id) {
		Optional<City> obj = repository.findById(id);
		return obj.get();
	}
}
