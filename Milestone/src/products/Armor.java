package products;

/**
 * Armor is a child of {@link SalableProduct} that has additional integer fields
 * of protection and weight. <br>
 * Protection is an integer value that represents a percent reduction to damage
 * received when player is wearing the Armor. <br>
 * Weight is an integer value that represents how heavy the armor is to carry.
 * 
 * @author pahlz
 *
 */
public class Armor extends SalableProduct {
	private int protection;
	private int weight;

	public Armor(String name, String description, int quantity, float unitPrice, int protection, int weight) {
		super(name, description, quantity, unitPrice);
		this.setProtection(protection);
		this.setWeight(weight);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

}
