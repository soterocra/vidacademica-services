package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByClasse(Classe classe);
}
