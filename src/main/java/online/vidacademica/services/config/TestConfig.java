package online.vidacademica.services.config;

import java.time.*;
import java.util.Arrays;

import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    // ADICIONAR REPOSITORIOS
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

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
    private TimeTableEntryRepository timeTableEntryRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Override
    public void run(String... args) throws Exception {

        // ADICIONA OS ELEMENTOS DO BANCO TESTE

        User u1 = new User(null, "Carlos Gustavo", "cgletras@gmail.com", null, "A2", "ASD", "123", Instant.now());
        User u2 = new User(null, "Eduardo Augusto", "dudu@gmail.com", null, "A3", "ABD", "123", Instant.now());
        User u3 = new User(null, "Rafael Sotero", "soso@gmail.com", null, "B5", "CSD", "123", Instant.now());
        User u4 = new User(null, "Tiago Marques", "titi@gmail.com", null, "C3", "JSD", "123", Instant.now());

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

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

        Subject sub1 = new Subject(null, "Projeto e Desenvolvimento de Software", ""
                + "Conhecer fundamentos e boas práticas dos aspectos essenciais de projeto e\n" +
                "desenvolvimento de um sistema para Internet com orientação a objetos e acesso a banco de\n" +
                "dados. Utilizar técnicas e ferramentas para implementação dos tópicos estudados.", 105.0, true, Instant.now(), 60.0);

        subjectRepository.saveAll(Arrays.asList(sub1));

        Classe class1 = new Classe(null, "Sistemas Para Internet", Instant.now(), Instant.parse("2019-07-22T15:21:22Z"), true, Instant.parse("2019-07-22T15:21:22Z"), sub1);

        postRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
        phoneRepository.saveAll(Arrays.asList(ph1, ph2, ph3));
        classRepository.saveAll(Arrays.asList(class1));


        TimeTable t1 = new TimeTable(null, "Horários 2019-2");

        TimeTableEntry e1 = new TimeTableEntry(null, DayOfWeek.MONDAY, 18L, 45L, 19L, 30L, t1);
        TimeTableEntry e2 = new TimeTableEntry(null, DayOfWeek.MONDAY, 19L, 30L, 20L, 15L, t1);
        TimeTableEntry e3 = new TimeTableEntry(null, DayOfWeek.TUESDAY, 18L, 45L, 19L, 30L, t1);
        TimeTableEntry e4 = new TimeTableEntry(null, DayOfWeek.TUESDAY, 19L, 30L, 20L, 15L, t1);
        TimeTableEntry e5 = new TimeTableEntry(null, DayOfWeek.TUESDAY, 20L, 15L, 21L, 00L, t1);

        timeTableRepository.save(t1);
        timeTableEntryRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

        //TimeBox tb1 = new TimeBox(null, DayOfWeek.MONDAY, 18L, 45L, 19L, 30L, t1);
        //timeBoxRepository.save(tb1);

        LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());

        System.out.println("DADOS=====================");
        System.out.println(dt1);

        Instant i1 = dt1.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(i1);
        System.out.println(dt1.getDayOfWeek());

        LocalDate d1 = dt1.toLocalDate();
        System.out.println(d1.getDayOfWeek());
    }
}
