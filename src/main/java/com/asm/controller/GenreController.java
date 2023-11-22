package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.GenreDAO;
import com.asm.entity.Genre;
@CrossOrigin("*")
@RestController
public class GenreController {

	@Autowired
	GenreDAO dao;
	
	@GetMapping("/rest/genres")
	public ResponseEntity<List<Genre>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@GetMapping("/rest/genres/{id}")
	public ResponseEntity<Genre> getOne(@PathVariable("id") Long id){
		if(!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id).get());
	}
	
	@PostMapping("/rest/genres")
	public ResponseEntity<Genre> post(@RequestBody Genre genres){
		if(dao.existsById(genres.getId())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(genres);
		return ResponseEntity.ok(genres);
	}
	
	@PutMapping("/rest/genres/{id}")
	public ResponseEntity<Genre> put(@PathVariable("id") Long id, @RequestBody Genre genre){
		if(!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.save(genre);
		return ResponseEntity.ok(genre);
	}
	
	@DeleteMapping("/rest/genres/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
