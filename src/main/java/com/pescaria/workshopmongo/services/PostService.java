package com.pescaria.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pescaria.workshopmongo.domain.Post;
import com.pescaria.workshopmongo.domain.User;
import com.pescaria.workshopmongo.dto.CommentDTO;
import com.pescaria.workshopmongo.repositories.PostRepository;
import com.pescaria.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	@Autowired
	private UserService userService;
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}
	
	public Post insert(Post obj) {
		User author = userService.setAuthorForPost(obj);
		Post insObj = repository.save(obj);
		userService.addPostForPostsCollection(insObj, author);		
		return insObj;
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post upObj = findById(obj.getId());
		updateData(upObj, obj);
		return repository.save(upObj);
	}

	
	private void updateData(Post upObj, Post obj) {
		String title = obj.getTitle();
		String body = obj.getBody();
		
		if (title != null)
			upObj.setTitle(title);
		if (body != null)
			upObj.setBody(body);
	}
	
	// Comments
	
	public List<CommentDTO> findAllComments(String id){
		Post obj = findById(id);
		return obj.getComments();
	}
}
