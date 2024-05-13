

package com.baeldung.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CartIsPurchasedTest {

	private Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
	}

	@Test
	public void isItemPurchased() {
		cart.setPurchased(true);
		Assert.assertTrue(cart.isPurchased());
	}

	@Test
	public void isItemNotPurchased() {
		cart.setPurchased(false);
		Assert.assertFalse(cart.isPurchased());
	}

	@Test
	public void isItemPurchasedDefaultValue() {
		Assert.assertFalse(cart.isPurchased());
	}

}
