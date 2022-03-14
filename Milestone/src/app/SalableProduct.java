package app;

public class SalableProduct {
	private String name;
	private String description;
	private int quantity;
	private float unitPrice;

	public SalableProduct(String name, String description, int quantity, float unitPrice) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public void print() {
		System.out.println();
		System.out.println(name + ": " + description);
		System.out.println("$" + unitPrice + "   " + "Quantity: " + quantity);
		System.out.println("Total Cost: $" + unitPrice * quantity);
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
