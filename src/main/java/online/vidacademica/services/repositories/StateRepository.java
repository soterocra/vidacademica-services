package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
