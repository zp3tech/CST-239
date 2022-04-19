package game;

import java.io.IOException;

public class ServerThread extends Thread {
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