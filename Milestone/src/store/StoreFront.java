package store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import products.SalableProduct;

/**
 * Primary class used to manage sales of items.
 * 
 * @author pahlz
 *
 */
public class StoreFront {
	private boolean isOpen;
	private ShoppingCart cart = new ShoppingCart();
	private ArrayList<SalableProduct> forSale = new ArrayList<SalableProduct>();
	private ArrayList<SalableProduct> playerInv = new ArrayList<SalableProduct>();
	private int sortSetting = 0;
	private String outputStrings = "";

	public StoreFront(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void setSortSetting(int sort) {
		sortSetting = sort;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void stock(SalableProduct item) {
		forSale.add(item);
	}

	public void sell(SalableProduct sold) {
		forSale.remove(sold);
	}

	/**
	 * Prints all items for sale to the console.
	 * <p>
	 * Sort order is determined by sortSetting variable (0 by default): <br>
	 * 0: by name, ascending. <br>
	 * 1: by name, descending. <br>
	 * 2: by price, ascending. <br>
	 * 3: by price, descending. <br>
	 * 
	 */
	public void viewStore() {
		sortStore();
		for (SalableProduct item : forSale) {
			item.print();
		}
	}

	/**
	 * 
	 * @return Contents of store as string in console friendly format
	 */
	public String viewStoreString() {
		outputStrings = "The current store:\n";
		sortStore();
		for (SalableProduct item : forSale) {
			outputStrings = outputStrings.concat(item.toString() + "\n");
		}
		return outputStrings;
	}

	/**
	 * @return The ArrayList containing all items currently for sale in shop.
	 */
	public ArrayList<SalableProduct> getForSale() {
		return forSale;
	}

	/**
	 * @return The ArrayList containing all items purchased by players.
	 */
	public ArrayList<SalableProduct> getPlayerInv() {
		return playerInv;
	}

	public void addToCart(SalableProduct item) {
		cart.addToCart(item);
		this.sell(item);
	}

	public void deleteFromCart(SalableProduct item) {
		cart.deleteFromCart(item);
		this.stock(item);
	}

	public void viewCart() {
		cart.viewCart();
	}

	public String viewCartString() {
		outputStrings = "The current cart:\n";
		for (SalableProduct item : this.getCart()) {
			outputStrings = outputStrings.concat(item.toString() + "\n");
		}
		return outputStrings;
	}

	/**
	 * 
	 * @return The ArrayList containing all items currently in the player's cart.
	 */
	public ArrayList<SalableProduct> getCart() {
		return cart.getProductsInCart();
	}

	/**
	 * Empties cart item by item, returning to store's inventory
	 */
	public void emptyCart() {
		for (int i = 0; i < cart.getCart().size(); i++) {
			forSale.add(cart.getCart().get(i));
		}
		cart.getCart().clear();
	}

	/**
	 * Prints a receipt to the console showing all items from the cart and the total
	 * price, then empties the cart.
	 * 
	 * @return The receipt of all purchased items with total price.
	 */
	public String checkout() {
		float totalPrice = 0;
		String receipt = "YOUR RECEIPT:\n";
		for (SalableProduct item : cart.getProductsInCart()) {
			receipt = receipt.concat(item.toString() + "\n");
			totalPrice += item.getQuantity() * item.getUnitPrice();
			playerInv.add(item);
		}
		receipt = receipt.concat("Your checkout total is: $" + totalPrice);
		cart.getCart().clear();
		return receipt;
	}

	/**
	 * Sorts all goods in store alphabetically, by item name.
	 */
	private void sortByName(ArrayList<SalableProduct> list) {
		list.sort(Comparator.comparing(SalableProduct::getName));
	}

	/**
	 * Sorts all goods in store by price (high to low).
	 */
	private void sortByPrice(ArrayList<SalableProduct> list) {
		list.sort(Comparator.comparing(SalableProduct::getUnitPrice));
	}

	private void sortReverse(ArrayList<SalableProduct> list) {
		Collections.reverse(list);
	}

	public void sortStore() {
		switch (sortSetting) {
		case 0:
			sortByName(forSale);
			break;
		case 1:
			sortByName(forSale);
			sortReverse(forSale);
			break;
		case 2:
			sortByPrice(forSale);
			break;
		case 3:
			sortByPrice(forSale);
			sortReverse(forSale);
			break;
		}
	}
}
