package br.com.pedro.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.data.vo.v1.PersonVO;
import br.com.pedro.servicies.PersonServices;
import br.com.pedro.util.MediaType;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices services;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	public PersonVO findById(@PathVariable(value = "id") Long id){

		return services.findById(id);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	public List<PersonVO> findAll() {
		return services.findAll();
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) throws Exception {

		return services.create(person);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {

		return services.update(person);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		services.delete(id);
		return ResponseEntity.noContent().build();
	}
}
