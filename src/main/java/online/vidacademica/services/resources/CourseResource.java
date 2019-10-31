package online.vidacademica.services.resources;

import java.net.URI;
import java.util.List;

import online.vidacademica.services.dto.CourseDTO;
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

import online.vidacademica.services.entities.City;
import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.services.CityService;
import online.vidacademica.services.services.CourseService;

@RestController
@RequestMapping(value = "/courses")
public class CourseResource {

	@Autowired
	private CourseService service;
	
	@GetMapping
	public ResponseEntity<List<CourseDTO>> findAll(){
		List<CourseDTO> dto = service.findAll();
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseDTO> findById(@PathVariable Long id){
		CourseDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PreAuthorize("hasAnyRole('TEACHER')")
	@PostMapping
	public ResponseEntity<CourseDTO> insert(@RequestBody CourseDTO dto){
		CourseDTO newDto = service.insert(dto);
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
	public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
