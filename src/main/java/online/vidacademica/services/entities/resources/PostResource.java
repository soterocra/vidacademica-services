package online.vidacademica.services.entities.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.vidacademica.services.entities.Post;
import online.vidacademica.services.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable Long id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
