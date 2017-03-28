package mytools;

public class LiftOff implements Runnable {

	public static void main(String[] args) {
		new LiftOff().run();
	}

	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;
	private String name = null;

	public LiftOff() {
	}

	public LiftOff(String name) {
		this.name = name;
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String staus() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + ")" + (name == null ? "default name" : name);
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(staus());
			Thread.yield();

		}
	}

}
