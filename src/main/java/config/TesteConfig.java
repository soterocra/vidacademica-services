package config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")

public class TesteConfig implements CommandLineRunner {

	
	// ADICIONAR REPOSITORIOS
	
	@Override
	public void run(String... args) throws Exception {
		
		// ADICIONA OS ELEMENTOS DO BANCO
		
		
		
	}
	
}
