package br.com.pedro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.data.vo.v1.BookVO;
import br.com.pedro.servicies.BookServices;


@RestController
@RequestMapping(value = "/books")
public class BookController {
	
	@Autowired
	private BookServices services;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BookVO>  create(@RequestBody BookVO vo){
		return ResponseEntity.status(HttpStatus.CREATED).body(services.create(vo));
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BookVO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<BookVO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BookVO> update(@RequestBody BookVO vo) {
		return ResponseEntity.status(HttpStatus.OK).body(services.update(vo));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
