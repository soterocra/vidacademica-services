package online.vidacademica.services.resources;

import java.net.URI;
import java.util.List;

import online.vidacademica.services.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.services.ClasseService;

@RestController
@RequestMapping(value = "/class")
public class ClassResource {

	@Autowired
	private ClasseService service;
	
	@GetMapping
	public ResponseEntity<List<Classe>> findAll(){
		List<Classe>list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClasseDTO> findById(@PathVariable Long id){
		ClasseDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClasseDTO> insert(@RequestBody ClasseDTO dto){
		ClasseDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClasseDTO> update(@PathVariable Long id, @RequestBody ClasseDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PutMapping(value = "/{id}/setsubject")
	public ResponseEntity<Void> setSubject(@PathVariable Long id,@RequestBody SubjectDTO dto){
		service.setSubject(id, dto);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@GetMapping(value = "/subject/{subjectId}")
	public ResponseEntity<List<ClasseDTO>> findBySubjectId(@PathVariable Long subjectId) {
		List<ClasseDTO> list = service.findBySubjectId(subjectId);
		return ResponseEntity.ok().body(list);
	}





}
