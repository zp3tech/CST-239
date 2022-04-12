package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * To receive and process incoming messages.
 * 
 * @author pahlz
 *
 */
public class Server {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void start(int port) throws IOException {

		// waits to accept client connection
		System.out.println("Waiting for client connection.........");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();

		// setups input buffered reader
		System.out.println("Received a Client connection on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// get input command from client input
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			// period "." shuts server down.
			if (".".equals(inputLine)) {
				System.out.println("Got a message to shut server down");
				out.println("QUIT");
				break;
			} else {
				// sends message to client that command was received successfully
				System.out.println("Received command: " + inputLine);
				out.println("OK");
			}
		}
		System.out.println("Server is shut down");
	}

	/**
	 * Closes all input and output network buffers and sockets.
	 * 
	 * @throws IOException Thrown if anything bad happens from networking classes.
	 */
	public void cleanup() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start(6666);
		server.cleanup();
	}

}
