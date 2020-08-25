package com.pescaria.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User u1 = new User(null, "Cris", "Cris@gmail.com");
		User u2 = new User(null, "Cassio", "Cassio@gmail.com");
		User u3 = new User(null, "Mãe", "Mãe@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
}
