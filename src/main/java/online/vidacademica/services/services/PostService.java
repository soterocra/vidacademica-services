package online.vidacademica.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.vidacademica.services.entities.Post;
import online.vidacademica.services.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> obj = repository.findById(id);
		return obj.get();
	}
}
