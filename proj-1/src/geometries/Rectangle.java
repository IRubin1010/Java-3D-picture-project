package geometries;

import java.util.List;
import primitives.Color;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

/**
 * class represents rectangle
 */
public class Rectangle extends Plane {

	Point _a;
	Point _b;
	Point _c;

	/***************** Constructors **********************/

	/**
	 * constructor
	 * 
	 * @param a        point a
	 * @param b        point b
	 * @param c        point c
	 * @param color    emission color of the plane
	 * @param material material of the plane
	 */
	public Rectangle(Point a, Point b, Point c, Color color, Material material) {
		super(a, b, c, color, material);
		_a = new Point(a);
		_b = new Point(b);
		_c = new Point(c);

		Vector ab = _b.substract(_a);
		Vector ac = _c.substract(_a);
		if (!Util.isZero(ab.dotProduct(ac))) {
			throw new IllegalArgumentException("ab is not orthogonal to ac");
		}
		point = _a.add(ab).add(ac);
		setMinMax();
	}

	/***************** Operations ************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see geometries.Plane#findIntersections(primitives.Ray)
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// get plane intersections
		List<GeoPoint> planeIntersection = EMPTY_LIST;
		planeIntersection = super.findIntersections(ray);
		if (planeIntersection.isEmpty())
			return planeIntersection;

		List<GeoPoint> intersectionList = planeIntersection;
		// the point is inside the rectangle if (0 < PA * AB < AB * AB) AND (0 < PA * AC
		// < AC * AC)
		Vector AB = _b.substract(_a);
		Vector AC = _c.substract(_a);

		if (intersectionList.get(0).equals(_a))
			return planeIntersection;

		Vector PA = intersectionList.get(0).point.substract(_a);
		double u = PA.dotProduct(AB);
		double v = PA.dotProduct(AC);
		if (!(u >= 0.0 && u <= AB.dotProduct(AB) && v >= 0.0 && v <= AC.dotProduct(AC)))
			planeIntersection.clear();

		return planeIntersection;
	}

	@Override
	protected void setMinMax() {

		double p1X = _a.getxCoordinate().get();
		double p1Y = _a.getyCoordinate().get();
		double p1Z = _a.getzCoordinate().get();

		double p2X = _b.getxCoordinate().get();
		double p2Y = _b.getyCoordinate().get();
		double p2Z = _b.getzCoordinate().get();

		double p3X = _c.getxCoordinate().get();
		double p3Y = _c.getyCoordinate().get();
		double p3Z = _c.getzCoordinate().get();

		double p4X = point.getxCoordinate().get();
		double p4Y = point.getyCoordinate().get();
		double p4Z = point.getzCoordinate().get();

		minX = getMin(p1X, p2X, p3X, p4X);
		maxX = getMax(p1X, p2X, p3X, p4X);

		minY = getMin(p1Y, p2Y, p3Y, p4Y);
		maxY = getMax(p1Y, p2Y, p3Y, p4Y);

		minZ = getMin(p1Z, p2Z, p3Z, p4Z);
		maxZ = getMax(p1Z, p2Z, p3Z, p4Z);

	}

	/**
	 * return the minimum number between 4 number
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @param n4
	 * @return the minimum number
	 */
	protected double getMin(double n1, double n2, double n3, double n4) {
		double min = super.getMin(n1, n2, n3);
		if (n4 < min)
			return n4;
		return min;
	}

	/**
	 * return the maximum number between 4 number
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @param n4
	 * @return the maximum number
	 */
	protected double getMax(double n1, double n2, double n3, double n4) {
		double max = super.getMax(n1, n2, n3);
		if (n4 > max)
			return n4;
		return max;
	}

}
