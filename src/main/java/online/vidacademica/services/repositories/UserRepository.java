package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
