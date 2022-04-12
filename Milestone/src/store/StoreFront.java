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
	private ShoppingCart cart = new ShoppingCart();
	private boolean isOpen;
	private ArrayList<SalableProduct> forSale = new ArrayList<SalableProduct>();
	private int sortSetting = 0;

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
	 * Sort order is determined by sortSetting variable: <br>
	 * 0: by name, ascending. <br>
	 * 1: by name, descending. <br>
	 * 2: by price, ascending. <br>
	 * 3: by price, descending. <br>
	 * 
	 */
	public void viewStore() {
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
		for (SalableProduct item : forSale) {
			item.print();
		}
	}

	/**
	 * @return The ArrayList containing all items currently for sale in shop.
	 */
	public ArrayList<SalableProduct> getForSale() {
		return forSale;
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
		for (SalableProduct item : cart.getCart()) {
			deleteFromCart(item);
		}
	}

	/**
	 * Prints a receipt to the console showing all items from the cart and the total
	 * price, then empties the cart.
	 */
	public void checkout() {
		ArrayList<SalableProduct> purchased = cart.getProductsInCart();
		float totalPrice = 0;
		System.out.println("Your Receipt:");
		for (SalableProduct item : purchased) {
			item.print();
			totalPrice += item.getQuantity() * item.getUnitPrice();
		}
		System.out.println("Your total price is: $" + totalPrice);
		cart = new ShoppingCart();
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
}
