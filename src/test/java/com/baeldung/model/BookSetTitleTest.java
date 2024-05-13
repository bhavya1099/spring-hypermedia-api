

package com.baeldung.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class BookSetTitleTest {

	private Book book;

	@Before
	public void setUp() throws Exception {
		book = new Book();
	}

	@Test
	public void setTitleWithValidTitle() {
		String title = "A Book Title";
		book.setTitle(title);
		assertEquals(title, book.getTitle());
	}

	@Test
	public void setTitleWithEmptyString() {
		String title = "";
		book.setTitle(title);
		assertEquals(title, book.getTitle());
	}

	@Test
	public void setTitleWithNull() {
		String title = null;
		book.setTitle(title);
		assertNull(book.getTitle());
	}

	// Add import java.sql.DataTruncation at the top of the file to fix the "cannot find
	// symbol" error.
	// The test case assumes that the maximum length of the title in the database is 1000
	// characters,
	// which might not be the case. This constraint needs to be confirmed from the
	// database schema.
	@Test(expected = java.sql.DataTruncation.class)
	public void setTitleExceedingDbCharacterLimit() {
		String title = new String(new char[1001]).replace("\0", "A");
		book.setTitle(title);
	}

}
