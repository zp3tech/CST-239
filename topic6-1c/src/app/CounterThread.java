package app;

import java.util.Random;

/**
 * Thread for Counter.
 * 
 * @author pahlz
 *
 */
public class CounterThread extends Thread {

	/**
	 * Thread is slept for a random amount of time before incrementing the counter.
	 */
	public void run() {
		try {
			Random rand = new Random();
			int sleeper = rand.ints(1, (1000 + 1)).findFirst().getAsInt();
			Thread.sleep(sleeper);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Counter.increment();
	}
}
