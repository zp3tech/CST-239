package store;

import java.util.ArrayList;

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

	public StoreFront(boolean isOpen) {
		this.isOpen = isOpen;
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
	 */
	public void viewStore() {
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
}
