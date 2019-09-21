package online.vidacademica.services.testeConfig;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.repositories.PostRepository;
import online.vidacademica.services.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	// ADICIONAR REPOSITORIOS
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// ADICIONA OS ELEMENTOS DO BANCO TESTE
		
		User u1 = new User("Carlos Gustavo", null, "A2", "ASD", "123", Instant.now());
		User u2 = new User("Eduardo Augusto", null, "A3", "ABD", "123", Instant.now());
		User u3 = new User("Rafael Sotero", null, "B5", "CSD", "123", Instant.now());
		User u4 = new User("Tiago Marques", null, "C3", "JSD", "123", Instant.now());
	
		Post p1 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u1);
		Post p2 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u1);
		Post p3 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u2);
		Post p4 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u3);
		Post p5 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u3);
		Post p6 = new Post(null, "Essa é uma mensagem de teste", Instant.now(), u4);
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
		postRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
	
	}
	
}
