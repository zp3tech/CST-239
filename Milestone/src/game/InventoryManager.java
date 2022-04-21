package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import products.Armor;
import products.Health;
import products.SalableProduct;
import products.Weapon;
import store.StoreFront;

/**
 * This class initializes the server containing the store front that the player
 * will be interacting with in the game.
 * 
 * @author pahlz
 *
 */
public class InventoryManager {
	public static StoreFront store = new StoreFront(true);
	public boolean secrets = false;

	public static void jsonWeaponsInit(String filename) throws IOException {
		ObjectMapper om = new ObjectMapper();
		List<Weapon> inventory = om.readValue(new File(filename), new TypeReference<List<Weapon>>() {
		});
		for (Weapon x : inventory) {
			store.stock(x);
		}
	}

	public static void jsonArmorInit(String filename) throws IOException {
		ObjectMapper om = new ObjectMapper();
		List<Armor> inventory = om.readValue(new File(filename), new TypeReference<List<Armor>>() {
		});
		for (Armor x : inventory) {
			store.stock(x);
		}
	}

	public static void basicInventoryInit() {
		Weapon sword = new Weapon("Sword", "an iron sword of low quality", 1, (float) 9.99, 10);
		Weapon axe = new Weapon("Axe", "an iron axe of low quality", 1, (float) 13.99, 14);
		Armor leather = new Armor("Leather Armor", "light armor made of leather", 1, (float) 25.00, 25, 10);
		Armor plate = new Armor("Plate Armor", "heavy plate armor", 1, (float) 100.00, 50, 75);
		Health redPotion = new Health("Red Potion", "restores 10hp", 2, (float) 5.00, 10);

		store.stock(sword);
		store.stock(axe);
		store.stock(leather);
		store.stock(plate);
		store.stock(redPotion);
	}

	/**
	 * The findItem method is used to find and return a {@link InventoryManager}
	 * object from an ArrayList of SalableProducts.
	 * 
	 * @param itemName The String name of the item to be found.
	 * @param list     The ArrayList of SalableProducts being searched thru.
	 * @return Returns the SalableProduct object found in list; if not found,
	 *         returns null.
	 */
	public static SalableProduct findItem(String itemName, ArrayList<SalableProduct> list) {
		SalableProduct foundItem = null;
		for (SalableProduct item : list) {
			if (item.getName().toLowerCase().equals(itemName)) {
				foundItem = item;
			}
		}
		return foundItem;
	}

	/**
	 * Connects InventoryManager with MultiThreadServer with admin options.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		basicInventoryInit();

		// TODO connect to server
	}
}
