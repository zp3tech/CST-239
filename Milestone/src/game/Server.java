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

//		switch (userIn) {
//		case "/store":
//			return InventoryManager.store.viewStoreString();
//		case "/cart":
//			return InventoryManager.store.viewCartString();
//
//		case "/add":
//			return "/add";
//			System.out.println("Enter the name of product to add to your cart:");
//			userIn = scnr.nextLine().toLowerCase().trim();
//			SalableProduct prodAdded = im.findItem(userIn, im.store.getForSale());
//			// checks if itemBought is in store or not, else adds to cart
//			if (prodAdded != null) {
//				im.store.addToCart(prodAdded);
//				System.out.println(prodAdded.getName() + " was added to your cart.");
//			} else {
//				System.out.println("The merchant is not selling an item named \"" + userIn + "\"");
//			}
//		case "/remove":
//			System.out.println("Enter the name of product to remove from cart:");
//			Scanner scnr = new Scanner(System.in);
//			userIn = scnr.nextLine().toLowerCase().trim();
//			SalableProduct prodRemoved = InventoryManager.findItem(userIn, InventoryManager.store.getCart());
//			if (prodRemoved != null) {
//				InventoryManager.store.deleteFromCart(prodRemoved);
//				System.out.println(prodRemoved.getName() + " was removed from your cart.");
//			} else {
//				System.out.println("You do not have " + userIn + " in your cart.");
//			}
//			break;
//		case "/checkout":
//			im.store.checkout();
//			break;
//		case "/sort":
//			System.out.println("Enter 0 to sort by name, ascending");
//			System.out.println("Enter 1 to sort by name, descending");
//			System.out.println("Enter 2 to sort by price, ascending");
//			System.out.println("Enter 3 to sort by price, descending");
//			userIn = scnr.nextLine().trim();
//			switch (userIn) {
//			case "0":
//				im.store.setSortSetting(0);
//				break;
//			case "1":
//				im.store.setSortSetting(1);
//				break;
//			case "2":
//				im.store.setSortSetting(2);
//				break;
//			case "3":
//				im.store.setSortSetting(3);
//				break;
//			default:
//				System.out.println("invalid setting.");
//			}
//			break;

//		default:
//			return "invalid command.";
//		}

		System.out.println("Instructions for admin:");
		System.out.println("Type \"secret\" to add secret store items to shop");
		System.out.println("Type \"R\" to create JSON file of all items currently in-game.");
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
				InventoryManager.basicInventoryInit();
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
