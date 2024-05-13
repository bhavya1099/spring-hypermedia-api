

package com.baeldung.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class CartSetPurchasedTest {

	private Cart cart;

	@BeforeEach
	public void setUp() {
		cart = new Cart();
	}

	@Test
	public void testSetPurchasedTrue() {
		cart.setPurchased(true);
		assertEquals(true, cart.isPurchased(), "Purchased attribute should be true after being set to true");
	}

	@Test
	public void testSetPurchasedFalse() {
		cart.setPurchased(false);
		assertEquals(false, cart.isPurchased(), "Purchased attribute should be false after being set to false");
	}

	@Test
	public void testUpdatePurchasedTrueToFalse() {
		cart.setPurchased(true);
		cart.setPurchased(false);
		assertEquals(false, cart.isPurchased(),
				"Purchased attribute should be updated to false after being changed from true to false");
	}

	@Test
	public void testPurchasedSwitching() {
		cart.setPurchased(true);
		cart.setPurchased(false);
		cart.setPurchased(true);
		assertEquals(true, cart.isPurchased(),
				"Purchased attribute should reflect the correct status after being switched back and forth");
	}

}
