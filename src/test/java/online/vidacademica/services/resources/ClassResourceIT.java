package online.vidacademica.services.resources;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Role;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.ClassRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassResourceIT {

    private static final String URI_PATH = "/class";

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    private static HttpHeaders headers = new HttpHeaders();
    private static User teacher;
    private static User student;

    private static Classe classe1;
    private static Classe classe2;

    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {

        if (setUpIsDone) {
            return;
        }

        createUsers();
        createClasses();

        String token = jwtUtil.generateToken(teacher.getUsername());
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        setUpIsDone = true;
    }

    @Test
    public void stage1_findAll_success() {
        HttpEntity<Classe[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Classe[]> response = restTemplate.exchange(URI_PATH, HttpMethod.GET, entity, Classe[].class);

        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    public void stage1_findById_success() {
        HttpEntity<Classe[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Classe> response = restTemplate.exchange(URI_PATH + "/" + classe1.getId(), HttpMethod.GET, entity, Classe.class);

        assertThat(response.getBody()).isEqualTo(classe1);
    }

    @Test
    public void stage2_insertClasse_success() {
        ClasseDTO classeDto = new ClasseDTO(null, "COMP - 2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        HttpEntity<ClasseDTO> entity = new HttpEntity<>(classeDto, headers);
        ResponseEntity<ClasseDTO> response = restTemplate.exchange(URI_PATH, HttpMethod.POST, entity, ClasseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void stage3_updateClasse_success() {
        classe1.setName("UPDATED");
        ClasseDTO classeDto = new ClasseDTO(classe1);

        HttpEntity<ClasseDTO> entity = new HttpEntity<>(classeDto, headers);
        ResponseEntity<ClasseDTO> response = restTemplate.exchange(URI_PATH + "/" + classeDto.getId(), HttpMethod.PUT, entity, ClasseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(classeDto.getName());
    }

    @Test
    public void stage3_deleteClasse_success() {
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(URI_PATH + "/" + classe2.getId(), HttpMethod.DELETE, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    public void createUsers() {
        teacher = new User(null, "Teacher 1", "teacher@example.com", Instant.now(),
                "21234444", "ABD", passwordEncode.encode("123456"), Instant.now());
        student = new User(null, "Student 1", "student@example.com", Instant.now(),
                "15309232", "ASD", passwordEncode.encode("123456"), Instant.now());

        userRepository.saveAll(Arrays.asList(teacher, student));

        Role roleTeacher = new Role(null, "ROLE_TEACHER");
        Role roleStudent = new Role(null, "ROLE_STUDENT");

        roleRepository.saveAll(Arrays.asList(roleTeacher, roleStudent));

        teacher.getRoles().add(roleTeacher);
        student.getRoles().add(roleStudent);

        userRepository.saveAll(Arrays.asList(teacher, student));
    }

    public void createClasses() {
        classe1 = new Classe(null, "SI -2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        classe2 = new Classe(null, "LCI - 2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        classRepository.saveAll(Arrays.asList(classe1, classe2));

    }

}
