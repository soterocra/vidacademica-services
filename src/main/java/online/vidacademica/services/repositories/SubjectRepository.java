package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByUser(User user);

}
