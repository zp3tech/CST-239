package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import products.SalableProduct;

public class MultiThreadServer {
	public static void main(String[] args) {

		ServerSocket server = null;

		try {
			System.out.println("Server listening on port 1234");
			// server "listening" on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// stocks inventory manager with basic store items on server
			InventoryManager.basicInventoryInit();
			System.out.println("Inventory stocked with basic items");

			// infinite loop for handling client requests
			while (true) {

				// socket obj to receive incoming clients and prints to console
				Socket client = server.accept();

				// create new thread obj to handle client w/ handler subclass
				ClientHandler clientSocket = new ClientHandler(client);

				// this new thread will handle the/each client separately on its own thread
				new Thread(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Contains game logic handling I/O from players and admin. <br>
	 * Players' commands are lowercase. <br>
	 * Admin commands are uppercase.
	 * 
	 * @author pahlz
	 *
	 */
	private static class ClientHandler implements Runnable {

		private final Socket clientSocket;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			PrintWriter out = null;
			BufferedReader in = null;

			try {
				// setup in and out streams from client
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				System.out.println("New client connected from " + clientSocket.getInetAddress().getHostAddress());

				String line;
				while ((line = in.readLine()) != null) {
					// display messaged received from client to console
					// gameDecision handles input and returns string which is printed to out
					System.out.printf("Server received command: %s\n", line);

					switch (line) {
					case "/leave":
						InventoryManager.store.emptyCart();
						return;
					case "/store":
						out.println(InventoryManager.store.viewStoreString());
						break;
					case "/cart":
						out.println(InventoryManager.store.viewCartString());
						break;
					case "/add":
						out.println("Enter the name of product to add to your cart:");
						line = in.readLine();
						SalableProduct prodAdded = InventoryManager.findItem(line, InventoryManager.store.getForSale());
						// checks if itemBought is in store and adds to cart if available
						if (prodAdded != null) {
							InventoryManager.store.addToCart(prodAdded);
							out.println(prodAdded.getName() + " was added to cart.");
						} else
							out.println("The merchant is not selling an item named \"" + line + "\"...");
						break;
					case "/remove":
						out.println("Enter the name of product to remove from cart:");
						line = in.readLine();
						SalableProduct prodRemoved = InventoryManager.findItem(line, InventoryManager.store.getCart());
						if (prodRemoved != null) {
							InventoryManager.store.deleteFromCart(prodRemoved);
							out.println(prodRemoved.getName() + " was removed from your cart.");
						} else
							out.println("You do not have " + line + " in your cart.");
						break;
					case "/checkout":
						out.println(InventoryManager.store.checkout());
						break;
					case "/sort":
						out.println("Enter 0 to sort by name, ascending");
						out.println("Enter 1 to sort by name, descending");
						out.println("Enter 2 to sort by price, ascending");
						out.println("Enter 3 to sort by price, descending");
						line = in.readLine();
						switch (line) {
						case "0":
							InventoryManager.store.setSortSetting(0);
							out.println("sort setting changed.");
							break;
						case "1":
							InventoryManager.store.setSortSetting(1);
							out.println("sort setting changed.");
							break;
						case "2":
							InventoryManager.store.setSortSetting(2);
							out.println("sort setting changed.");
							break;
						case "3":
							InventoryManager.store.setSortSetting(3);
							out.println("sort setting changed.");
							break;
						default:
							out.println("invalid sort setting");

						}
						break;
					case "/SECRET":
						InventoryManager.jsonArmorInit("assets/special-armor.json");
						InventoryManager.jsonWeaponsInit("assets/special-weapons.json");
						out.println("secret items have been added to shop");
						break;
					case "/R":
						// creates JSON file of all items *currently in-game*
						// i.e. will not contain secret items if admin has not stocked them in-game

						// create ArrayList and fill with all items from cart, store, and player's
						// inventory
						ArrayList<SalableProduct> allCurrentItems = new ArrayList<SalableProduct>();
						allCurrentItems.addAll(InventoryManager.store.getCart());
						allCurrentItems.addAll(InventoryManager.store.getForSale());
						allCurrentItems.addAll(InventoryManager.store.getPlayerInv());

						LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

						// creates a JSON file to write all items to
						BufferedWriter fileOut = new BufferedWriter(
								new FileWriter("assets/all-items_" + now.toString().replaceAll(":", "") + ".json"));

						fileOut.write("[");
						for (int i = 0; i < allCurrentItems.size(); i++) {
							if (i == allCurrentItems.size() - 1)
								// exclude comma on last element
								fileOut.write(allCurrentItems.get(i).toJSON());
							else
								fileOut.write(allCurrentItems.get(i).toJSON() + ",");
						}
						fileOut.write("]");
						fileOut.close();
						out.println("JSON file with all current in-game items has been created in assets folder.");
						break;
					case "/LEAVE":
						break;
					default:
						out.println("invalid command");
					}
				}

			} catch (IOException e) {
//				e.printStackTrace();
			} finally {
				System.out.println("Client from " + clientSocket.getInetAddress().getHostAddress()
						+ " has disconnected from server");
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
