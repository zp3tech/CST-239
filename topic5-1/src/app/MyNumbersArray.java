package app;

/**
 * Same as example MyArray class except this has E extend Number and prints an
 * array of floats.
 * 
 * @author pahlz
 *
 */
public class MyNumbersArray {
	public <E extends Number> void printArray(E[] inputArray) {
		for (E element : inputArray) {
			System.out.printf("%s ", element);
		}
	}

	public static void main(String[] args) {
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Float[] floatArray = { 0.0f, 1.0f, 2.5f, 3.5f };

		MyNumbersArray ma = new MyNumbersArray();
		System.out.println("Array integerArray contains:");
		ma.printArray(intArray);
		System.out.println("\nArray doubleArray contains:");
		ma.printArray(doubleArray);
		System.out.println("\nArray characterArray contains:");
		ma.printArray(floatArray);
	}
}
