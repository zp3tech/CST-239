package products;

/**
 * Weapon is a child of {@link SalableProduct} that has additional integer field
 * dmg that represents how many hitpoints are dealt to an enemy when struck by
 * this weapon.
 * 
 * @author pahlz
 *
 */
public class Weapon extends SalableProduct {
	private int dmg;

	public Weapon() {
		super();
		dmg = 0;
	}

	public Weapon(String name, String description, int quantity, float unitPrice, int dmg) {
		super(name, description, quantity, unitPrice);
		this.setDmg(dmg);
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

}
