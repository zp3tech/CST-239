package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import products.SalableProduct;

public class SalableProductTest {

	@Test
	public void testToJSON() throws JsonProcessingException {
		SalableProduct test = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		assertEquals(test.toJSON(),
				"{\"name\":\"axe\",\"description\":\"dull axe\",\"quantity\":1,\"unitPrice\":6.99}");
	}

	@Test
	public void testToString() {
		SalableProduct test = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		assertEquals(test.toString(), "axe: dull axe\n$6.99   Quantity: 1\nTotal Cost: $6.99\n");
	}

	@Test
	public void testCompareTo() {
		SalableProduct test = new SalableProduct("axe", "dull axe", 1, (float) 6.99);
		SalableProduct test2 = new SalableProduct("Sword", "short sword", 1, (float) 10.99);
		assertTrue(test.compareTo(test2) < 0);
		assertTrue(test2.compareTo(test) > 0);
	}

}
