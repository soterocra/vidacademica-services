package online.vidacademica.services.repositories;

import online.vidacademica.services.dto.TestResultDTO;
import online.vidacademica.services.entities.Test;
import online.vidacademica.services.entities.TestResult;
import online.vidacademica.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    @Query(value = "SELECT * FROM tb_test_result tr WHERE tr.user_id = ?", nativeQuery = true)
    List<TestResult> findByUserId(Long user_id);


}
