package online.vidacademica.services.resources;

import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.TestDTO;
import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.Test;
import online.vidacademica.services.services.PostService;
import online.vidacademica.services.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tests")
public class TestResource {

	@Autowired
	private TestService service;

	@PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
	@GetMapping
	public ResponseEntity<List<TestDTO>> findAll(){
		List<TestDTO> dto = service.findAll();
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TestDTO> findById(@PathVariable Long id){
		TestDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PostMapping
	public ResponseEntity<TestDTO> insert(@RequestBody TestDTO dto){
		TestDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TestDTO> update(@PathVariable Long id, @RequestBody TestDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
