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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

	@Autowired
	private PersonServices services;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			"application/x-yaml" })
	@Operation(summary = "Finds a Person", description = "Finds a Person",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
		}
	)
	public PersonVO findById(@PathVariable(value = "id") Long id) {

		return services.findById(id);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Finds all People", description = "Finds all People",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", 
						content = {
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
	public List<PersonVO> findAll() {
		return services.findAll();
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			"application/x-yaml" }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					"application/x-yaml" })
	@Operation(summary = "Adds a new Person", description = "Adds a new Person by passind in a JSON, XML or YAML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Succes", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
		}
	)
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		
		return services.create(person);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/x-yaml" })
	@Operation(summary = "Updats a Person", description = "Updats a Person by passind in a JSON, XML or YAML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
		}
	)
	public PersonVO update(@RequestBody PersonVO person) {

		return services.update(person);

	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a Person", description = "Delete a Person by passind in a JSON, XML or YAML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content)
		}
	)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		services.delete(id);
		return ResponseEntity.noContent().build();
	}
}
