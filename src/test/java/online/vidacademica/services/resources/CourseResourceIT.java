package online.vidacademica.services.resources;

import online.vidacademica.services.dto.CourseDTO;
import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.Role;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.CourseRepository;
import online.vidacademica.services.repositories.RoleRepository;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.security.JWTUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseResourceIT {

    private static final String URI_PATH = "/courses";

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    private static HttpHeaders headers = new HttpHeaders();
    private static User teacher;
    private static User student;

    private static Course course1;
    private static Course course2;

    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {

        if (setUpIsDone) {
            return;
        }

        createUsers();
        createCourses();

        String token = jwtUtil.generateToken(teacher.getUsername());
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        setUpIsDone = true;
    }

    @Test
    public void stage1_findAll_success() {
        HttpEntity<Course[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Course[]> response = restTemplate.exchange(URI_PATH, HttpMethod.GET, entity, Course[].class);

        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    public void stage1_findById_success() {
        HttpEntity<Course[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Course> response = restTemplate.exchange(URI_PATH + "/" + course1.getId(), HttpMethod.GET, entity, Course.class);

        assertThat(response.getBody()).isEqualTo(course1);
    }

    @Test
    public void stage2_insert_success() {
        CourseDTO courseDTO = new CourseDTO(null, "Licenciatura em Computação",
                "O curso de licenciatura em computação possibilita o ensino-aprendizagem de TI.",
                3025.00, true, Instant.parse("2020-12-02T00:21:22Z"));

        HttpEntity<CourseDTO> entity = new HttpEntity<>(courseDTO, headers);
        ResponseEntity<CourseDTO> response = restTemplate.exchange(URI_PATH, HttpMethod.POST, entity, CourseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void stage3_updateCourse_success() {
        course1.setName("UPDATED");
        CourseDTO courseDTO = new CourseDTO(course1);

        HttpEntity<CourseDTO> entity = new HttpEntity<>(courseDTO, headers);
        ResponseEntity<CourseDTO> response = restTemplate.exchange(URI_PATH + "/" + courseDTO.getId(), HttpMethod.PUT, entity, CourseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(courseDTO.getName());
    }

    @Test
    public void stage3_deleteCourse_success() {
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(URI_PATH + "/" + course2.getId(), HttpMethod.DELETE, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    public void createUsers() {
        teacher = new User(null, "Teacher 1", "teacher@example.com", Instant.now(),
                "21234444", passwordEncode.encode("123456"), Instant.now());
        student = new User(null, "Student 1", "student@example.com", Instant.now(),
                "15309232", passwordEncode.encode("123456"), Instant.now());

        userRepository.saveAll(Arrays.asList(teacher, student));

        Role roleTeacher = new Role(null, "ROLE_TEACHER");
        Role roleStudent = new Role(null, "ROLE_STUDENT");

        roleRepository.saveAll(Arrays.asList(roleTeacher, roleStudent));

        teacher.getRoles().add(roleTeacher);
        student.getRoles().add(roleStudent);

        userRepository.saveAll(Arrays.asList(teacher, student));
    }

    public void createCourses() {
        course1 = new Course(null, "Sistemas para Internet",
                "O curso de Tecnologia tem o objetivo de ensinar Sistemas para Internet.",
                2260.00, true, Instant.parse("2020-02-01T00:21:22Z"));

        course2 = new Course(null, "Logística",
                "O curso de Tecnologia tem o objetivo de ensinar Logística.",
                1820.00, true, Instant.parse("2020-01-01T00:21:22Z"));

        courseRepository.saveAll(Arrays.asList(course1, course2));
    }
}
