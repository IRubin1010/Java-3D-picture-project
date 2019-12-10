package primitives;

public class Point {
	public static final Point ZERO = new Point(0, 0, 0);

	private Coordinate x, y, z; // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//-------------------------------------Constructors------------------------------------------------------------// 
	/**
	 * ctor by numbers
	 * 
	 * @param x double
	 * @param y double
	 * @param z double
	 */
	public Point(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * ctor by Coordinate
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(Coordinate x, Coordinate y, Coordinate z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * copy ctor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(Point other) {
		this.x = new Coordinate(other.x);
		this.y = new Coordinate(other.y);
		this.z = new Coordinate(other.z);
	}

//-------------------------------------Getters/Setters--------------------------------------------------------------------//
	public Coordinate getxCoordinate() {
		return x;
		// return xCoordinate;
	}

	public Coordinate getyCoordinate() {
		return y;
		// return yCoordinate;
	}

	public Coordinate getzCoordinate() {
		return z;
		// return zCoordinate;
	}

//-----------------------------------------Admin-------------------------------------------------------------------//	
	/**
	 * this func check for equals points
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;

		return this.x.equals(((Point) obj).x) && this.y.equals(((Point) obj).y) && this.z.equals(((Point) obj).z);
	}

	@Override
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ", " // +++++++++++++++++++++++++++++++
				+ z.toString() + ")";
	}

//------------------------------------------Operations------------------------------------------------------------------------------//
	/**
	 * 
	 * @param gets point p Subtract from this point
	 * @return new vector from this subtracted points
	 */
	public Vector substract(Point p) {
		return new Vector(x.get() - p.x.get(), y.get() - p.y.get(), z.get() - p.z.get());

	}

	/**
	 * 
	 * @param gets other vector add vectors points to this point
	 * @return new point from the add of the vector and this point
	 */
	public Point add(Vector other) {
		return new Point(x.get() + (other.getPoint().getxCoordinate().get()),
				y.get() + (other.getPoint().getyCoordinate().get()),
				z.get() + (other.getPoint().getzCoordinate().get()));

	}

	/**
	 * gets squareDistance from other point
	 * 
	 * @param other
	 * @return (double) the square distance from this point
	 */
	public double squareDistance(Point other) {
		double tempX, tempY, tempZ;

		tempX = this.x.get() - other.x.get();
		tempY = this.y.get() - other.y.get();
		tempZ = this.z.get() - other.z.get();

		return tempX * tempX + tempY * tempY + tempZ * tempZ;
	}

	/**
	 * gets sqrt squareDistance from other point
	 * 
	 * @param other
	 * @return (double)the distance from this point
	 */
	public double distance(Point other) {
		return Math.sqrt(squareDistance(other));
	}
}
