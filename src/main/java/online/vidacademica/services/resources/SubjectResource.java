package online.vidacademica.services.resources;

import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectResource {

    @Autowired
    private SubjectService service;

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findAll() {
        List<SubjectDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<SubjectDTO> findById(@PathVariable Long id) {
        SubjectDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    @GetMapping(value = "/course/{courseId}")
    public ResponseEntity<List<SubjectDTO>> findByCourse(@PathVariable Long courseId) {
        List<SubjectDTO> dto = service.findByCourse(courseId);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping
    public ResponseEntity<SubjectDTO> insert(@Valid @RequestBody SubjectDTO dto) {
        SubjectDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('TEACHER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SubjectDTO> update(@PathVariable Long id, @Valid @RequestBody SubjectDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PutMapping(value = "/{id}/setteacher")
    public ResponseEntity<Void> setTeacher(@PathVariable Long id, @RequestBody UserDTO dto) {
        service.setTeacher(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value = "/teacher/{teacherId}")
    public ResponseEntity<List<SubjectDTO>> findByTeacherId(@PathVariable Long teacherId) {
        List<SubjectDTO> list = service.findByTeacherId(teacherId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mySubjects")
    public ResponseEntity<List<SubjectDTO>> findByClient() {
        List<SubjectDTO> list = service.findSubjectsByTeacher();
        return ResponseEntity.ok().body(list);
    }


}
