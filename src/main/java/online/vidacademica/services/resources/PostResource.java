package online.vidacademica.services.resources;

import online.vidacademica.services.dto.InsertPostDTO;
import online.vidacademica.services.dto.PostDTO;
import online.vidacademica.services.entities.Post;
import online.vidacademica.services.repositories.PostRepository;
import online.vidacademica.services.services.AuthService;
import online.vidacademica.services.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;
    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping
    public ResponseEntity<InsertPostDTO> insertPost(@RequestBody InsertPostDTO dto) {
        InsertPostDTO newDto = service.insertPost(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping("/{idPostFather}/postComment")
    public ResponseEntity<PostDTO> insertComment(@PathVariable Long idPostFather, @RequestBody InsertPostDTO dto) {
        PostDTO newDto = service.insertPostComment(idPostFather,dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }




}
