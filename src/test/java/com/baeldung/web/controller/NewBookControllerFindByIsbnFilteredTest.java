
// ********RoostGPT********
/*
Test generated by RoostGPT for test javaspring-unit-test using AI Type Open AI and AI Model gpt-4

ROOST_METHOD_HASH=findByIsbnFiltered_8b3376a021
ROOST_METHOD_SIG_HASH=findByIsbnFiltered_3bb19813ef

"""
Scenario 1: Test to find a book by ISBN with specific fields
Details:
  TestName: testFindByIsbnFilteredWithValidFields.
  Description: This test is designed to check the findByIsbnFiltered method when given a valid ISBN and fields. The method should return a book with the specified fields.
Execution:
  Arrange: Mock the BookRepository to return a valid Book when findByIsbn is called with a specific ISBN. Also, set the expected fields to be returned.
  Act: Call the findByIsbnFiltered method with the mocked ISBN and fields.
  Assert: Assert that the returned MappingJacksonValue's wrapped value is an instance of NewBookResource and its filter includes the specified fields.
Validation:
  The assertion verifies that the method correctly filters out the fields of the book based on the input. This test is essential to ensure that the application only displays the required information when a book is queried by its ISBN.

Scenario 2: Test to find a book by ISBN with no fields
Details:
  TestName: testFindByIsbnFilteredWithNoFields.
  Description: This test is designed to check the findByIsbnFiltered method when given a valid ISBN and no fields. The method should return a book without any fields.
Execution:
  Arrange: Mock the BookRepository to return a valid Book when findByIsbn is called with a specific ISBN.
  Act: Call the findByIsbnFiltered method with the mocked ISBN and no fields.
  Assert: Assert that the returned MappingJacksonValue's wrapped value is an instance of NewBookResource and its filter excludes all fields.
Validation:
  The assertion verifies that the method correctly handles the scenario when no fields are specified. This test is essential to ensure that the application does not reveal any information when no fields are specified.

Scenario 3: Test to find a book by an invalid ISBN
Details:
  TestName: testFindByIsbnFilteredWithInvalidIsbn.
  Description: This test is designed to check the findByIsbnFiltered method when given an invalid ISBN. The method should throw an exception.
Execution:
  Arrange: Mock the BookRepository to return null when findByIsbn is called with an invalid ISBN.
  Act: Call the findByIsbnFiltered method with the mocked invalid ISBN and any fields.
  Assert: Assert that an exception is thrown.
Validation:
  The assertion verifies that the method correctly handles the scenario when an invalid ISBN is given. This test is essential to ensure that the application properly validates the input and throws an exception when the ISBN does not exist.
"""
*/

// ********RoostGPT********

package com.baeldung.web.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJacksonValue;
import com.baeldung.model.Book;
import com.baeldung.persistence.BookRepository;
import com.baeldung.web.error.EntityNotFoundException;
import com.baeldung.web.resource.NewBookResource;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.junit.jupiter.api.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baeldung.model.BookView;
import com.baeldung.web.error.Checks;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@ExtendWith(MockitoExtension.class)
public class NewBookControllerFindByIsbnFilteredTest {

	@Mock
	private BookRepository repo;

	@InjectMocks
	private NewBookController controller;

	private Book book;

	@BeforeEach
	public void setUp() {
		book = new Book();
		book.setIsbn("123456789");
		book.setTitle("Test Book");
	}

	@Test
    @Tag("valid")
    public void testFindByIsbnFilteredWithValidFields() {
        when(repo.findByIsbn(anyString())).thenReturn(book);
        String fields = "title,isbn";
        MappingJacksonValue result = controller.findByIsbnFiltered(fields, book.getIsbn());
        assertTrue(result.getValue() instanceof NewBookResource);
        FilterProvider filters = result.getFilters();
        assertNotNull(filters);
        assertTrue(filters.findPropertyFilter("bookFilter", null).isIncluded("title"));
        assertTrue(filters.findPropertyFilter("bookFilter", null).isIncluded("isbn"));
    }

	@Test
    @Tag("valid")
    public void testFindByIsbnFilteredWithNoFields() {
        when(repo.findByIsbn(anyString())).thenReturn(book);
        String fields = "";
        MappingJacksonValue result = controller.findByIsbnFiltered(fields, book.getIsbn());
        assertTrue(result.getValue() instanceof NewBookResource);
        FilterProvider filters = result.getFilters();
        assertNotNull(filters);
        assertFalse(filters.findPropertyFilter("bookFilter", null).isIncluded("title"));
        assertFalse(filters.findPropertyFilter("bookFilter", null).isIncluded("isbn"));
    }

	@Test
    @Tag("invalid")
    public void testFindByIsbnFilteredWithInvalidIsbn() {
        when(repo.findByIsbn(anyString())).thenReturn(null);
        String fields = "title,isbn";
        assertThrows(EntityNotFoundException.class, () -> {
            controller.findByIsbnFiltered(fields, "invalidIsbn");
        });
    }

}