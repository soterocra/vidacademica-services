package online.vidacademica.services.testeConfig;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	// ADICIONAR REPOSITORIOS
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// ADICIONA OS ELEMENTOS DO BANCO TESTE
		
		User u1 = new User("Carlos Gustavo", null, "A2", "ASD", "123", Instant.now());
		User u2 = new User("Eduardo Augusto", null, "A3", "ABD", "123", Instant.now());
		User u3 = new User("Rafael Sotero", null, "B5", "CSD", "123", Instant.now());
		User u4 = new User("Tiago Marques", null, "C3", "JSD", "123", Instant.now());
	
		userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
	
	}
	
}
