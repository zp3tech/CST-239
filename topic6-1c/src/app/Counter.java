package app;

/**
 * Basic Counter class that increments and gets an integer count value.
 * 
 * @author pahlz
 *
 */
public class Counter {
	static int count = 0;

	/**
	 * Increases the count variable by one, done synchronized to be thread-safe.
	 */
	static synchronized void increment() {
		count++;
	}

	static int getCount() {
		return count;
	}
}
