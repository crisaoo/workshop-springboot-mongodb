package com.pescaria.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pescaria.workshopmongo.domain.Post;
import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.dto.AuthorDTO;
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
		String name = obj.getName();
		String email = obj.getEmail();

		if (name != null)
			entity.setName(name);
		if (email != null)
			entity.setEmail(email);
	}

	// Adiciona o author ao post (o author necessita apenas do id)
	public User setAuthorForPost(Post obj) {
		User author = findById(obj.getAuthor().getId());
		obj.setAuthor(new AuthorDTO(author));
		return author;
	}

	// Adiciona o post à coleção de posts do user
	public void addPostForPostsCollection(Post obj, User user) {
		user.getPosts().add(obj);
		repository.save(user);
	}
}
