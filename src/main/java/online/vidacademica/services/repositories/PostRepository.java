package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
