package br.com.pedro.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.pedro.data.vo.v1.BookVO;
import br.com.pedro.model.Book;

@Service
public class BookMapper {
	

	public static BookVO convertEntityToVo(Book book) {
		BookVO bookVO = new BookVO();
		
		bookVO.setId(book.getId());
		bookVO.setAuthor(book.getAuthor());
		bookVO.setLaunchDate(book.getLaunchDate());
		bookVO.setPrice(book.getPrice());
		bookVO.setTitle(book.getTitle());
		return bookVO;
	}
	
	public static Book convertVoToEntity(BookVO bookVO) {
		Book book = new Book();
		
		book.setId(bookVO.getId());
		book.setAuthor(bookVO.getAuthor());
		book.setLaunchDate(bookVO.getLaunchDate());
		book.setPrice(bookVO.getPrice());
		book.setTitle(bookVO.getTitle());
		return book;
	}

}
