package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
