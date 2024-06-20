package br.com.pedro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.pedro.data.vo.v1.PersonVO;
import br.com.pedro.servicies.BookServices;
import br.com.pedro.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/books")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {
	
	@Autowired
	private BookServices services;
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Adds a new Book", description = "Adds a new Book", tags = { "Books" }, 
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))
			),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
	}
	)
	public ResponseEntity<BookVO>  create(@RequestBody BookVO vo){
		return ResponseEntity.status(HttpStatus.CREATED).body(services.create(vo));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Finds a Book", description = "Finds a Book", tags = { "Books" }, 
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
	}
	)
	public ResponseEntity<BookVO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Finds All Books", description = "Finds All Books", tags = { "Books" },
	responses = { 
			@ApiResponse(description = "Succes", responseCode = "200", content = {
					@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
							)
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
	}
	)
	public ResponseEntity<List<BookVO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
	}
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Updats a Book", description = "updats a Book", tags = { "Books" },
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = BookVO.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
	}
	)
	public ResponseEntity<BookVO> update(@RequestBody BookVO vo) {
		return ResponseEntity.status(HttpStatus.OK).body(services.update(vo));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delets a Book", description = "Delets a Book", tags = { "Books" },
	responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
	}
	)
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
