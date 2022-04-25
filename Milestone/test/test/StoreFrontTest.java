package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import products.SalableProduct;
import store.StoreFront;

public class StoreFrontTest {

	@Test
	public void testAdddAndDeleteFromCart() {
		SalableProduct axe = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		StoreFront store1 = new StoreFront(true);
		StoreFront store2 = new StoreFront(true);

		store1.stock(axe);
		store2.stock(axe);
		store1.addToCart(axe);
		// store1: ForSale is [], cart is [axe]
		// store2: ForSale is [axe], cart is []

		assertFalse(Arrays.equals(store1.getForSale().toArray(), store2.getForSale().toArray()));
		assertFalse(Arrays.equals(store1.getCart().toArray(), store2.getCart().toArray()));

		store1.deleteFromCart(axe);

		// now stores should be identical
		assertArrayEquals(store1.getForSale().toArray(), store2.getForSale().toArray());
		assertArrayEquals(store1.getCart().toArray(), store2.getCart().toArray());
	}

	/**
	 * Also tests sortStore() after emptying the cart back to store.
	 */
	@Test
	public void testEmptyCart() {
		SalableProduct axe = new SalableProduct("axe1", "dull axe", 1, (float) 6.99);
		SalableProduct axe2 = new SalableProduct("axe2", "dull axe", 1, (float) 6.99);
		SalableProduct axe3 = new SalableProduct("axe3", "dull axe", 1, (float) 6.99);
		StoreFront store1 = new StoreFront(true);
		StoreFront store2 = new StoreFront(true);

		store1.stock(axe);
		store1.stock(axe2);
		store1.stock(axe3);
		store2.stock(axe);
		store2.stock(axe2);
		store2.stock(axe3);

		assertArrayEquals(store1.getForSale().toArray(), store2.getForSale().toArray());
		assertArrayEquals(store1.getCart().toArray(), store2.getCart().toArray());

		store1.addToCart(axe);
		store1.addToCart(axe3);
		store1.addToCart(axe2);

		assertFalse(Arrays.equals(store1.getForSale().toArray(), store2.getForSale().toArray()));
		assertFalse(Arrays.equals(store1.getCart().toArray(), store2.getCart().toArray()));

		store1.emptyCart();
		store1.sortStore();

		// now stores should be identical again
		assertArrayEquals(store1.getForSale().toArray(), store2.getForSale().toArray());
		assertArrayEquals(store1.getCart().toArray(), store2.getCart().toArray());

	}

	@Test
	public void testCheckout() {
		SalableProduct axe = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		StoreFront store1 = new StoreFront(true);
		StoreFront store2 = new StoreFront(true);

		store1.stock(axe);
		store2.stock(axe);
		store1.addToCart(axe);

		String output = store1.checkout();

		assertEquals("YOUR RECEIPT:\n" + axe.toString() + "\nYour checkout total is: $6.99", output);

		ArrayList<SalableProduct> testPlyrInv = new ArrayList<>();
		testPlyrInv.add(axe);

		assertArrayEquals(store1.getPlayerInv().toArray(), testPlyrInv.toArray());

		// now store1 and store2 should both have different forSale list
		assertFalse(Arrays.equals(store1.getForSale().toArray(), store2.getForSale().toArray()));
		// but identical carts (both empty)
		assertArrayEquals(store1.getCart().toArray(), store2.getCart().toArray());
	}

}
