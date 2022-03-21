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
		System.out.println("\"Welcome to my store, weary traveler, what would you like to buy?\"");

		im.store.viewStore();
		System.out.println("\nPlease enter an action from the options below:");
		System.out.println("The name of a product to add it to your cart,");
		System.out.println("\"Cart\" to view your current cart,");
		System.out.println("\"Store\" to view the current store,");
		System.out.println("\"Remove\" to remove items from cart, ");
		System.out.println("\"Checkout\" to purchase all items in your cart.");
		System.out.println("\"Leave\" to leave the store without checking out.");

		String userIn = "";

		while (!userIn.equals("leave")) {
			userIn = scnr.nextLine().toLowerCase().trim();
			switch (userIn) {
			case "cart":
				im.store.viewCart();
				break;
			case "store":
				System.out.print("The current store:");
				im.store.viewStore();
				break;
			case "remove":
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
			case "checkout":
				im.store.checkout();
				break;
			case "leave":
				break;
			default:
				SalableProduct prodAdded = im.findItem(userIn, im.store.getForSale());
				// checks if itemBought is in store or not, else adds to cart
				if (prodAdded != null) {
					im.store.addToCart(prodAdded);
					System.out.println(prodAdded.getName() + " was added to your cart.");
				} else {
					System.out.println("The merchant is not selling an item named \"" + userIn + "\"");
				}
			}
		}

		System.out.println("You leave as the merchant continues to eye you greedily.");
		scnr.close();
	}

}
