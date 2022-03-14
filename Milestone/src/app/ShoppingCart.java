package app;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<SalableProduct> products = new ArrayList<SalableProduct>();

	public ArrayList<SalableProduct> getProducts() {
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

}
