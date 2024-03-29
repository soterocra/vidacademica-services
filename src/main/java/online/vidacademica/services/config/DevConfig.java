package online.vidacademica.services.config;

import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.*;
import java.util.Arrays;

@Configuration
@DependsOn("securityConfig")
@Profile({"dev", "testmariadb", "production"})
public class DevConfig implements CommandLineRunner {

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
    private  RegistrationRepository registrationRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public void run(String... args) throws Exception {

        // ADICIONA OS ELEMENTOS DO BANCO TESTE

        User u1 = new User(null, "Carlos Gustavo", "cgletras@gmail.com", null, "A2", passwordEncode.encode("123"), Instant.now());
        User u2 = new User(null, "Eduardo Augusto", "dudu@gmail.com", null, "A3", passwordEncode.encode("123"), Instant.now());
        User u3 = new User(null, "Rafael Sotero", "soso@gmail.com", null, "B5", passwordEncode.encode("123"), Instant.now());
        User u4 = new User(null, "Tiago Marques", "titi@gmail.com", null, "C3", passwordEncode.encode("123"), Instant.now());
        User u5 = new User(null, "Nelio Alves", "nelio@gmail.com", null, "d4", passwordEncode.encode("123"), Instant.now());
        User u6 = new User(null, "Cricia", "cricia@gmail.com", null, "d4", passwordEncode.encode("123"), Instant.now());

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));

        Role role_Student = new Role(null, "ROLE_STUDENT");
        Role role_Teacher = new Role(null, "ROLE_TEACHER");

        roleRepository.saveAll(Arrays.asList(role_Teacher, role_Student));

        u1.getRoles().add(role_Student);
        u2.getRoles().add(role_Student);
        u3.getRoles().add(role_Student);
        u4.getRoles().add(role_Student);
        u5.getRoles().add(role_Teacher);
        u6.getRoles().add(role_Teacher);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));


        Country ct1 = new Country(null, "Brasil");
        countryRepository.saveAll(Arrays.asList(ct1));

        State s1 = new State(null, "Minas Gerais", ct1);
        State s2 = new State(null, "Rio de Janeiro", ct1);
        State s3 = new State(null, "São Paulo", ct1);
        State s4 = new State(null, "Paraíba", ct1);
        State s5 = new State(null, "Brasília", ct1);
        stateRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

        City c1 = new City(null, "Uberlandia", s1);
        City c2 = new City(null, "Uberaba", s1);
        City c3 = new City(null, "Nova Ponte", s1);
        City c4 = new City(null, "Araxa", s1);
        City c5 = new City(null, "Belo Horizonte", s1);
        cityRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        Address a1 = new Address(null, "38408-265", "Rua João Balbino", "1153", c1, u1);
        Address a2 = new Address(null, "12312-265", "Rua Cifrao", "123", c1, u1);
        Address a3 = new Address(null, "38545-265", "Rua Balinha", "11", c1, u1);
        Address a4 = new Address(null, "45454-265", "Rua Pacheco", "153", c1, u1);
        Address a5 = new Address(null, "38408-165", "Rua Silva da Mata", "263", c1, u1);

        addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

        Post p5 = new Post("Veja essa postagem legal", Instant.now(), u3);
        Post p6 = new Post("Faça sua inscrição em um curso incrível", Instant.now(), u4);

        postRepository.saveAll(Arrays.asList(p5, p6));

        Post p1 = new Post("Muito bom!!", p5.getId(), Instant.now(), u1);
        Post p2 = new Post("Parabéns, ficou legal mesmo", p5.getId(), Instant.now(), u1);
        Post p3 = new Post("Massa.", p5.getId(), Instant.now(), u2);
        Post p4 = new Post("To dentro", p6.getId(), Instant.now(), u3);

        Phone ph1 = new Phone(null, "55", "34", "999472644", u1);
        Phone ph2 = new Phone(null, "55", "34", "999472644", u1);
        Phone ph3 = new Phone(null, "55", "34", "999472644", u2);

        Subject sub1 = new Subject(null, "Projeto e Desenvolvimento de Software",
                "" + "Conhecer fundamentos e boas práticas dos aspectos essenciais de projeto e\n"
                        + "desenvolvimento de um sistema para Internet com orientação a objetos e acesso a banco de\n"
                        + "dados. Utilizar técnicas e ferramentas para implementação dos tópicos estudados.",
                105.0, true, Instant.now(), 60.0, LocalDate.of(2019, 02, 01), LocalDate.of(2019, 07, 12));

        Subject sub2 = new Subject(null, "Banco de Dados",
                "Conhecer fundamentos e boas práticas usando a linguagem Mysql", 75.0, true, Instant.now(), 60.0,
                LocalDate.of(2019, 02, 01), LocalDate.of(2019, 07, 12));

        subjectRepository.saveAll(Arrays.asList(sub1));
        subjectRepository.saveAll(Arrays.asList(sub2));

        Course course1 = new Course(null, "Sistemas para Internet",
                "O curso de Tecnologia " + "em Sistemas para Internet possui como objetivo geral formar "
                        + "tecnólogos em Sistemas para Internet propiciando conhecimentos ",
                2260.00, true, Instant.parse("2010-01-01T00:21:22Z"));

        course1.getSubjects().add(sub1);
        course1.getSubjects().add(sub2);

        courseRepository.saveAll(Arrays.asList(course1));

        Classe class1 = new Classe(null, "SI -2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        Classe class2 = new Classe(null, "LCI - 2018/01", LocalDate.of(2018, 1, 1),
                LocalDate.of(2019, 7, 22), true, Instant.parse("2018-01-01T00:21:22Z"));

        postRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
        phoneRepository.saveAll(Arrays.asList(ph1, ph2, ph3));
        classRepository.saveAll(Arrays.asList(class1));
        classRepository.saveAll(Arrays.asList(class2));

//        TimeTable t1 = new TimeTable(null, "Horários 2019-2", LocalDate.now(), LocalDate.now().plusDays(180));

        WeekEntry e1 = new WeekEntry(null, DayOfWeek.MONDAY, 18L, 45L, 19L, 30L, class1);
        WeekEntry e2 = new WeekEntry(null, DayOfWeek.MONDAY, 19L, 30L, 20L, 15L, class1);
        WeekEntry e3 = new WeekEntry(null, DayOfWeek.TUESDAY, 18L, 45L, 19L, 30L, class2);
        WeekEntry e4 = new WeekEntry(null, DayOfWeek.TUESDAY, 19L, 30L, 20L, 15L, class2);
        WeekEntry e5 = new WeekEntry(null, DayOfWeek.TUESDAY, 20L, 15L, 21L, 00L, class2);

//        timeTableRepository.save(t1);
        timeTableEntryRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

        Test test1 = new Test(null, "Exercicio Avaliativo Banco de Dados", 10.00, Instant.parse("2019-08-15T00:21:22Z"),
                Instant.parse("2019-08-10T00:21:22Z"));
        test1.getUser().add(u1);
        test1.getUser().add(u2);
        test1.getUser().add(u3);
        test1.getUser().add(u4);


        Test test2 = new Test(null, "Prova 1 de Banco de Dados", 20.00, Instant.parse("2019-09-01T00:21:22Z"),
                Instant.parse("2019-08-31T00:21:22Z"));
        test2.getUser().add(u1);
        test2.getUser().add(u2);
        test2.getUser().add(u3);
        test2.getUser().add(u4);

        Test test3 = new Test(null, "Exercicio Avaliativo 2 de Banco de Dados", 15.00,
                Instant.parse("2019-09-10T00:21:22Z"), Instant.parse("2019-09-08T00:21:22Z"));
        test3.getUser().add(u1);
        test3.getUser().add(u2);
        test3.getUser().add(u3);
        test3.getUser().add(u4);

        Test test4 = new Test(null, "Prova 2 de Banco de Dados", 15.00,
                Instant.parse("2019-11-12T21:15:00Z"), Instant.parse("2019-11-08T12:21:22Z"));
        test4.getUser().add(u1);
        test4.getUser().add(u2);
        test4.getUser().add(u3);
        test4.getUser().add(u4);

        Test test5 = new Test(null, "Prova 2 de Redes de Computadores I", 25.00,
                Instant.parse("2019-11-01T21:15:00Z"), Instant.parse("2019-10-25T12:21:22Z"));

        testRepository.saveAll(Arrays.asList(test1));
        testRepository.saveAll(Arrays.asList(test2));
        testRepository.saveAll(Arrays.asList(test3));
        testRepository.saveAll(Arrays.asList(test4));
        testRepository.saveAll(Arrays.asList(test5));


        TestResult testResult1 = new TestResult(u1, test1, 8.00, Instant.parse("2019-09-01T00:21:22Z"));
        TestResult testResult2 = new TestResult(u2, test1, 8.00, Instant.parse("2019-09-01T00:21:22Z"));
        TestResult testResult3 = new TestResult(u3, test1, 7.00, Instant.parse("2019-09-01T00:21:22Z"));
        TestResult testResult4 = new TestResult(u4, test1, 10.00, Instant.parse("2019-09-01T00:21:22Z"));

        TestResult testResult5 = new TestResult(u1, test2, 17.00, Instant.parse("2019-09-01T00:21:22Z"));
        TestResult testResult6 = new TestResult(u2, test2, 15.00, Instant.parse("2019-10-01T00:21:22Z"));
        TestResult testResult7 = new TestResult(u3, test2, 18.00, Instant.parse("2019-10-01T00:21:22Z"));
        TestResult testResult8 = new TestResult(u4, test2, 10.00, Instant.parse("2019-10-01T00:21:22Z"));

        TestResult testResult9 = new TestResult(u1, test3, 15.00, Instant.parse("2019-10-11T00:21:22Z"));
        TestResult testResult10 = new TestResult(u2, test3, 13.00, Instant.parse("2019-10-11T00:21:22Z"));
        TestResult testResult11 = new TestResult(u3, test3, 12.00, Instant.parse("2019-10-11T00:21:22Z"));
        TestResult testResult12 = new TestResult(u4, test3, 14.00, Instant.parse("2019-10-11T00:21:22Z"));



        testResultRepository.saveAll(Arrays.asList(testResult1));
        testResultRepository.saveAll(Arrays.asList(testResult2));
        testResultRepository.saveAll(Arrays.asList(testResult3));
        testResultRepository.saveAll(Arrays.asList(testResult4));

        testResultRepository.saveAll(Arrays.asList(testResult5));
        testResultRepository.saveAll(Arrays.asList(testResult6));
        testResultRepository.saveAll(Arrays.asList(testResult7));
        testResultRepository.saveAll(Arrays.asList(testResult8));

        testResultRepository.saveAll(Arrays.asList(testResult9));
        testResultRepository.saveAll(Arrays.asList(testResult10));
        testResultRepository.saveAll(Arrays.asList(testResult11));
        testResultRepository.saveAll(Arrays.asList(testResult12));


        test1.setClasse(class1);
        test2.setClasse(class1);
        test3.setClasse(class1);
        test4.setClasse(class1);
        test5.setClasse(class2);


        testRepository.saveAll(Arrays.asList(test1, test2, test3, test4, test5));

        // TimeBox tb1 = new TimeBox(null, DayOfWeek.MONDAY, 18L, 45L, 19L, 30L, t1);
        // timeBoxRepository.save(tb1);

        LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());

        System.out.println("DADOS=====================");
        System.out.println(dt1);

        Instant i1 = dt1.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(i1);
        System.out.println(dt1.getDayOfWeek());

        LocalDate d1 = dt1.toLocalDate();
        System.out.println(d1.getDayOfWeek());

        Registration r1 = new Registration(null,Instant.parse("2019-10-11T00:21:22Z"), u2, class1);
        Registration r2 = new Registration(null,Instant.parse("2019-11-11T00:21:22Z"), u2, class2);

        registrationRepository.saveAll(Arrays.asList(r1,r2));
    }
}
