package products;

/**
 * Health is a child of {@link SalableProduct} that has the additional integer
 * field of healthRegen that represents how many health points are recovered
 * when this item is used by player.
 * 
 * @author pahlz
 *
 */
public class Health extends SalableProduct {
	private int healthRegen;

	public Health(String name, String description, int quantity, float unitPrice, int healthRegen) {
		super(name, description, quantity, unitPrice);
		this.setHealthRegen(healthRegen);
	}

	public int getHealthRegen() {
		return healthRegen;
	}

	public void setHealthRegen(int healthRegen) {
		this.healthRegen = healthRegen;
	}

}
