package online.vidacademica.services.config;

import online.vidacademica.services.entities.Role;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile({"as"})
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


    }
}
