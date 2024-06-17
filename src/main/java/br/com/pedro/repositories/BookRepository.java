package br.com.pedro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.pedro.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
