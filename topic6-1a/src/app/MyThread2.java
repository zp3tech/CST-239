package app;

public class MyThread2 implements Runnable {

	/**
	 * Forced to implement run() from Runnable interface.
	 */
	@Override
	public void run() {
		System.out.println("MyThread2 is running");
	}

}
