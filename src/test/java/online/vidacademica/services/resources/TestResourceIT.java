package online.vidacademica.services.resources;

import online.vidacademica.services.dto.TestDTO;
import online.vidacademica.services.entities.Role;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.RoleRepository;
import online.vidacademica.services.repositories.TestRepository;
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

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResourceIT {

    private static final String URI_PATH = "/tests";

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    private static HttpHeaders headers = new HttpHeaders();
    private static User teacher;
    private static User student;

    private static online.vidacademica.services.entities.Test test1;
    private static online.vidacademica.services.entities.Test test2;

    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {

        if (setUpIsDone) {
            return;
        }

        createUsers();
        createTests();

        String token = jwtUtil.generateToken(teacher.getUsername());
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        setUpIsDone = true;
    }

    @Test
    public void stage1_findAll_success() {
        HttpEntity<online.vidacademica.services.entities.Test[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<online.vidacademica.services.entities.Test[]> response = restTemplate.exchange(URI_PATH, HttpMethod.GET, entity, online.vidacademica.services.entities.Test[].class);

        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    public void stage1_findById_success() {
        HttpEntity<online.vidacademica.services.entities.Test[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<online.vidacademica.services.entities.Test> response = restTemplate.exchange(URI_PATH + "/" + test1.getId(), HttpMethod.GET, entity, online.vidacademica.services.entities.Test.class);

        assertThat(response.getBody()).isEqualTo(test1);
    }

    @Test
    public void stage2_insert_success() {
        TestDTO testDTO = new TestDTO(null,"Prova 2 de Banco de Dados",100.00,Instant.parse("2018-01-01T00:21:22Z"),Instant.now());

        HttpEntity<TestDTO> entity = new HttpEntity<>(testDTO, headers);
        ResponseEntity<TestDTO> response = restTemplate.exchange(URI_PATH, HttpMethod.POST, entity, TestDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void stage3_updateTest_success() {
        test1.setName("UPDATED");
        test1.setFullScore(10.00);
        test1.setDate(Instant.parse("2018-01-01T00:21:22Z"));
        test1.setCreationDate(Instant.now());
        TestDTO testDTO = new TestDTO(test1);

        HttpEntity<TestDTO> entity = new HttpEntity<>(testDTO, headers);
        ResponseEntity<TestDTO> response = restTemplate.exchange(URI_PATH + "/" + testDTO.getId(), HttpMethod.PUT, entity, TestDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(testDTO.getName());
        assertThat(response.getBody().getFullScore()).isEqualTo(testDTO.getFullScore());
        assertThat(response.getBody().getDate()).isEqualTo(testDTO.getDate());
        assertThat(response.getBody().getCreationDate()).isEqualTo(testDTO.getCreationDate());
    }

    @Test
    public void stage3_deleteTest_success() {
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(URI_PATH + "/" + test2.getId(), HttpMethod.DELETE, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    public void createUsers() {
        teacher = new User(null, "Teacher 1", "teacher@example.com", Instant.now(),
                "21234444", passwordEncode.encode("123456"), Instant.now());
        student = new User(null, "Student 1", "student@example.com", Instant.now(),
                "15309232", passwordEncode.encode("123456"), Instant.now());

        userRepository.save(teacher);
        userRepository.save(student);

        Role roleTeacher = new Role(null, "ROLE_TEACHER");
        Role roleStudent = new Role(null, "ROLE_STUDENT");

        roleRepository.save(roleTeacher);
        roleRepository.save(roleStudent);

        teacher.getRoles().add(roleTeacher);
        student.getRoles().add(roleStudent);

        userRepository.save(teacher);
        userRepository.save(student);
    }

    public void createTests() {
         test1 = new online.vidacademica.services.entities.Test(null, "Exercicio Avaliativo Banco de Dados", 10.00, Instant.parse("2019-08-15T00:21:22Z"),
                Instant.parse("2019-08-10T00:21:22Z"));

         test2 = new online.vidacademica.services.entities.Test(null, "Prova 1 de Banco de Dados", 20.00, Instant.parse("2019-09-01T00:21:22Z"),
                Instant.parse("2019-08-31T00:21:22Z"));

        testRepository.save(test1);
        testRepository.save(test2);

    }

}
