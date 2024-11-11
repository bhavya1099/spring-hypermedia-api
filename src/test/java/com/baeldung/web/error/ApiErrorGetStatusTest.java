
package com.baeldung.web.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;

public class ApiErrorGetStatusTest {

	@Test
	@Tag("valid")
	public void testGetStatusWhenStatusIsSet() {
		ApiError apiError = new ApiError();
		HttpStatus status = HttpStatus.OK;
		apiError.setStatus(status);
		assertEquals(status, apiError.getStatus(),
				"getStatus method should return the correct HttpStatus that was set");
	}

	@Test
	@Tag("boundary")
	public void testGetStatusWhenStatusIsNotSet() {
		ApiError apiError = new ApiError();
		assertNull(apiError.getStatus(), "getStatus method should return null when status is not set");
	}

	@Test
	@Tag("valid")
	public void testGetStatusWhenStatusIsChanged() {
		ApiError apiError = new ApiError();
		HttpStatus initialStatus = HttpStatus.OK;
		apiError.setStatus(initialStatus);
		HttpStatus changedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		apiError.setStatus(changedStatus);
		assertEquals(changedStatus, apiError.getStatus(),
				"getStatus method should return the latest HttpStatus that was set");
	}

}