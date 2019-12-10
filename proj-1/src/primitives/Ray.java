package primitives;

public class Ray {
	private Point point;
	private Vector vector;

//------------------------------------------Constructors-------------------------------------------------------------//
	/**
	 * ctor
	 * 
	 * @param point
	 * @param vector
	 */
	public Ray(Point point, Vector vector) {
		this.point = new Point(point);
		this.vector = vector.normalized_Vector();
	}

	/**
	 * copy ctor
	 * 
	 * @param other
	 */
	public Ray(Ray other) {
		this.point =  new Point(other.getPoint());
		this.vector = new Vector(other.getVector());
	}
	

//-----------------------------------------Getters/Setters------------------------------------------------------------//
	public Point getPoint() {
		return this.point;
	}

	public Vector getVector() {
		return this.vector;
	}

//---------------------------------------------Admin-----------------------------------------------------------//
	
	@Override
	public String toString() {
		return "Ray: point=" + point + ", vector=" + vector;
	}

	@Override                                                               // +++++++++++++
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;

		return this.point.equals(((Ray)obj).point)& this.vector.equals(((Ray)obj).vector);
	}

}
