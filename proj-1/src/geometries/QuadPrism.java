/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * 
 * 
 * this class represents Quad Prism
 */
public class QuadPrism extends Intersectable {

	public Rectangle front;
	public Rectangle back;
	public Rectangle up;
	public Rectangle down;
	public Rectangle left;
	public Rectangle right;

	/**
	 * 
	 * ctor
	 * 
	 * @param length
	 * @param width
	 * @param height
	 * @param point    of left up front corner
	 * @param color
	 * @param material
	 */
	public QuadPrism(double length, double width, double height, Point point, Color color, Material material) {

		Vector vToVector = new Vector(0, 0, 1);
		Vector vUpVector = new Vector(0, 1, 0);
		Vector vRightVector = new Vector(1, 0, 0);

		Point a = new Point(point);
		Point b = new Point(point.add(vRightVector.scale(width)));
		Point c = new Point(point.add(vUpVector.scale(-height)));
		Point d = new Point(point.add(vRightVector.scale(width)).add(vUpVector.scale(-height)));
		Point e = new Point(point.add(vToVector.scale(length)));
		Point f = new Point(b.add(vToVector.scale(length)));
		Point g = new Point(c.add(vToVector.scale(length)));
		Point h = new Point(d.add(vToVector.scale(length)));

		front = new Rectangle(a, b, c, new Color(0, 300, 0), material);
		back = new Rectangle(e, f, g, new Color(0, 300, 0), material);
		up = new Rectangle(e, f, a, new Color(0, 0, 300), material);
		down = new Rectangle(g, h, c, new Color(0, 0, 300), material);
		left = new Rectangle(a, e, c, new Color(300, 0, 0), material);
		right = new Rectangle(b, f, d, new Color(300, 0, 0), material);

	}

	/**
	 * 
	 * ctor
	 * 
	 * @param length
	 * @param width
	 * @param height
	 * @param point       of left up front corner
	 * @param color
	 * @param material
	 * @param tVector
	 * @param upVector
	 * @param rightVector
	 */
	public QuadPrism(double length, double width, double height, Point point, Color color, Material material,
			Vector tVector, Vector upVector, Vector rightVector) {

		Vector vToVector = tVector;
		Vector vUpVector = upVector;
		Vector vRightVector = rightVector;

		Point a = new Point(point);
		Point b = new Point(point.add(vRightVector.scale(width)));
		Point c = new Point(point.add(vUpVector.scale(-height)));
		Point d = new Point(point.add(vRightVector.scale(width)).add(vUpVector.scale(-height)));
		Point e = new Point(point.add(vToVector.scale(length)));
		Point f = new Point(b.add(vToVector.scale(length)));
		Point g = new Point(c.add(vToVector.scale(length)));
		Point h = new Point(d.add(vToVector.scale(length)));

		front = new Rectangle(a, b, c, new Color(220, 0, 0), material);
		back = new Rectangle(e, f, g, new Color(0, 200, 0), material);
		up = new Rectangle(e, f, a, new Color(0, 0, 200), material);
		down = new Rectangle(g, h, c, new Color(220, 0, 0), material);
		left = new Rectangle(a, e, c, new Color(0, 200, 0), material);
		right = new Rectangle(b, f, d, new Color(0, 0, 200), material);

	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> iGeoPoints = new ArrayList<Intersectable.GeoPoint>();
		iGeoPoints.addAll(front.findIntersections(ray));
		iGeoPoints.addAll(back.findIntersections(ray));
		iGeoPoints.addAll(up.findIntersections(ray));
		iGeoPoints.addAll(down.findIntersections(ray));
		iGeoPoints.addAll(left.findIntersections(ray));
		iGeoPoints.addAll(right.findIntersections(ray));
		return iGeoPoints;

	}

	@Override
	protected void setMinMax() {
		// TODO Auto-generated method stub

	}

}
