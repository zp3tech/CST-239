package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import products.SalableProduct;

/**
 * The GameDriver class houses the main method and contains menu and logic for
 * player to utilize store in the console. Acts as client to server created in
 * Inventory Manager.
 * 
 * @author pahlz
 *
 */
public class GameDriver {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;

//		// attempt to connect to server as a client.
//		try {
//			clientSocket = new Socket("127.0.1", 6666);
//			out = new PrintWriter(clientSocket.getOutputStream(), true);
//			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		} catch (IOException e) {
//			System.out.println("You approach an empty stall... there's a sign up that reads:");
//			System.out.println("There's no SERVER here!!! Go away until I can find one and START their employment!");
//			return;
//		}

		Scanner scnr = new Scanner(System.in);

		// Welcome message
		System.out.println("You approach the merchant's tent and he greets you with a large smile:");
		System.out.println("\"Welcome to my store, weary traveler, what would you like to buy?\"\n");

		InventoryManager im = new InventoryManager();
		im.basicInventoryInit();

		im.store.viewStore();

		System.out.println("Please enter your action using the commands below:");
		System.out.println("/add to add an item to your cart,");
		System.out.println("/remove to remove items from cart, ");
		System.out.println("/cart to view your current cart,");
		System.out.println("/store to view the current store,");
		System.out.println("/checkout to purchase all items in your cart.");
		System.out.println("/leave to leave the store without checking out.");
		System.out.println("/sort to change the sort order of products in the store.");

		String userIn = "";

		while (!userIn.equals("/leave")) {
			userIn = scnr.nextLine().toLowerCase().trim();
			switch (userIn) {
			case "/store":
				System.out.println("The current store:\n");
//				im.store.viewStore();
				System.out.println(im.store.viewStoreString());
				break;
			case "/cart":
				im.store.viewCart();
				break;
			case "/secret":
//				// checks server if secret items have been added to shop
//				out.println("store-ping");
//				try {
//					String fromServ = in.readLine();
//				} catch (SocketTimeoutException e) {
//
//				}
//				if (in.readLine().equals("yes")) {
//					im.jsonArmorInit("assets/special-armor.json");
//					im.jsonWeaponsInit("assets/special-weapons.json");
//				}
			case "/remove":
				System.out.println("Enter the name of product to remove from cart:");
				userIn = scnr.nextLine().toLowerCase().trim();
				SalableProduct prodRemoved = im.findItem(userIn, im.store.getCart());
				if (prodRemoved != null) {
					im.store.deleteFromCart(prodRemoved);
					System.out.println(prodRemoved.getName() + " was removed from your cart.");
				} else {
					System.out.println("You do not have " + userIn + " in your cart.");
				}
				break;
			case "/checkout":
				im.store.checkout();
				break;
			case "/leave":
				im.store.emptyCart();
				System.out.println("All items in your cart are given back to the shopkeeper...");
				break;
			case "/add":
				System.out.println("Enter the name of product to add to your cart:");
				userIn = scnr.nextLine().toLowerCase().trim();
				SalableProduct prodAdded = im.findItem(userIn, im.store.getForSale());
				// checks if itemBought is in store or not, else adds to cart
				if (prodAdded != null) {
					im.store.addToCart(prodAdded);
					System.out.println(prodAdded.getName() + " was added to your cart.");
				} else {
					System.out.println("The merchant is not selling an item named \"" + userIn + "\"");
				}
				break;
			case "/sort":
				System.out.println("Enter 0 to sort by name, ascending");
				System.out.println("Enter 1 to sort by name, descending");
				System.out.println("Enter 2 to sort by price, ascending");
				System.out.println("Enter 3 to sort by price, descending");
				userIn = scnr.nextLine().trim();
				switch (userIn) {
				case "0":
					im.store.setSortSetting(0);
					break;
				case "1":
					im.store.setSortSetting(1);
					break;
				case "2":
					im.store.setSortSetting(2);
					break;
				case "3":
					im.store.setSortSetting(3);
					break;
				default:
					System.out.println("invalid setting.");
				}
				break;

			default:
				System.out.println("invalid command.");
			}
		}

		System.out.println("You leave as the merchant continues to eye you greedily.");
		scnr.close();
//		out.println("close");
//		try {
//			clientSocket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
