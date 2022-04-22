package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import products.Armor;
import products.Health;
import products.SalableProduct;
import products.Weapon;
import store.StoreFront;

/**
 * This class connects an admin to the server.
 * 
 * @author pahlz
 *
 */
public class InventoryManager {
	public static StoreFront store = new StoreFront(true);

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
			if (item.getName().equalsIgnoreCase(itemName)) {
				foundItem = item;
			}
		}
		return foundItem;
	}

	/**
	 * Handles incoming messages from server and displays to console of user of this
	 * method.
	 * 
	 * @param in The BufferedReader that client uses to receive server's message
	 * @throws IOException
	 */
	public static void readMessage(BufferedReader in) throws IOException {
		String readMessage;
		while ((readMessage = in.readLine()) != null) {
			System.out.println(readMessage);
			if (!in.ready())
				break;
		}
	}

	/**
	 * Connects InventoryManager with MultiThreadServer with admin options. <br>
	 * Admin commands are sent to server in all caps.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static synchronized void main(String[] args) throws IOException {

		PrintWriter out = null;
		BufferedReader in = null;
		Scanner scnr = new Scanner(System.in);

		try (Socket socket = new Socket("localhost", 1234)) {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = "";

			// connection message and admin instructions
			System.out.println("Admin connected to server on " + socket.getPort());
			System.out.println("Instructions for admin:");
			System.out.println("Type /secret to add secret store items to shop.");
			System.out.println("Type /r to create JSON file of all items currently in-game.");
			System.out.println("Type /leave to exit.");

			// collect user's input -> send to server -> print server's reply
			while (!"/leave".equalsIgnoreCase(line)) {
				line = scnr.nextLine().toUpperCase().trim();
				out.println(line);
				out.flush();
				readMessage(in);
			}

		} catch (IOException e) {
			System.out.println("Check if server is running.");
			e.printStackTrace();
		} finally {
			// exit message
			scnr.close();
			out.close();
			in.close();
			System.out.println("Thank you for your administration.");
		}
	}
}
