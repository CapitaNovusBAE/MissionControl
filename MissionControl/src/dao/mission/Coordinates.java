package dao.mission;

/**
 * Coordinates object
 * @author Vadim khoruzhenko
 *
 */
public class Coordinates {

	private double x;
	private double y;

	/**Constructor
	 * @param x
	 * @param y
	 */
	public Coordinates(final double x, final double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * @param x
	 */
	public void setX(final double x) {
		this.x = x;
	}
	/**
	 * @return y
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * @param y
	 */
	public void setY(final double y) {
		this.y = y;
	}
}
