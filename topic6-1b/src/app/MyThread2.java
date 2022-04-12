package app;

public class MyThread2 implements Runnable {

	/**
	 * Forced to implement run() from Runnable interface.
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("MyThread2 is running iteration " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
