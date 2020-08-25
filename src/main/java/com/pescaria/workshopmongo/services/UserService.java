package com.pescaria.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.repositories.UserRepository;
import com.pescaria.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}
}
