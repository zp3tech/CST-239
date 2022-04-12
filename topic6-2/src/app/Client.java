package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Connects to remote server per the ip and port params.
	 * 
	 * @param ip   Remote IP address to connect to.
	 * @param port Remote port to connect to.
	 * @throws UnknownHostException Thrown if network resolution fails.
	 * @throws IOException          Thrown if anything bad from any networking
	 *                              classes.
	 */
	public void start(String ip, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(ip, port);

		// creates in/out network buffers to communicate with server
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	/**
	 * Sends message to server
	 * 
	 * @param msg Message string to send.
	 * @return Response back from server.
	 * @throws IOException Thrown if anything bad from any networking classes.
	 */
	public String sendMessage(String msg) throws IOException {
		// sends/prints message to server
		out.println(msg);

		// returns the response from server
		return in.readLine();
	}

	/**
	 * Closes all input and output network buffers and sockets.
	 * 
	 * @throws IOException Thrown if anything bad from any networking classes.
	 */
	public void cleanup() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}

	public static void main(String[] args) throws IOException {
		// creates client and connects it to server
		Client client = new Client();
		client.start("127.0.1", 6666);

		String response;
		for (int i = 0; i < 10; i++) {
			String message;
			if (i != 9) {
				message = "Hello from Client " + i;
			} else {
				message = ".";
			}
			response = client.sendMessage(message);

			// prints server response, and exits if gets QUIT
			System.out.println("Server response was " + response);
			if (response.equals("QUIT"))
				break;
		}

		client.cleanup();
	}

}
