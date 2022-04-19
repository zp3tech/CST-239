package app;

import java.io.IOException;

public class ServerThread extends Thread {

	/**
	 * Creates an instance of Server, then starts it on port 6666, then cleanup on
	 * exit.
	 */
	public void run() {
		Server server = new Server();

		try {
			server.start(6666);
			server.cleanup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
