
package geometries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * interface Intersectable , also Contains a static class GeoPoint
 * 
 * @author Y&I
 *
 */

public abstract class Intersectable {

	protected double minX;
	protected double maxX;
	protected double minY;
	protected double maxY;
	protected double minZ;
	protected double maxZ;
	public double location;

	/**
	 * empty list to return if the list empty to save run time...
	 */
	public static final List<GeoPoint> EMPTY_LIST = new ArrayList<GeoPoint>();

	/**
	 * check if a bounding box is intersected by a ray
	 * 
	 * @param ray ray to intersection with
	 * @return true if bounding box is intersected by the ray else return false
	 */
	protected boolean isIntersectedBox(Ray ray) {

		Point rayPoint = ray.getPoint();
		Point rayDirection = ray.getVector().getPoint();

		double rayPointX = rayPoint.getxCoordinate().get();
		double rayPointY = rayPoint.getyCoordinate().get();
		double rayPointZ = rayPoint.getzCoordinate().get();

		double rayDirX = rayDirection.getxCoordinate().get();
		double rayDirY = rayDirection.getyCoordinate().get();
		double rayDirZ = rayDirection.getzCoordinate().get();

		double tMin = (minX - rayPointX) / rayDirX;
		double tMax = (maxX - rayPointX) / rayDirX;

		if (tMin > tMax) {
			double temp = tMin;
			tMin = tMax;
			tMax = temp;
		}

		double tyMin = (minY - rayPointY) / rayDirY;
		double tyMax = (maxY - rayPointY) / rayDirY;

		if (tyMin > tyMax) {
			double temp = tyMin;
			tyMin = tyMax;
			tyMax = temp;
		}

		if (tMin > tyMax || tMax < tyMin)
			return false;

		if (tyMin > tMin)
			tMin = tyMin;

		if (tyMax < tMax)
			tMax = tyMax;

		double tzMin = (minZ - rayPointZ) / rayDirZ;
		double tzMax = (maxZ - rayPointZ) / rayDirZ;

		if (tzMin > tzMax) {
			double temp = tzMin;
			tzMin = tzMax;
			tzMax = temp;
		}

		if (tMin > tzMax || tMax < tzMin)
			return false;

		return true;
	}

	/**
	 * set the min and max X, Y, Z for each Geometry
	 */
	protected abstract void setMinMax();

	/**
	 * A function that will receive a ray and return points of cutting with geometry
	 * 
	 * @param ray
	 * @return List<GeoPoint> of the all intersections
	 */
	protected abstract List<GeoPoint> findIntersections(Ray ray);
	
	public List<GeoPoint> findBoxedIntersections(Ray ray) {
		return (!Scene._boxing || isIntersectedBox(ray)) ? findIntersections(ray) : EMPTY_LIST;
	}

	/**
	 * static class represent point and the shape of the point
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point point;
	}

	/**
	 * getLocation
	 * 
	 * @return
	 */
	public double getLocation() {
		return location;
	}

	/**
	 * setLocation
	 * 
	 * @param location
	 */
	public void setLocation(double location) {
		this.location = location;
	}

}
