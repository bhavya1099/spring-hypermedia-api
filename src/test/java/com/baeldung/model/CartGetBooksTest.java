

package com.baeldung.model;

import com.baeldung.entity.Book;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.ArrayList;

public class CartGetBooksTest {

	private Cart cart;

	private List<Book> books;

	@Before
	public void setUp() {
		this.cart = new Cart();
		this.books = new ArrayList<>();
	}

	@Test
	public void testGetBooksNormalBehavior() {
		Book book1 = new Book("Author1", "Title1", "ISBN1");
		Book book2 = new Book("Author2", "Title2", "ISBN2");

		books.add(book1);
		books.add(book2);

		// Note: Make sure the 'getBooks' method in "Cart" class is implemented correctly and make sure it returns a mutable list.
		// Also, make sure that 'Book' class has correct 'equals' and 'hashCode' methods, as it is used in 'assertEquals'.
		cart.getBooks().addAll(books);

		assertEquals("Book lists should be same", books, cart.getBooks()); 
	}

	@Test
	public void testGetBooksWhenNoBooksExist() {
		// Note: Make sure the 'getBooks' method in "Cart" class is implemented correctly and returns a list (can be empty) but not null.
		assertTrue("Book list should be empty", cart.getBooks().isEmpty());
	}

	@Test
	public void testGetBooksLargeNumberOfBooks() {
		for (int i = 0; i < 1000; i++) {
			books.add(new Book("Author" + i, "Title" + i, "ISBN" + i));
		}
		
		// Note: Make sure the 'getBooks' method in "Cart" class is implemented correctly and make sure it returns a mutable list.
		cart.getBooks().addAll(books);

		// Note: Ensure your application's heap space can handle this large amount of data and does not lead to OutOfMemoryError.
		assertEquals("Size of book list should be same", 1000, cart.getBooks().size());
	}

}
