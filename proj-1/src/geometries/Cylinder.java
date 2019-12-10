package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;
/**
* class Cylinder
*/
public class Cylinder extends Tube {
	private double height;

	// ------------------------------------------------Constructors---------------------------------------------------------//
	/**
	 * ctor
	 * 
	 * @param radius
	 * @param ray
	 * @param from
	 * @param to
	 */
	public Cylinder(double radius, Ray ray, double height) throws IllegalArgumentException {
		super(radius, ray);
		this.height = height;
	}

	// ------------------------------------------------Getters/Setters-------------------------------------------------------//

	/**
	 * getTo
	 * 
	 * @return (double) to
	 */
	public double getH() {
		return height;
	}

	// --------------------------------------------------Admin-------------------------------------------------------------//
	@Override
	public String toString() {
		return "Cylinder: " + super.toString() + " height =" + height;
	}

	/**
	 * @param point
	 * @return the normal of the Cylinder on the point
	 */
	@Override
	public Vector getNormal(Point p1) {
		Vector v = this.ray.getVector();
		Point p0 = this.ray.getPoint();
		
		double t = p1.substract(p0).dotProduct(v); //(p-p0)*vector of ray
		if (isZero(t))
			return v.scale(-1); // The point is at the bottom base
		if (isZero(t-height)) return v;    //The point is at the top base

		return super.getNormal(p1);
	}
}
