package primitives;


public class Vector {
	private Point point;

	// -------------------------------------Constructors---------------------------------------------------------------------------//
	/**
	 * ctor by numbers
	 * 
	 * @param x Double
	 * @param y Double
	 * @param z Double
	 * @throws IllegalArgumentException
	 */
	public Vector(double x, double y, double z) throws IllegalArgumentException {
		this.point = new Point(x, y, z);
		if (this.point.equals(Point.ZERO))
			throw new IllegalArgumentException("this vector cant be zero");
	}

	/**
	 * ctor by point
	 * 
	 * @param point
	 * @throws IllegalArgumentException
	 */
	public Vector(Point point) throws IllegalArgumentException {
		if (point.equals(Point.ZERO)) {
			throw new IllegalArgumentException("this vector cant be zero");
		}
		this.point = new Point(point);
	}

	/**
	 * copy ctor
	 * 
	 * @param other
	 * @throws exception if the vector is the zero vector
	 */
	public Vector(Vector other) {
		this.point = new Point(other.getPoint());
	}

//----------------------------------------Getters/Setters------------------------------------------------------------------//
	/**
	 * get point
	 * 
	 * @return point
	 */
	public Point getPoint() {
		return this.point;
	}

//-------------------------------------------Admin---------------------------------------------------------------------//
	/**
	 * checks if the vectors are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;

		return this.point.equals(((Vector) obj).point);
	}

	@Override
	public String toString() {
		return "vector: " + point;
	}

	// ---------------------------------------Operations---------------------------------------------------------------------------//

	/**
	 * gets other vector
	 * 
	 * @param other
	 * @return the add of this vectors
	 */
	public Vector add(Vector other) {
		return new Vector(this.point.getxCoordinate().get() + (other.point.getxCoordinate().get()),
				this.point.getyCoordinate().get() + (other.point.getyCoordinate().get()),
				this.point.getzCoordinate().get() + (other.point.getzCoordinate().get()));
	}

	/**
	 * gets other vector
	 * 
	 * @param other
	 * @return the Subtraction of this vectors
	 */
	public Vector subtract(Vector other) {
		return new Vector(this.point.getxCoordinate().get() - (other.point.getxCoordinate().get()),
				this.point.getyCoordinate().get() - (other.point.getyCoordinate().get()),
				this.point.getzCoordinate().get() - (other.point.getzCoordinate().get()));

	}

	/**
	 * gets Vector * number
	 * 
	 * @param num
	 * @return scalar multiplication with this vector
	 */
	public Vector scale(double num) {
		return new Vector(this.point.getxCoordinate().scale(num).get(), this.point.getyCoordinate().scale(num).get(),
				this.point.getzCoordinate().scale(num).get());
	}

	/**
	 * get Vector * other vector
	 * 
	 * @param Vector other
	 * @return (double) dotProduct of the vectors
	 */
	public double dotProduct(Vector other) {
		return this.point.getxCoordinate().get() * other.getPoint().getxCoordinate().get()
				+ this.point.getyCoordinate().get() * other.getPoint().getyCoordinate().get()
				+ this.point.getzCoordinate().get() * other.getPoint().getzCoordinate().get();
	}

	/**
	 * get Vector cross other vector
	 * 
	 * @param other Vector
	 * @return crossProduct of the vectors
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(
				this.point.getyCoordinate().get() * (other.getPoint().getzCoordinate().get())
						- this.getPoint().getzCoordinate().get() * (other.getPoint().getyCoordinate().get()),
				this.point.getzCoordinate().get() * (other.getPoint().getxCoordinate().get())
						- this.getPoint().getxCoordinate().get() * (other.getPoint().getzCoordinate().get()),
				this.point.getxCoordinate().get() * (other.getPoint().getyCoordinate().get())
						- this.getPoint().getyCoordinate().get() * (other.getPoint().getxCoordinate().get()));
	}

	/**
	 * Calculate the length of the vector by func of point
	 * 
	 * @return (decimal) length of the vector
	 */
	public double length() {
		return this.point.distance(Point.ZERO);
	}

	/**
	 * Calculate the square length of the vector by func of point
	 * 
	 * @return (decimal) length of the vector
	 */
	public double squarelength() {
		return this.point.squareDistance(point.ZERO);
	}

	/**
	 * normalized the Vector
	 * 
	 * @return new Vector normalized
	 */
	public Vector normalized_Vector() {
		double norma = 1 / (this.length());
		return new Vector(this.point.getxCoordinate().get() * norma, this.point.getyCoordinate().get() * norma,
				this.point.getzCoordinate().get() * norma);
	}
}
