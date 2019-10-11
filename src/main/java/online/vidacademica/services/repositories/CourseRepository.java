package online.vidacademica.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.vidacademica.services.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
