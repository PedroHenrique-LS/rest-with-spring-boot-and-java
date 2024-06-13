package br.com.pedro.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.pedro.data.vo.v1.PersonVO;
import br.com.pedro.model.Person;

@Service
public class PersonMapper {

	public PersonVO convertEntityToVo(Person person) {
		PersonVO personVO = new PersonVO();
		
		personVO.setKey(person.getId());
		personVO.setAddress(person.getAddress());
		personVO.setFirstName(person.getFirstName());
		personVO.setGender(person.getGender());
		personVO.setLastName(person.getLastName());
		return personVO;
	}
	
	public Person convertVoToEntity(PersonVO personVO) {
		Person person = new Person();
		
		person.setId(personVO.getKey());
		person.setAddress(personVO.getAddress());
		person.setFirstName(personVO.getFirstName());
		person.setGender(personVO.getGender());
		person.setLastName(personVO.getLastName());
		return person;
	}
	
}
