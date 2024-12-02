

package com.baeldung.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class BookSetIsbnTest {

	private Book book;

	@BeforeEach
	public void setUp() {
		book = new Book();
	}

	@Test
	public void setValidIsbn() {
		String validIsbn = "978-3-16-148410-0";
		book.setIsbn(validIsbn);
		assertEquals(validIsbn, book.getIsbn());
	}

	@Test
	public void setInvalidIsbn() {
		String invalidIsbn = "12345";
		book.setIsbn(invalidIsbn);
		assertEquals(invalidIsbn, book.getIsbn());
	}

	@Test
	public void setNullIsbn() {
		book.setIsbn(null);
		assertNull(book.getIsbn());
	}

}
