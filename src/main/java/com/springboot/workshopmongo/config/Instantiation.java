package com.springboot.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springboot.workshopmongo.domain.Post;
import com.springboot.workshopmongo.domain.User;
import com.springboot.workshopmongo.dto.AuthorDTO;
import com.springboot.workshopmongo.repository.PostRepository;
import com.springboot.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//LIMPEZA DAS COLEÇÕES
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		// INSTANCIANDO OS USUARIOS
		User mario = new User(null, // ID
				"Mario Sergio", // NOME
				"mario@paysandu.com"); // EMAIL
		User joao = new User(null, 
				"Joao Vieira", 
				"joao@paysandu.com"); 
		User yuri = new User(null, 
				"Yuri Alberto", 
				"yuri@corinthians.com");
		
		// SALVAR AS INTANCIAS
		userRepository.saveAll(Arrays.asList(mario, joao, yuri));
		
		// INSTANCIANDO OS POSTS
		Post post1 = new Post(null, // ID
				sdf.parse("11/11/2012"), // DATA
				"Meu aniversario", // TITULO
				"Muitos aos de vida!!", // DESCRIÇÃO DO POST
			new AuthorDTO(yuri)); // AUTOR 
		
		Post post2 = new Post(null,
				sdf.parse("12/06/2016"), 
				"Vamos subir paysandu!!", 
				"VAMO PRA SERIE B",
				new AuthorDTO(joao));
		
		// SALVAR AS INTANCIAS
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
