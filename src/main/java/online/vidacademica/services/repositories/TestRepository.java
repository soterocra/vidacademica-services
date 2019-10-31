package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}
