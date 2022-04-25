package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class connects player to server and runs this console-based game.
 * 
 * @author pahlz
 *
 */
public class GameDriver {

	/**
	 * Handles incoming messages from server and displays to console of user of this
	 * method.
	 * 
	 * @param in The BufferedReader that client uses to receive server's message
	 * @throws IOException
	 */
	public static void readMessage(BufferedReader in) throws IOException {
		String readMessage;
		while ((readMessage = in.readLine()) != null) {
			System.out.println(readMessage);
			if (!in.ready())
				break;
		}
	}

	/**
	 * Prints user commands to console.
	 */
	public static void help() {
		System.out.println("Please enter your action using the commands below:");
		System.out.println("/add to add an item to your cart,");
		System.out.println("/remove to remove items from cart, ");
		System.out.println("/cart to view your current cart,");
		System.out.println("/store to view the current store,");
		System.out.println("/checkout to purchase all items in your cart.");
		System.out.println("/sort to change the sort order of products in the store.");
		System.out.println("/leave to leave the store without checking out.");
		System.out.println("/help to repeat these instructions.");
	}

	/**
	 * Player commands to server are sent in all lowercase.
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static synchronized void main(String[] args) throws ClassNotFoundException, IOException {

		PrintWriter out = null;
		BufferedReader in = null;
		Scanner scnr = new Scanner(System.in);

		// establish connection to server
		try (Socket socket = new Socket("localhost", 1234)) {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = "";

			// Welcome message and user commands
			System.out.println("The merchant greets you with a large smile:");
			System.out.println("\"Welcome to my store, weary traveler, what would you like to buy?\"\n");
			help();

			// collect user's input -> send to server -> print server's reply
			while (!"/leave".equalsIgnoreCase(line)) {
				line = scnr.nextLine().toLowerCase().trim();
				if ("/help".equals(line)) {
					help();
					continue;
				}

				out.println(line);
				out.flush();
				readMessage(in);
			}

			// clean up
			scnr.close();
			out.close();
			in.close();

			// Exit message
			System.out.println("You leave as the merchant continues to eye you greedily.");

		} catch (IOException e) {
			System.out.println("The merchant's stall is empty... there's a sign up that reads:");
			System.out.println("There's no SERVER here!!! Go away until I can find one and START their employment!");
			return;
		}
	}
}
