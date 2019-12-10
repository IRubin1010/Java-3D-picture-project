package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * class Tube extends RadialGeometry
 */
public class Tube extends RadialGeometry {
	protected Ray ray;

	// -----------------------------------Constructors-------------------------------------------------------------//
	/**
	 * ctor
	 * 
	 * @param radius
	 * @param ray
	 */
	public Tube(double radius, Ray ray) throws IllegalArgumentException {
		super(radius);
		this.ray = new Ray(ray);
	}

	// ----------------------------------Getters/Setters----------------------------------------------------------//
	/**
	 * Getters
	 * 
	 * @return Ray
	 */
	public Ray getRay() {
		return ray;
	}
	// ---------------------------------Admin------------------------------------------------------------------------//

	@Override
	public String toString() {
		return "Tube:" + super.toString() + ray;
	}
	// ---------------------------------------Operations---------------------------------------------------------------------------//
	/**
	 * @param point
	 * @return the normal of the Cylinder on the point
	 */
	@Override
	public Vector getNormal(Point point) {
		double t = this.ray.getVector().dotProduct(point.substract(this.ray.getPoint()));
		Point o = this.ray.getPoint();
		if (t != 0) {
			o = o.add(this.ray.getVector().scale(t));
		}
		return point.substract(o).normalized_Vector();
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setMinMax() {
		// TODO Auto-generated method stub
		
	}

}
