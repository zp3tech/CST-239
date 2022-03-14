package game;

import java.util.Scanner;

import racecar.Racecar;

public class GameDriver {

	public static void displayMenu() {
		System.out.println(
				"Enter \"race\" to race your car, \"restart\" to race again, or \"stop\" to turn off your engine and quit.");
	}

	public static void main(String[] args) {
		Scanner userIn = new Scanner(System.in);
		Racecar rc = new Racecar();

		System.out.println("Welcome to the racetrack! Enter 'Start' to start your engine and begin the race!");

		String input = userIn.nextLine().trim();
		while (!input.equalsIgnoreCase("start")) {
			System.out.println("Your engine doesn't seem to be starting...");
			input = userIn.nextLine();
		}
		if (input.equalsIgnoreCase("start")) {
			rc.startEngine();
			System.out.println("VROOOOOOM! Your engine fires to life!");
		}

		displayMenu();
		input = userIn.nextLine().trim();

		while (!input.equalsIgnoreCase("stop")) {
			if (input.equalsIgnoreCase("race")) {
				System.out.print("Enter the speed in mph you want to go for the race: ");
				int speed = (int) userIn.nextInt();
				System.out.print("Enter how many seconds you'll be holding down the gas pedal for: ");
				int time = (int) userIn.nextInt();
				float distance = rc.run(speed, time);
				System.out.println("Whoa! you've traveled " + distance + " miles down the racetrack this race!\n");
				displayMenu();
			} else if (input.equalsIgnoreCase("restart")) {
				rc.restart();
				System.out.println(
						"Your racecar was stopped and has been towed back to the startline. It's ready to race again!\n");
				displayMenu();
			} else if (input.equalsIgnoreCase("stop")) {
				break;
			}
			input = userIn.nextLine().trim();
		}

		System.out.println("Thanks for racing today!");

		userIn.close();
	}
}
