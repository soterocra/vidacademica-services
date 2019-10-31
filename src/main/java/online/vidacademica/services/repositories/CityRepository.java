package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
