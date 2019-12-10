package geometries;

import java.util.ArrayList;
import java.util.List;

/**
 * class Sphere extends RadialGeometry
 */
import primitives.*;
import scene.Scene;
import geometries.*;
import geometries.Intersectable.GeoPoint;

/**
 * class Sphere extends RadialGeometry
 * 
 * @author student
 *
 */
public class Sphere extends RadialGeometry {

	private Point centerPoint;

	// ----------------------------------------Constructors------------------------------------------------//
	/**
	 * ctor receive : radius, Point centerPoint, Color color, Material material
	 * 
	 * @param radius
	 * @param centerPoint
	 */
	public Sphere(double radius, Point centerPoint, Color color, Material material) throws IllegalArgumentException {
		super(radius, color, material);
		this.centerPoint = new Point(centerPoint);
		setMinMax();
	}

	/**
	 * old ctor for old tests receive double radius, Point centerPoint call
	 * super(radius ,new Color(0,0,0), new Material(0.5, 0.5, 20));
	 * 
	 * @param radius
	 * @param centerPoint
	 */
	public Sphere(double radius, Point centerPoint) throws IllegalArgumentException {
		super(radius, new Color(0, 0, 0), new Material(0.5, 0.5, 20));
		this.centerPoint = new Point(centerPoint);
		setMinMax();
	}

	// -----------------------------------------Getters/Setters---------------------------------------//
	/**
	 * Getters
	 * 
	 * @return centerPoint
	 */
	public Point getCenterPoint() {
		return centerPoint;
	}

	// ------------------------------------------------Admin---------------------------------------------//
	@Override
	public String toString() {
		return "Sphere: " + super.toString() + "centerPoint= " + centerPoint;
	}

	@Override
	public Vector getNormal(Point point) {
		// (p-p0)
		return point.substract(this.centerPoint).normalized_Vector();
	}
	// ---------------------------------------Operations-----------------------------------------------------------//

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> retList = new ArrayList<GeoPoint>();
		Point p1; // ret p1
		Point p2; // ret p2

		Vector vectorFromRay = (ray.getVector());
		Point pointFromRay = (ray.getPoint());
		double radiusOfSphere = this.getRadius();
		// point of sphere and point of ray are the same
		if (centerPoint.equals(pointFromRay)) {
			GeoPoint geoPoint1 = new GeoPoint();
			geoPoint1.geometry = this;
			p1 = new Point(pointFromRay.add(vectorFromRay.scale(radiusOfSphere)));
			geoPoint1.point = p1;
			retList.add(geoPoint1);
			return retList;
		}
		// point start on sphere
		if (this.centerPoint.substract(pointFromRay).length() == radiusOfSphere) {
			GeoPoint geoPoint1 = new GeoPoint();
			geoPoint1.geometry = this;
			geoPoint1.point = pointFromRay;
			retList.add(geoPoint1);
			// return retList;
		}

		Vector u = centerPoint.substract(pointFromRay);
		double tm = Util.alignZero(u.dotProduct(vectorFromRay));
		double d = Math.sqrt(u.squarelength() - tm * tm);
		if (d > radiusOfSphere)
			// the ray out of sphere ---> there are no intersections
			return EMPTY_LIST;
		if (d < radiusOfSphere) {
			double th = Math.sqrt(radiusOfSphere * radiusOfSphere - d * d);
			double t1 = Util.alignZero(tm + th);
			double t2 = Util.alignZero(tm - th);
			if (t1 > 0) {
				GeoPoint p = new GeoPoint();
				p.point = new Point(pointFromRay.add(vectorFromRay.scale(t1)));
				p.geometry = this;
				retList.add(p);
			}
			if (t2 > 0 && t1 != t2) {
				GeoPoint geoPoint2 = new GeoPoint();
				geoPoint2.geometry = this;
				p2 = new Point(pointFromRay.add(vectorFromRay.scale(t2)));
				geoPoint2.point = p2;
				retList.add(geoPoint2);
			}
			return retList;
		}
		// if(Util.isZero(d - radiusOfSphere) tangent
		if (tm > 0) {
			GeoPoint geoPoint2 = new GeoPoint();
			geoPoint2.geometry = this;
			p1 = new Point(pointFromRay).add(vectorFromRay.scale(tm));
			geoPoint2.point = p1;
			retList.add(geoPoint2);
		}
		return retList;
	}

	@Override
	protected void setMinMax() {

		minX = centerPoint.getxCoordinate().get() - getRadius();
		maxX = centerPoint.getxCoordinate().get() + getRadius();

		minY = centerPoint.getyCoordinate().get() - getRadius();
		maxY = centerPoint.getyCoordinate().get() + getRadius();

		minZ = centerPoint.getzCoordinate().get() - getRadius();
		maxZ = centerPoint.getzCoordinate().get() + getRadius();

	}
}
