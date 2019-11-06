package online.vidacademica.services.resources;

import online.vidacademica.services.dto.TestDTO;
import online.vidacademica.services.dto.TestResultDTO;
import online.vidacademica.services.dto.TestResultInsertDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.entities.TestResult;
import online.vidacademica.services.services.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/test_results")
public class TestResultResource {

    @Autowired
    private TestResultService service;

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping
    public ResponseEntity<TestDTO> postScore(@RequestBody TestResultInsertDTO dto) {
        TestDTO testDTO = service.postScore(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(testDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(testDTO);
    }




}
