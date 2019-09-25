package online.vidacademica.services.entities.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.dto.UserInsertDTO;
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
	
	@PostMapping
	public ResponseEntity<Subject> insert(@RequestBody Subject obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody Subject obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
