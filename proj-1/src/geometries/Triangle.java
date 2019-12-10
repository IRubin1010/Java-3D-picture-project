package geometries;

import java.util.List;
import primitives.*;
import scene.Scene;

/**
 * class Triangle extends Plane
 */
public class Triangle extends Plane {

	private Point point2;
	private Point point3;

	// ------------------------------Constructors----------------------------------------------------------------------//
	/**
	 * ctor
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @throws exception if two points are equal
	 */
	public Triangle(Point point1, Point point2, Point point3, Color color, Material material)
			throws IllegalArgumentException {
		super(point1, point2, point3, color, material);
		this.point2 = new Point(point1);
		this.point3 = new Point(point3);
		setMinMax();
	}

	// -------------------------------Getters/Setters--------------------------------------------------------------//

	/**
	 * Getters
	 * 
	 * @return Point2
	 */
	public Point getPoint2() {
		return point2;
	}

	/**
	 * Getters
	 * 
	 * @return Point3
	 */
	public Point getPoint3() {
		return point3;
	}

	// -----------------------------------------Admin-------------------------------------------------------------------//

	@Override
	public String toString() {
		return "Triangle: " + "point1=" + point + ", point2=" + point2 + ", point3=" + point3;
	}

	// ------------------------------------------Operations------------------------------------------------------------//
	/**
	 * Find intersections of ray and triangle
	 * 
	 * @return all intersections
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> retList = super.findIntersections(ray); // retLisr = all points from plane
		if (retList.isEmpty())
			return EMPTY_LIST; // There are no intersection in plan
		Point point = retList.get(0).point;
		Point p0 = ray.getPoint();
		if (point.equals(p0)) {// the ray starts inside plane so check if its on triangle by reverse epsilon
			// ray = (new Ray(p0.add(ray.getVector().scale(-1)), ray.getVector()));
			// p0 = ray.getPoint();
			p0 = p0.add(ray.getVector().scale(-1));
		}

		Vector v1 = this.point.substract(p0);
		Vector v2 = this.point2.substract(p0);
		Vector v3 = this.point3.substract(p0);

		Vector normal1 = v1.crossProduct(v2);
		Vector normal2 = v2.crossProduct(v3);
		Vector normal3 = v3.crossProduct(v1);

		Vector pSubP0 = point.substract(p0); // the ray starts inside plane--- to check if its on triangel

		double dp1Vector = pSubP0.dotProduct(normal1);
		double dp2Vector = pSubP0.dotProduct(normal2);
		double dp3Vector = pSubP0.dotProduct(normal3);

		if (dp1Vector > 0 && dp2Vector > 0 && dp3Vector > 0 || dp1Vector < 0 && dp2Vector < 0 && dp3Vector < 0)
			return retList; // The point is inside if all dot product have the same sign

		return EMPTY_LIST; // The point is outside ---> no Intersections

	}

	@Override
	protected void setMinMax() {

		double p1X = point.getxCoordinate().get();
		double p1Y = point.getyCoordinate().get();
		double p1Z = point.getzCoordinate().get();

		double p2X = point2.getxCoordinate().get();
		double p2Y = point2.getyCoordinate().get();
		double p2Z = point2.getzCoordinate().get();

		double p3X = point3.getxCoordinate().get();
		double p3Y = point3.getyCoordinate().get();
		double p3Z = point3.getzCoordinate().get();

		minX = getMin(p1X, p2X, p3X);
		maxX = getMax(p1X, p2X, p3X);

		minY = getMin(p1Y, p2Y, p3Y);
		maxY = getMax(p1Y, p2Y, p3Y);

		minZ = getMin(p1Z, p2Z, p3Z);
		maxZ = getMax(p1Z, p2Z, p3Z);
	}
	// -------------------------------old------------//

	/**
	 * old ctor
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @throws exception if two points are equal
	 */
	public Triangle(Point point1, Point point2, Point point3) throws IllegalArgumentException {
		super(point1, point2, point3);
		this.point2 = new Point(point1);
		this.point3 = new Point(point3);
		setMinMax();
	}
}
