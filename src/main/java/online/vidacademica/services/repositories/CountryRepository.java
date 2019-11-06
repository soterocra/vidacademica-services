package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
