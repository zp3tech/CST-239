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
		System.out.println("MyThread1 is running");
	}
}
