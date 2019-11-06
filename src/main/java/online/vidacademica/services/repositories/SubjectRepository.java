package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByTeacher(User teacher);

}
