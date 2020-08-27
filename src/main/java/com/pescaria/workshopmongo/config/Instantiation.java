package com.pescaria.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pescaria.workshopmongo.domain.Post;
import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.dto.AuthorDTO;
import com.pescaria.workshopmongo.dto.CommentDTO;
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
		
		Post p1 = new Post(null, sdf.parse("2020-03-21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(u2));
		Post p2 = new Post(null, sdf.parse("2020-02-23"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(u2));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!!", sdf.parse("2020-03-22"), new AuthorDTO(u3));
		CommentDTO c2 = new CommentDTO("Bom dia!!!", sdf.parse("2020-02-23"), new AuthorDTO(u3));
		CommentDTO c3 = new CommentDTO("bom dia é o caralho, aqui é torcida jovem", sdf.parse("2020-02-23"), new AuthorDTO(u1));
		
		p1.getComments().add(c1);
		p2.getComments().addAll(Arrays.asList(c2, c3));
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		u2.getPosts().addAll(Arrays.asList(p1,p2));
		userRepository.save(u2);
	}
}
