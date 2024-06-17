package br.com.pedro.servicies;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.controllers.BookController;
import br.com.pedro.data.vo.v1.BookVO;
import br.com.pedro.mapper.custom.BookMapper;
import br.com.pedro.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	
	public BookVO create(BookVO bookVO) {
		var book = BookMapper.convertVoToEntity(bookVO);
		var vo = BookMapper.convertEntityToVo(repository.save(book));
		vo.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
		return vo;
		 
	}
	
	public BookVO findById(Long id) {
		var vo = BookMapper.convertEntityToVo(repository.findById(id).get());
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	
	public List<BookVO> findAll() {
		List<BookVO> books = repository.findAll().stream()
				.map(book -> BookMapper.convertEntityToVo(book)
				.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel()))
				.collect(Collectors.toList());
		return books;
	}
	
	
	public BookVO update(BookVO bookVO) {
		
		var book = repository.findById(bookVO.getId()).get();
		try {
			BeanUtils.copyProperties(bookVO, book);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		book.setAuthor(bookVO.getAuthor());
//		book.setLaunchDate(bookVO.getLaunchDate());
//		book.setPrice(bookVO.getPrice());
//		book.setTitle(bookVO.getTitle());
		
		var vo = BookMapper.convertEntityToVo(repository.save(book));
		vo.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
		return vo;
		
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
		 
	}
	
	

}
