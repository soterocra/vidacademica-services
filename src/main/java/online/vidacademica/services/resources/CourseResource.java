package online.vidacademica.services.resources;

import java.net.URI;
import java.util.List;

import online.vidacademica.services.dto.CourseDTO;
import online.vidacademica.services.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

	@PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
	@GetMapping
	public ResponseEntity<List<CourseDTO>> findAll(){
		List<CourseDTO> dto = service.findAll();
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseDTO> findById(@PathVariable Long id){
		CourseDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
	@GetMapping(value = "/subject/{subjectId}")
	public ResponseEntity<Page<CourseDTO>> findBySubjectPaged(
			@PathVariable Long subjectId,
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC") String direction){

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
		Page<CourseDTO>dto = service.findBySubjectPaged(subjectId,pageRequest);
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

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PutMapping(value = "/{id}/addsubject")
	public ResponseEntity<Void> addSubject(@PathVariable Long id,@RequestBody SubjectDTO dto){
		service.addSubject(id, dto);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PutMapping(value = "/{id}/removesubject")
	public ResponseEntity<Void> removeSubject(@PathVariable Long id,@RequestBody SubjectDTO dto){
		service.removeSubject(id, dto);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('TEACHER')")
	@PutMapping(value = "/{id}/setsubject")
	public ResponseEntity<Void> setSubjects(@PathVariable Long id,@RequestBody List<SubjectDTO> dto){
		service.setSubject(id, dto);
		return ResponseEntity.noContent().build();
	}
}
