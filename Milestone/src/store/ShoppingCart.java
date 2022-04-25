package store;

import java.util.ArrayList;

import products.SalableProduct;

public class ShoppingCart {
	private ArrayList<SalableProduct> products = new ArrayList<SalableProduct>();

	public ArrayList<SalableProduct> getProductsInCart() {
		return products;
	}

	public void addToCart(SalableProduct item) {
		products.add(item);
	}

	public void deleteFromCart(SalableProduct item) {
		products.remove(item);
	}

	public ArrayList<SalableProduct> getCart() {
		return products;
	}

	/**
	 * Prints all items in the current cart followed by the total price of the
	 * entire cart.
	 */
	public void viewCart() {
		float totalPriceInCart = 0;
		System.out.println("Your current cart:\n");
		for (SalableProduct item : products) {
			item.print();
			totalPriceInCart += item.getUnitPrice() * item.getQuantity();
		}
		System.out.println("The total price in cart is: $" + totalPriceInCart);
	}

}
