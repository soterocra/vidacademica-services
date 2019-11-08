package online.vidacademica.services.resources;

import online.vidacademica.services.entities.Phone;
import online.vidacademica.services.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/phones")
public class PhoneResource {

    @Autowired
    private PhoneService service;

    @GetMapping
    public ResponseEntity<List<Phone>> findAll() {
        List<Phone> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> findById(@PathVariable Long id) {
        Phone obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
