package online.vidacademica.services.repositories;

import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Course obj INNER JOIN obj.subject cats WHERE :subject IN cats")
    Page<Course> findBySubject(@org.springframework.data.repository.query.Param("subject") Subject subject, Pageable pageable);

    List<Course> findByCommander(User commander);

}


