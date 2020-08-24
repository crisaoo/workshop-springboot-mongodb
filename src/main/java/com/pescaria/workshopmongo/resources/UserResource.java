package com.pescaria.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pescaria.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = new ArrayList<>();
		
		User u1 = new User("1001", "Cris", "Cris@gmail.com");
		User u2 = new User("1002", "Cassio", "Cassio@gmail.com");
		User u3 = new User("1003", "Mãe", "Mãe@gmail.com");
		
		list.addAll(Arrays.asList(u1, u2, u3));
		
		return ResponseEntity.ok().body(list);
	}
}
