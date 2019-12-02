package online.vidacademica.services.resources;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.dto.UserInsertDTO;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Registration;
import online.vidacademica.services.entities.Role;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.ClassRepository;
import online.vidacademica.services.repositories.RoleRepository;
import online.vidacademica.services.repositories.UserRepository;
import online.vidacademica.services.security.JWTUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserResourceIT {

    private static final String URI_PATH = "/users";

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
    private static User student1;
    private static User student2;
    private static Classe classe1;

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
        HttpEntity<User[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<User[]> response = restTemplate.exchange(URI_PATH, HttpMethod.GET, entity, User[].class);

        assertThat(response.getBody()).hasSize(3);
    }

    @Test
    public void stage1_findById_success() {
        HttpEntity<User[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<User> response = restTemplate.exchange(URI_PATH + "/" + teacher.getId(), HttpMethod.GET, entity, User.class);

        assertThat(response.getBody()).isEqualTo(teacher);
    }

    @Test
    public void stage2_insertUser_error() {
        UserInsertDTO userInsertDto = new UserInsertDTO(null, "TeacherTest", "TeacherT@mail.com", Instant.now(), "21234444", new Registration(null, Instant.now(), student1, classe1), passwordEncode.encode("123456") );

        HttpEntity<UserInsertDTO> entity = new HttpEntity<>(userInsertDto, headers);
        ResponseEntity<UserInsertDTO> response = restTemplate.exchange(URI_PATH, HttpMethod.POST, entity, UserInsertDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void stage3_updateUser_error() {
        teacher.setName(null);
        UserDTO userDto = new UserDTO(teacher);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDto, headers);
        ResponseEntity<UserDTO> response = restTemplate.exchange(URI_PATH + "/" + userDto.getId(), HttpMethod.PUT, entity, UserDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getName()).isEqualTo(userDto.getName());
    }

    @Test
    public void stage3_deleteUser_success() {
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(URI_PATH + "/" + student2.getId(), HttpMethod.DELETE, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    public void createUsers() {
        teacher = new User(null, "Teacher 1", "teacher@example.com", Instant.now(),
                "21234444", passwordEncode.encode("123456"), Instant.now());
        student1 = new User(null, "Student 1", "student1@example.com", Instant.now(),
                "15309232", passwordEncode.encode("123456"), Instant.now());
        student2 = new User(null, "Student 2", "student2@example.com", Instant.now(),
                "15309232", passwordEncode.encode("123456"), Instant.now());

        userRepository.saveAll(Arrays.asList(teacher, student1, student2));

        Role roleTeacher = new Role(null, "ROLE_TEACHER");
        Role roleStudent = new Role(null, "ROLE_STUDENT");

        roleRepository.saveAll(Arrays.asList(roleTeacher, roleStudent));

        teacher.getRoles().add(roleTeacher);
        student1.getRoles().add(roleStudent);
        student2.getRoles().add(roleStudent);

        userRepository.saveAll(Arrays.asList(teacher, student1));
    }

    public void createClasses() {
        classe1 = new Classe(null, "SI -2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        classRepository.saveAll(Arrays.asList(classe1));

    }
    

}
