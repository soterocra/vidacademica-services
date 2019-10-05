package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.TimeTableEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableEntryRepository extends JpaRepository<TimeTableEntry, Long> {

}
