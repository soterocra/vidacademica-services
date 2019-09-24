package online.vidacademica.services.entities.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.vidacademica.services.entities.State;
import online.vidacademica.services.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private StateService service;
	
	@GetMapping
	public ResponseEntity<List<State>> findAll(){
		List<State> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<State> findById(@PathVariable Long id){
		State obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
