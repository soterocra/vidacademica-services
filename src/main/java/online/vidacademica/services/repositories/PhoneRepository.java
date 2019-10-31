package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
