package br.com.pedro.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.pedro.controllers.PersonController;
import br.com.pedro.data.vo.v1.PersonVO;
import br.com.pedro.excptions.ResourceNotFoundException;
import br.com.pedro.mapper.DozerMapper;
import br.com.pedro.model.Person;
import br.com.pedro.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	public PersonVO findById(Long id) {
		
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
		return vo;
	}
	
	public List<PersonVO> findAll() {
		List<PersonVO> people = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		people.stream().forEach(person -> {
			try {
				person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return people;
		
	}
	
	public PersonVO create(PersonVO person) {
		
		Person entity = DozerMapper.parseObject(person, Person.class);
		
		return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
	}
	
	public PersonVO update(PersonVO person) {
		
		Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
	}
	
	public void delete(Long id) {
		
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepository.delete(entity);
	}

	
	
}
