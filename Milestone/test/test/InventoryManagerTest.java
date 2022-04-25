package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.InventoryManager;
import products.SalableProduct;

public class InventoryManagerTest {

	@Test
	public void testFindItem() {
		SalableProduct axe = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		SalableProduct sword = new SalableProduct("Sword", "short sword", 1, (float) 10.99);

		InventoryManager.store.stock(axe);
		InventoryManager.store.stock(sword);
		InventoryManager.store.addToCart(sword);

		// there should now be a sword in cart and an axe in forSale
		assertEquals(sword, InventoryManager.findItem("sword", InventoryManager.store.getCart()));
		assertEquals(axe, InventoryManager.findItem("axe", InventoryManager.store.getForSale()));
	}
}
