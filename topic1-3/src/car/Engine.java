package car;

public class Engine {
	public boolean isRunning = false;

	public void start() {
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}
}
