
package com.baeldung.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

public class CartGetBooksTest {

	private Cart cart;

	private Book book1;

	private Book book2;

	private Book book3;

	@BeforeEach
	public void setUp() {
		cart = new Cart();
		book1 = new Book("Author1", "Title1", "ISBN1");
		book2 = new Book("Author2", "Title2", "ISBN2");
		book3 = new Book("Author3", "Title3", "ISBN3");
	}

	@Test
	@Tag("valid")
	public void testGetBooks() {
		cart.add(book1);
		cart.add(book2);
		List<Book> books = cart.getBooks();
		assertTrue(books.contains(book1));
		assertTrue(books.contains(book2));
		assertFalse(books.contains(book3));
	}

	@Test
	@Tag("boundary")
	public void testGetBooksForEmptyCart() {
		List<Book> books = cart.getBooks();
		assertTrue(books.isEmpty());
	}

	@Test
	@Tag("valid")
	public void testGetBooksReflectsLatestCartState() {
		cart.add(book1);
		cart.add(book2);
		cart.add(book3);
		List<Book> books = cart.getBooks();
		assertTrue(books.contains(book1));
		assertTrue(books.contains(book2));
		assertTrue(books.contains(book3));
	}

}