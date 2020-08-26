package com.pescaria.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User upObj = findById(obj.getId());
		updateData(upObj, obj);
		return repository.save(upObj);
	}
	
	
	public User fromDTO(UserDTO dto) {
		String id = dto.getId();
		String name = dto.getName(); 
		String email = dto.getEmail();
		return new User(id, name, email);

	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
	}
}
