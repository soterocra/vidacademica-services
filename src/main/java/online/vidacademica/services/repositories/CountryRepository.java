package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
