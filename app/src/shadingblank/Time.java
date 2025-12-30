package shadingblank;

public class Time {

	private double currentTime;
	private double lastTime;

	private double deltaTime;

	private double mult;

	public Time() {
		mult = 1.0;
		update();
	}

	public void update() {

		currentTime = System.nanoTime() * mult;

		deltaTime = (currentTime - lastTime) / 1_000_000_000.0;

		lastTime = currentTime;

	}

	public double getTime() {
		return currentTime;
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setMultTime(double value) {
		mult = value;
	}
}