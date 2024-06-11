package br.com.pedro.unittests.mockito.servicies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pedro.model.Person;
import br.com.pedro.repositories.PersonRepository;
import br.com.pedro.servicies.PersonServices;
import br.com.pedro.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonServices services;
	
	@Mock
	private PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person person = input.mockEntity(1);
		person.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		var resulte = services.findById(1L);
		assertNotNull(resulte);
		assertNotNull(resulte.getKey());
		assertNotNull(resulte.getLinks());
		System.out.println(resulte.toString());
		assertTrue(resulte.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", resulte.getAddress());
		assertEquals("First Name Test1", resulte.getFirstName());
		assertEquals("Last Name Test1", resulte.getLastName());
		assertEquals("Female", resulte.getGender());
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
