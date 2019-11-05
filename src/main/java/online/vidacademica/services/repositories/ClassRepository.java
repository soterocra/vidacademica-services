package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Classe;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classe, Long> {
    List<Classe>  findBySubject(Subject subject);
}
