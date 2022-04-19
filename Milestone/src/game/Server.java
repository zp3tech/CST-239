package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import products.SalableProduct;

/**
 * Initiates a basic storefront on start, with commands "secret" and "R" for
 * admin to use via console.
 * 
 * @author pahlz
 *
 */
public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Scanner scnr;

	public void start(int port) throws IOException {

		System.out.println("Instructions for admin:");
		System.out.println("Type \"secret\" to add secret store items to shop");
		System.out.println("Type \"R\" to create JSON file of all items in game.");
		System.out.println("Type \"stop\" to stop server and exit.");

		String secretItemsStocked = "no";
		boolean sis = false;

		System.out.println("\nServer waiting for client connection...");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();

		System.out.println("Player connected on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		out.println(secretItemsStocked);
		scnr = new Scanner(System.in);

		String adminInput = "";
		while (!adminInput.equals("stop")) {
			adminInput = scnr.nextLine().trim().toLowerCase();
			if (adminInput.equals("secret")) {
				System.out.println("Secret items have been added to the shop.");
				secretItemsStocked = "yes";
				sis = true;
				out.println(secretItemsStocked);
			} else if (adminInput.toUpperCase().equals("R")) {
				// creates and completely fills an Inventory Manager.
				InventoryManager allItems = new InventoryManager();
				allItems.basicInventoryInit();
				try {
					allItems.jsonWeaponsInit("assets/special-weapons.json");
					allItems.jsonArmorInit("assets/special-armor.json");
				} catch (IOException e) {
					e.printStackTrace();
				}

				// creates a JSON file to write all items to
				BufferedWriter fileOut = new BufferedWriter(new FileWriter("assets/all-items.json"));

				fileOut.write("[");
				int counter = 0;
				for (SalableProduct item : allItems.store.getForSale()) {
					counter++;
					if (counter == allItems.store.getForSale().size())
						fileOut.write(item.toJSON());
					else
						fileOut.write(item.toJSON() + ",");
				}
				fileOut.write("]");
				fileOut.close();
			} else {
				System.out.println("invalid command.");
			}
		}

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if (sis) {
				out.println("yes");
			}
		}

		System.out.println("Server is shutting down");
	}

	public void cleanup() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		scnr.close();
	}

	public static void main(String[] args) {
		ServerThread st = new ServerThread();
		st.run();
	}
}
