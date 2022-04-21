package game;

import products.SalableProduct;

public class Commands {

	public String gameDecision(String input) {

		String userIn = "";

		while (!userIn.equals("/leave")) {
			userIn = scnr.nextLine().toLowerCase().trim();
			switch (userIn) {
			case "/store":
				System.out.println("The current store:\n");
//			im.store.viewStore();
				System.out.println(im.store.viewStoreString());
				break;
			case "/cart":
				im.store.viewCart();
				break;
			case "/help":
				// TODO
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
	}
}
