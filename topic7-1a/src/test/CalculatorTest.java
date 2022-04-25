package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import junit.Calculator;

class CalculatorTest {

	Calculator calc = new Calculator();

	@Test
	void testAdd() {
		assertEquals(3, calc.add(2, 1));
		assertEquals(7, calc.add(5, 2));
//		fail("Not yet implemented");
	}

	@Test
	void testSubtract() {
		assertEquals(1, calc.subtract(3, 2));
		assertEquals(3, calc.subtract(5, 2));
		assertEquals(5, calc.subtract(0, 5)); // test purposefully written to fail
//		fail("Not yet implemented");
	}

	@Test
	void testMultiply() {
		assertEquals(2, calc.multiply(2, 1));
		assertEquals(10, calc.multiply(5, 2));
//		fail("Not yet implemented");
	}

	@Test
	void testDivide() {
		assertEquals(2, calc.divide(2, 1));
		assertEquals(2, calc.divide(5, 2));
//		fail("Not yet implemented");
	}

}
