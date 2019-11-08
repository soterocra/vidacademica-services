package online.vidacademica.services.services;

import online.vidacademica.services.dto.PostDTO;
import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.entities.enums.PostType;
import online.vidacademica.services.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private AuthService authService;
    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> obj = repository.findById(id);
        return obj.get();
    }

    public PostDTO insertPost(PostDTO dto) {
        User user = authService.authenticated();

        Post entity = dto.toEntity();
        entity.setPostType(PostType.POST);
        entity.setAuthor(user);
        entity = repository.save(entity);
        return new PostDTO(entity);
    }



}
