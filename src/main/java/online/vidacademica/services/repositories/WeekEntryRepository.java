package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.WeekEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekEntryRepository extends JpaRepository<WeekEntry, Long> {

}
