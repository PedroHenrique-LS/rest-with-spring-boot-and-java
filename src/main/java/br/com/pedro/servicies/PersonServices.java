package br.com.pedro.servicies;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		
	}
	
	public PersonVO create(PersonVO person) {
		
		Person entity = DozerMapper.parseObject(person, Person.class);
		
		return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
	}
	
	public PersonVO update(PersonVO person) {
		
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
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
