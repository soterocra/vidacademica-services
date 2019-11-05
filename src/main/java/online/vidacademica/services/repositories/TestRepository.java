package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByClasse(Classe classe);
}
