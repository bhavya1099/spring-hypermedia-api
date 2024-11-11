
package com.baeldung.web.error;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChecksCheckEntityExistsTest {

	@Test
	@Tag("valid")
	public void testEntityExists() {
		// Arrange
		String entity = "Test Entity";
		// Act
		String returnedEntity = Checks.checkEntityExists(entity, "Entity not found");
		// Assert
		assertSame(entity, returnedEntity, "The returned entity should be the same as the one created");
	}

	@Test
	@Tag("invalid")
	public void testEntityDoesNotExist() {
		// Arrange
		String entity = null;
		// Assert
		assertThrows(EntityNotFoundException.class, () -> {
			// Act
			Checks.checkEntityExists(entity, "Entity not found");
		}, "EntityNotFoundException should be thrown when the entity is null");
	}

	@Test
	@Tag("invalid")
	public void testEntityDoesNotExistWithSpecificMessage() {
		// Arrange
		String entity = null;
		String message = "Specific entity not found";
		// Assert
		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			// Act
			Checks.checkEntityExists(entity, message);
		});
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(message), "Exception message should contain the specific message");
	}

}