package app;

import java.util.ArrayList;

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

	public void add(SalableProduct item) {
		cart.addToCart(item);
	}

	public void remove(SalableProduct item) {
		cart.deleteFromCart(item);
	}

	public void checkout() {
		ArrayList<SalableProduct> purchased = cart.getProducts();
		System.out.println("Your Receipt:");
		for (SalableProduct item : purchased) {
			item.print();
		}
	}
}
