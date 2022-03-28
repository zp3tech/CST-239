package game;

import java.util.Scanner;

import products.SalableProduct;

/**
 * The GameDriver class houses the main method and contains menu and logic for
 * player to utilize store in the console.
 * 
 * @author pahlz
 *
 */
public class GameDriver {
	public static InventoryManager im = new InventoryManager();

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		// Welcome message
		System.out.println("You approach the merchant's tent and he greets you with a large smile:");
		System.out.println("\"Welcome to my store, weary traveler, what would you like to buy?\"\n");

		im.store.viewStore();

		System.out.println("Please enter your action using the commands below:");
		System.out.println("/add to add an item to your cart,");
		System.out.println("/remove to remove items from cart, ");
		System.out.println("/cart to view your current cart,");
		System.out.println("/store to view the current store,");
		System.out.println("/checkout to purchase all items in your cart.");
		System.out.println("/leave to leave the store without checking out.");

		String userIn = "";

		while (!userIn.equals("/leave")) {
			userIn = scnr.nextLine().toLowerCase().trim();
			switch (userIn) {
			case "/cart":
				im.store.viewCart();
				break;
			case "/store":
				System.out.println("The current store:\n");
				im.store.viewStore();
				break;
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
			default:
				System.out.println("invalid command.");
			}
		}

		System.out.println("You leave as the merchant continues to eye you greedily.");
		scnr.close();
	}

}
