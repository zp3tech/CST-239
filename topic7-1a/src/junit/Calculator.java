package junit;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();

		System.out.println("Adding 1 + 2 is " + calc.add(1, 2));
		System.out.println("Subtracting 2 from 1 is " + calc.subtract(2, 1));
		System.out.println("Multiplying 10 and 2 is " + calc.multiply(10, 2));
		System.out.println("Dividing 10 by 2 is " + calc.divide(10, 2));

	}

}
