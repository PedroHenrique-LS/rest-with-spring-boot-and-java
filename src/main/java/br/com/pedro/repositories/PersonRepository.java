package br.com.pedro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
