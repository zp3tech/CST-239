package products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A SalableProduct is an object that has name and description as Strings, an
 * integer quantity, and a float unitPrice fields.
 * 
 * @author pahlz
 *
 */
public class SalableProduct implements Comparable<SalableProduct> {
	private String name;
	private String description;
	private int quantity;
	private float unitPrice;

	public SalableProduct() {
		name = "";
		description = "";
		quantity = 0;
		unitPrice = 0f;
	}

	public SalableProduct(String name, String description, int quantity, float unitPrice) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	/**
	 * Converts SalableProduct object into a JSON string
	 * 
	 * @return Returns the string
	 */
	public String toJSON() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(this);
		return json;
	}

	/**
	 * Prints the SalableProduct object in the following format:
	 * <p>
	 * [name]: [description] <br>
	 * $[unitPrice] Quantity: [quantity] <br>
	 * Total Cost: $[unitPrice*quantity]
	 */
	public void print() {
		System.out.println(name + ": " + description);
		System.out.println("$" + unitPrice + "   " + "Quantity: " + quantity);
		System.out.println("Total Cost: $" + unitPrice * quantity);
		System.out.println();
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

	/**
	 * Comparison based on the name of the item and follows alphabetical ordering
	 * rules that ignore case.
	 */
	@Override
	public int compareTo(SalableProduct o) {
		return this.getName().toLowerCase().compareTo(o.getName());
	}

}
