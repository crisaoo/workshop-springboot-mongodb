package com.pescaria.workshopmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pescaria.workshopmongo.domain.Post;
import com.pescaria.workshopmongo.dto.CommentDTO;
import com.pescaria.workshopmongo.resources.util.URL;
import com.pescaria.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "posts")
public class PostResource {
	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody Post obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post obj){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
												@RequestParam(value = "minDate", defaultValue = "") String minDate,
												@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max= URL.convertDate(minDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	// Comments
	
	@GetMapping(value = "/{id}/comments")
	public ResponseEntity<List<CommentDTO>> findAllComments(@PathVariable String id){
		List<CommentDTO> list = service.findAllComments(id);
		return ResponseEntity.ok().body(list);
	}
}
