package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.WeekEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekEntryRepository extends JpaRepository<WeekEntry, Long> {

    List<WeekEntry> findByClasse(Classe classe);

}
