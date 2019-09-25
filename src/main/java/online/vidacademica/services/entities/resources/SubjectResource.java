package online.vidacademica.services.entities.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.vidacademica.services.entities.City;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.services.ClasseService;
import online.vidacademica.services.services.SubjectService;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectResource {

	@Autowired
	private SubjectService service;
	
	@GetMapping
	public ResponseEntity<List<Subject>> findAll(){
		List<Subject> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Subject> findById(@PathVariable Long id){
		Subject obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
