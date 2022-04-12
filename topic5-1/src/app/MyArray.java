package app;

/**
 * An example class for learning generics containing a printArray method that
 * prints all elements of array separated by spaces.
 * 
 * @author pahlz
 *
 */
public class MyArray {
	public <E> void printArray(E[] inputArray) {
		for (E element : inputArray) {
			System.out.printf("%s ", element);
		}
	}

	public static void main(String[] args) {
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

		MyArray ma = new MyArray();
		System.out.println("Array integerArray contains:");
		ma.printArray(intArray);
		System.out.println("\nArray doubleArray contains:");
		ma.printArray(doubleArray);
		System.out.println("\nArray characterArray contains:");
		ma.printArray(charArray);
	}
}
