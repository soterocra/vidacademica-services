package online.vidacademica.services.resources;

import online.vidacademica.services.dto.*;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/class")
public class ClassResource {

    @Autowired
    private ClasseService service;

    @GetMapping
    public ResponseEntity<List<Classe>> findAll() {
        List<Classe> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClasseDTO> findById(@PathVariable Long id) {
        ClasseDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> insert(@RequestBody ClasseDTO dto) {
        ClasseDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClasseDTO> update(@PathVariable Long id, @RequestBody ClasseDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PutMapping(value = "/{id}/setsubject")
    public ResponseEntity<Void> setSubject(@PathVariable Long id, @RequestBody SubjectDTO dto) {
        service.setSubject(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value = "/subject/{subjectId}")
    public ResponseEntity<List<ClasseDTO>> findBySubjectId(@PathVariable Long subjectId) {
        List<ClasseDTO> list = service.findBySubjectId(subjectId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{timeTableId}/timebox")
    public ResponseEntity<List<TimesDTO>> timeboxes(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @PathVariable Long timeTableId) {
        if (endDate == null) {
            endDate = startDate;
        }
        LocalDate d1 = LocalDate.parse(startDate);
        LocalDate d2 = LocalDate.parse(endDate);
        List<TimesDTO> list = service.times(timeTableId, d1, d2);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/weekentry")
    public ResponseEntity<WeekEntryDTO> timeboxes(@RequestBody WeekEntryDTO dto) {
        WeekEntryDTO newDto = service.addWeekEntry(dto);
        return ResponseEntity.ok().body(newDto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/attach-student")
    public ResponseEntity<Void> attachStudent(@RequestBody RegistrationDTO dto) {
        service.atachStudent(dto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value = "/subject/{registrationId}")
    public ResponseEntity<List<ClasseDTO>> findByRegistrationId(@PathVariable Long registrationId) {
        List<ClasseDTO> list = service.findByRegistrationId(registrationId);
        return ResponseEntity.ok().body(list);
    }

}
