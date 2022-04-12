package app;

/**
 * Creates a thread that extends the Thread class for demonstration/education.
 * 
 * @author pahlz
 *
 */
public class MyThread1 extends Thread {

	/**
	 * Must override the run() in Thread.
	 */
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("MyThread1 is running iteration " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
