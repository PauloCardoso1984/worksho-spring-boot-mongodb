package com.paulocardoso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulocardoso.domain.User;
import com.paulocardoso.repository.UserRepository;


// CONFIGURAÇÃO
@Configuration
public class Instantiastion implements CommandLineRunner {

	
	// INJETAR O USER_REPOSITORY
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// VAI LIMPAR A COLEÇÃO NO MONGODB
		userRepository.deleteAll();
		
		//INSTANCIAR OBJETOS E SALVAR NO BANCO DE DADOS
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// SALVAR A COLEÇÃO ACIMA NO MONGODB
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
	}

}
