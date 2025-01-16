package com.baeldung.web.controller;

import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mockito.when;
import com.baeldung.model.Book;
import com.baeldung.persistence.BookRepository;
import com.baeldung.web.error.Checks;
import com.baeldung.web.resource.BookResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baeldung.model.BookView;
import com.fasterxml.jackson.annotation.JsonView;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import org.mockito.Mockito.times;
import org.mockito.Mockito.verify;
import org.mockito.Mockito;

public class BookControllerTest {

	@BeforeEach
	void init() {
		validIsbn = "1234567890";
		invalidIsbn = "invalidIsbn";
		book = new Book();
		book.setIsbn(validIsbn);
	}

@Test
@Tag("valid")
public void testFindByValidIsbn() {
    when(bookRepository.findByIsbn(validIsbn)).thenReturn(book);
    BookResource result = bookController.findByIsbn(validIsbn);
    assertNotNull(result);
    assertEquals(result.getBook(), book);
}

@Test
@Tag("invalid")
public void testFindByInvalidIsbn() {
    when(bookRepository.findByIsbn(invalidIsbn)).thenReturn(null);
    assertThrows(Checks.EntityNotFoundException.class, () -> {
        bookController.findByIsbn(invalidIsbn);
    });
}

	@Test
	@Tag("boundary")
	public void testFindByNullIsbn() {
		assertThrows(IllegalArgumentException.class, () -> {
			bookController.findByIsbn(null);
		});
	}

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

@Test
@Tag("valid")
public void testFindAllWhenRepoIsEmpty() {
    when(repo.findAll()).thenReturn(Collections.emptyList());
    List<BookResource> result = bookController.findAll();
    assertEquals(0, result.size());
}

	@Test
	@Tag("valid")
	public void testFindAllWhenRepoHasBooks() {
		Book book1 = new Book();
		Book book2 = new Book();
		when(repo.findAll()).thenReturn(Arrays.asList(book1, book2));
		List<BookResource> result = bookController.findAll();
		assertEquals(2, result.size());
		assertEquals(book1, result.get(0).getBook());
		assertEquals(book2, result.get(1).getBook());
	}

@Test
@Tag("invalid")
public void testFindAllWhenRepoThrowsException() {
    when(repo.findAll()).thenThrow(new RuntimeException());
    assertThrows(RuntimeException.class, () -> bookController.findAll());
}

	@Test
	@Tag("valid")
	public void testFindAllSummaryReturnsCorrectList() {
		Book book1 = new Book();
		Book book2 = new Book();
		List<Book> bookList = Arrays.asList(book1, book2);
		when(repo.findAll()).thenReturn(bookList);
		List<BookResource> expectedList = Arrays.asList(new BookResource(book1), new BookResource(book2));
		List<BookResource> actualList = bookController.findAllSummary();
		assertEquals(expectedList, actualList);
		verify(repo, times(1)).findAll();
	}

@Test
@Tag("boundary")
public void testFindAllSummaryReturnsEmptyListWhenNoBooks() {
    when(repo.findAll()).thenReturn(Collections.emptyList());
    List<BookResource> actualList = bookController.findAllSummary();
    assertEquals(Collections.emptyList(), actualList);
    verify(repo, times(1)).findAll();
}

@Test
@Tag("invalid")
public void testFindAllSummaryHandlesException() {
    when(repo.findAll()).thenThrow(new RuntimeException());
    try {
        bookController.findAllSummary();
    } catch (Exception e) {
        assertEquals(RuntimeException.class, e.getClass());
    }
    verify(repo, times(1)).findAll();
}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bookController = new BookController(bookRepository);
	}

	@Test
	@Tag("valid")
	public void testCreateBook() {
		BookResource bookResource = mock(BookResource.class);
		Book book = mock(Book.class);
		when(bookResource.getBook()).thenReturn(book);
		bookController.create(bookResource);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	@Tag("invalid")
	public void testCreateBookWithNullInput() {
		bookController.create(null);
		verify(bookRepository, never()).save(any(Book.class));
	}

	@Test
	@Tag("boundary")
	public void testCreateBookWithEmptyDetails() {
		BookResource bookResource = mock(BookResource.class);
		Book book = new Book();
		when(bookResource.getBook()).thenReturn(book);
		bookController.create(bookResource);
		verify(bookRepository, times(1)).save(book);
	}

}