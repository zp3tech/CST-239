package app;

/**
 * Creates 1000 threads that each increment the Counter once.
 * 
 * @author pahlz
 *
 */
public class CounterWorker {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("This is the intial value of the Counter " + Counter.getCount());

		int numCounters = 1000;

		// create CounterThread x1000
		CounterThread[] counters = new CounterThread[numCounters];
		for (int i = 0; i < numCounters; i++) {
			counters[i] = new CounterThread();
		}

		// start the CounterThread x1000
		for (int i = 0; i < numCounters; i++) {
			counters[i].start();
		}

		// waits for threads to die (finish)
		for (int i = 0; i < numCounters; i++) {
			counters[i].join();
		}

		System.out.println("This is the end value of the Counter " + Counter.getCount());
	}
}
