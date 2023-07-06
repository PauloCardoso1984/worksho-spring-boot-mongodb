package com.paulocardoso.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulocardoso.domain.Post;
import com.paulocardoso.domain.User;
import com.paulocardoso.dto.AuthorDTO;
import com.paulocardoso.dto.CommentDTO;
import com.paulocardoso.repository.PostRepository;
import com.paulocardoso.repository.UserRepository;

// CONFIGURAÇÃO
@Configuration
public class Instantiastion implements CommandLineRunner {

	
	// INJETAR O USER_REPOSITORY
	@Autowired
	private UserRepository userRepository;
	
	// INJETAR O POST_REPOSITORY
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				
		// VAI LIMPAR A COLEÇÃO NO MONGODB
		userRepository.deleteAll();
		
		// VAI LIMPAR A COLEÇÃO DE COMENTARIO NO MONGODB
		postRepository.deleteAll();
		
		//INSTANCIAR OBJETOS E SALVAR NO BANCO DE DADOS
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// SALVAR USUÁRIOS
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
				

		// SALVANDO OS POSTS E COMENTARIOS
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
