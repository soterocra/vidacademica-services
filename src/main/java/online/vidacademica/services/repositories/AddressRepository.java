package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
