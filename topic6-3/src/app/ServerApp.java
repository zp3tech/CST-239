package app;

public class ServerApp {
	public static void main(String[] args) throws InterruptedException {
		ServerThread st = new ServerThread();

		st.start();

		for (int i = 0; i < 10; i++) {
			System.out.println(".");
			Thread.sleep(4999);
		}
	}
}
