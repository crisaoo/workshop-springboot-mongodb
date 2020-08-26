package com.pescaria.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pescaria.workshopmongo.domain.Post;
import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.repositories.PostRepository;
import com.pescaria.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void run(String... args) throws Exception {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Cris", "Cris@gmail.com");
		User u2 = new User(null, "Cassio", "Cassio@gmail.com");
		User u3 = new User(null, "Mãe", "Mãe@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post p1 = new Post(null, sdf.parse("2020-03-21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", u1);
		Post p2 = new Post(null, sdf.parse("2020-02-23"), "Bom dia", "Acordei feliz hoje!", u1);
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
	}
}
