package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
