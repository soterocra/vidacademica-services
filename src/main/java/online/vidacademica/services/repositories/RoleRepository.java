package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
