package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.TestResult;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
