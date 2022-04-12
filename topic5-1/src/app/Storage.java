package app;

/**
 * Example of using generics with a storage class that has a getter method and a
 * constructor that sets data of field s.
 * 
 * @author pahlz
 *
 * @param <T>
 */
public class Storage<T> {
	private T s = null;

	/**
	 * Constructor with data set
	 * 
	 * @param s sets data to input with type T
	 */
	public Storage(T s) {
		this.s = s;
	}

	public T getData() {
		return this.s;
	}

	public static void main(String[] args) {
		Storage<String> storage1 = new Storage<String>("Zachary Pahl");
		System.out.println("This is the data " + storage1.getData());
		Storage<Integer> storage2 = new Storage<Integer>(0);
		System.out.println("This is the data " + storage2.getData());
	}

}
