package br.com.pedro.data.vo.v1;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class BookVO extends RepresentationModel<BookVO> implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String author;
	private Instant launchDate;
	private Double price;
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Instant getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Instant launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(id, other.id);
	}

	

}
