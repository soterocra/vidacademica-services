package online.vidacademica.services.testeConfig;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import online.vidacademica.services.entities.Address;
import online.vidacademica.services.entities.City;
import online.vidacademica.services.entities.Country;
import online.vidacademica.services.entities.Phone;
import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.State;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.entities.enums.PostType;
import online.vidacademica.services.repositories.AddressRepository;
import online.vidacademica.services.repositories.CityRepository;
import online.vidacademica.services.repositories.CountryRepository;
import online.vidacademica.services.repositories.PhoneRepository;
import online.vidacademica.services.repositories.PostRepository;
import online.vidacademica.services.repositories.StateRepository;
import online.vidacademica.services.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

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
	
	@Override
	public void run(String... args) throws Exception {
		
		// ADICIONA OS ELEMENTOS DO BANCO TESTE
		
		User u1 = new User(null, "Carlos Gustavo","cgletras@gmail.com", null, "A2", "ASD", "123", Instant.now());
		User u2 = new User(null, "Eduardo Augusto","dudu@gmail.com", null, "A3", "ABD", "123", Instant.now());
		User u3 = new User(null, "Rafael Sotero", "soso@gmail.com", null, "B5", "CSD", "123", Instant.now());
		User u4 = new User(null, "Tiago Marques", "titi@gmail.com", null, "C3", "JSD", "123", Instant.now());
		
		Country ct1 = new Country(null, "Brasil");
		State s1 = new State(null, "Minas Gerais", ct1);
		City c1 = new City(null, "Uberlandia", s1);
		Address a1 = new Address(null, "38408-265", "Rua João Balbino", "1153", c1, u1);
		
		Post p1 = new Post(null, "Essa é uma mensagem de teste", PostType.COMMENT, Instant.now(), u1);
		Post p2 = new Post(null, "Essa é uma mensagem de teste", PostType.COMMENT,Instant.now(), u1);
		Post p3 = new Post(null, "Essa é uma mensagem de teste", PostType.COMMENT,Instant.now(), u2);
		Post p4 = new Post(null, "Essa é uma mensagem de teste", PostType.COMMENT,Instant.now(), u3);
		Post p5 = new Post(null, "Essa é uma mensagem de teste", PostType.POST,Instant.now(), u3);
		Post p6 = new Post(null, "Essa é uma mensagem de teste", PostType.POST,Instant.now(), u4);
		
		Phone ph1 = new Phone(null, "55", "34", "999472644", u1);
		Phone ph2 = new Phone(null, "55", "34", "999472644", u1);
		Phone ph3 = new Phone(null, "55", "34", "999472644", u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
		postRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
		phoneRepository.saveAll(Arrays.asList(ph1, ph2, ph3));
	
		countryRepository.saveAll(Arrays.asList(ct1));
		stateRepository.saveAll(Arrays.asList(s1));
		cityRepository.saveAll(Arrays.asList(c1));
		addressRepository.saveAll(Arrays.asList(a1));
	
	
	
	
	}
	
}
