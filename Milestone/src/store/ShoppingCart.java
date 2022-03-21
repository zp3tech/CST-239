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
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).equals(item)) {
				products.remove(i);
			}
		}
	}

	/**
	 * Prints all items in the current cart followed by the total price of the
	 * entire cart.
	 */
	public void viewCart() {
		float totalPriceInCart = 0;
		System.out.print("Your current cart: ");
		for (SalableProduct item : products) {
			item.print();
			totalPriceInCart += item.getUnitPrice() * item.getQuantity();
		}
		System.out.println("The total price in cart is: $" + totalPriceInCart);
	}

}
