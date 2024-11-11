
package com.baeldung.web.controller;

import com.baeldung.web.resource.RootResource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
public class RootControllerRootTest {

	@Test
	@Tag("valid")
	public void testRootMethodReturnsRootResourceInstance() {
		// Arrange
		RootController rootController = new RootController();
		// Act
		Object result = rootController.root();
		// Assert
		assertTrue(result instanceof RootResource, "Result should be an instance of RootResource");
	}

	@Test
	@Tag("valid")
	public void testRootMethodDoesNotReturnNull() {
		// Arrange
		RootController rootController = new RootController();
		// Act
		RootResource result = rootController.root();
		// Assert
		assertNotNull(result, "Root method should not return null");
	}

}