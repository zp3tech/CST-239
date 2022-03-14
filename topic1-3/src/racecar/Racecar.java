package racecar;

import car.Engine;
import car.Tire;

public class Racecar {
	private Tire[] tires;
	private Engine engine;
	public float distanceTraveled = 0;

	public Racecar() {
		tires = new Tire[4];
		for (int i = 0; i < tires.length; i++) {
			tires[i] = new Tire(32);
		}
		engine = new Engine();
	}

	/**
	 * Helper function for startEngine that checks each tire object has greater than
	 * 32 psi
	 * 
	 * @return false if any single tire has less than 32 psi, true otherwise
	 */
	private boolean checkTires() {
		for (int i = 0; i < tires.length; i++) {
			if (tires[i].getPsi() < 32)
				return false;
		}
		return true;
	}

	/**
	 * If any single tire has less than 32 psi, prints error message to console and
	 * doesn't start engine
	 */
	public void startEngine() {
		if (checkTires()) {
			engine.start();
		} else {
			System.out.println("Tires are too low on air pressure to start engine!");
		}
	}

	public void stopEngine() {
		engine.stop();
	}

	/**
	 * Resets distanceTraveled to 0, then runs stopEngine followed by startEngine
	 */
	public void restart() {
		distanceTraveled = 0;
		stopEngine();
		startEngine();
	}

	/**
	 * Race the racecar! Only works if your engine is running.
	 * 
	 * @param speed The speed of racecar as an integer in miles per hour
	 * @param time  The length of time car has raced in seconds
	 * @return Returns the total distance traveled by racecar
	 */
	public float run(int speed, int time) {
		if (!this.engine.isRunning) {
			System.out.println("You're engine is off! The car didn't move...");
			return 0;
		}
		float hours = (float) time / 360;
		distanceTraveled += speed * hours;
		return distanceTraveled;
	}
}
