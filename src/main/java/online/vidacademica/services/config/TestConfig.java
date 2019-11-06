package online.vidacademica.services.config;

import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.*;
import java.util.Arrays;

@Configuration
@Profile({"test", "testmariadb"})
public class TestConfig implements CommandLineRunner {

    // ADICIONAR REPOSITORIOS
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private WeekEntryRepository timeTableEntryRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public void run(String... args) throws Exception {

        // ADICIONA OS ELEMENTOS DO BANCO TESTE

        User u1 = new User(null, "Carlos Gustavo", "cgletras@gmail.com", null, "A2", "ASD", passwordEncode.encode("123"), Instant.now());
        User u2 = new User(null, "Eduardo Augusto", "dudu@gmail.com", null, "A3", "ABD", passwordEncode.encode("123"), Instant.now());
        User u3 = new User(null, "Rafael Sotero", "soso@gmail.com", null, "B5", "CSD", passwordEncode.encode("123"), Instant.now());
        User u4 = new User(null, "Tiago Marques", "titi@gmail.com", null, "C3", "JSD", passwordEncode.encode("123"), Instant.now());
        User u5 = new User(null, "Nelio Alves", "nelio@gmail.com", null, "d4", "JSD", passwordEncode.encode("123"), Instant.now());
        User u6 = new User(null, "Cricia", "cricia@gmail.com", null, "d4", "JSD", passwordEncode.encode("123"), Instant.now());

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));

        Role role_Teacher = new Role(null, "ROLE_TEACHER");
        Role role_Student = new Role(null, "ROLE_STUDENT");

        roleRepository.saveAll(Arrays.asList(role_Teacher, role_Student));

        u1.getRoles().add(role_Student);
        u2.getRoles().add(role_Student);
        u3.getRoles().add(role_Student);
        u4.getRoles().add(role_Student);
        u5.getRoles().add(role_Teacher);
        u6.getRoles().add(role_Teacher);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));
    }
}
