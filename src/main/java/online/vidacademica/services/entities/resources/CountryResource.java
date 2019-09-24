package online.vidacademica.services.entities.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.vidacademica.services.entities.Country;
import online.vidacademica.services.services.CountryService;

@RestController
@RequestMapping(value = "/countries")
public class CountryResource {

	@Autowired
	private CountryService service;
	
	@GetMapping
	public ResponseEntity<List<Country>> findAll(){
		List<Country> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Country> findById(@PathVariable Long id){
		Country obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
