package multithreaded_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import game.InventoryManager;

public class MultiThreadServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		InventoryManager im = new InventoryManager();
		im.basicInventoryInit();

		try {
			// server "listening" on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// infinite loop for handling client requests
			while (true) {

				// socket obj to receive incoming clients and prints to console
				Socket client = server.accept();
				System.out.println("New client connected: " + client.getInetAddress().getHostAddress());

				// create new thread obj to handle client w/ handler subclass
				ClientHandler clientSocket = new ClientHandler(client);

				// this new thread will handle the/each client separately on its own thread
				new Thread(clientSocket).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class ClientHandler implements Runnable {

		private final Socket clientSocket;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			PrintWriter out = null;
			BufferedReader in = null;

			try {
				// setup in and out streams from client
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				// display messaged received from client to console
				String line;
				while ((line = in.readLine()) != null) {
					System.out.printf(" sent from client: %s\n", line);
					out.println(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
