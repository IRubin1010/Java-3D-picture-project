package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 * class plane implements Geometry
 * 
 * @author ISRAEL&YOSEF
 */
public abstract class Plane extends Geometry {
	protected Point point;
	private Vector normalVector;

	// --------------------------------------------Constructors------------------------------------------------------------//
	/**
	 * ctor receive Point point, Vector normalVector for old tests send to super
	 * default (new Color(0, 0, 0), new Material(0, 0, 0))
	 * 
	 * @param point
	 * @param normalVector
	 */
	public Plane(Point point, Vector normalVector) {
		super(new Color(0, 0, 0), new Material(0, 0, 0));// for old tests
		this.point = new Point(point);
		this.normalVector = normalVector.normalized_Vector();
	}

	/**
	 * ctor Build plane from 3 points The integrity of the triangle is in functions
	 * send to super default (new Color(0, 0, 0), new Material(0, 0, 0))
	 * 
	 * @param point1 first point
	 * @param point2 second point
	 * @param point3 third point
	 */
	public Plane(Point point1, Point point2, Point point3) throws IllegalArgumentException {
		super(new Color(0, 0, 0), new Material(0, 0, 0));// for old tests
		this.point = new Point(point2);
		// Build two vectors in the plane from two pairs of points
		// if the points are on the same line - there will be exception
		this.normalVector = point2.substract(point1).crossProduct(point3.substract(point1)).normalized_Vector();
	}

	/**
	 * ctor
	 * 
	 * @param point
	 * @param normalVector
	 */
	public Plane(Point point, Vector normalVector, Color color, Material material) {
		super(color, material);
		this.point = new Point(point);
		this.normalVector = normalVector.normalized_Vector();
	}

	/**
	 * ctor Build plane from 3 points The integrity of the triangle is in functions
	 * 
	 * @param point1 first point
	 * @param point2 second point
	 * @param point3 third point
	 */
	public Plane(Point point1, Point point2, Point point3, Color color, Material material)
			throws IllegalArgumentException {
		super(color, material);
		this.point = new Point(point2);
		// Build two vectors in the plane from two pairs of points
		// if the points are on the same line - there will be exception
		this.normalVector = point2.substract(point1).crossProduct(point3.substract(point1)).normalized_Vector();
	}

	// -------------------------------------------Getters/Setters-------------------------------------------//
	/**
	 * Get point
	 * 
	 * @return point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * getNormalVector
	 * 
	 * @return normalVector
	 */
	public Vector getNormal() {
		return normalVector;
	}
	// -----------------------------------------------Admin---------------------------------------------//

	@Override
	public String toString() {
		return "Plane: " + "point= " + point + ", normalVector= " + normalVector;
	}

	// --------------------------------------------Operations------------------------------------------------------------------//

	/**
	 * @return the normal vector at the point
	 */
	@Override
	public Vector getNormal(Point point) {
		// We assume that a point must be on the surface
		return this.normalVector;
	}

	/**
	 * Find intersections of ray and plane
	 * 
	 * @return all intersections
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> retList;
		Point pointFromRay = new Point(ray.getPoint());
		Vector vectorFromRay = new Vector(ray.getVector());
		GeoPoint geoPoint = new GeoPoint();
		geoPoint.geometry = this;
		Point p;

		if (Util.isZero(vectorFromRay.dotProduct(this.normalVector)))
			// The direction of the ray is the same as the surface - So there is no
			// intersections point
			return EMPTY_LIST;

		if (this.point.equals(pointFromRay)) {
			// if The ray starts at the point of the surface
			retList = new ArrayList<GeoPoint>();
			geoPoint.point = this.point;
			retList.add(geoPoint);
			return retList;
		}

		double t = normalVector.dotProduct(this.point.substract(pointFromRay)) / normalVector.dotProduct(vectorFromRay);
		if (t < 0)
			// if The ray after of the surface
			return EMPTY_LIST;

		retList = new ArrayList<GeoPoint>();
		if (Util.isZero(t)) {
			// if The ray starts at any point of the surface
			geoPoint.point = pointFromRay;
			retList.add(geoPoint);
		} else {
			// if The ray before of the surface
			try {
				geoPoint.point = pointFromRay.add(vectorFromRay.scale(t));
				retList.add(geoPoint);
			} catch (Exception e) {
				geoPoint.point = pointFromRay;
				retList.add(geoPoint);
			}
		}

		return retList;
	}
	
	/**
	 * return the minimum number between 3 number
	 * @param n1
	 * @param n2
	 * @param n3
	 * @return the minimum number
	 */
	protected double getMin(double n1, double n2, double n3) {
		if(n1 > n2) {
			if(n2 > n3)
				return n3;
			else
				return n2;
		}else {
			if(n1 > n3)
				return n3;
			else
				return n1;
		}
	}
	
	/**
	 * return the maximum number between 3 number
	 * @param n1
	 * @param n2
	 * @param n3
	 * @return the maximum number
	 */
	protected double getMax(double n1, double n2, double n3) {
		if(n1 > n2) {
			if(n3 > n1)
				return n3;
			else
				return n1;
		}else {
			if(n3 > n2)
				return n3;
			else
				return n2;
		}
	}

	


}
