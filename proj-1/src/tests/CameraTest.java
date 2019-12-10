package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;
import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Junit to test Camera Class
 */
public class CameraTest {

	Vector vUp1 = new Vector(1, 2, 3);
	Vector vTo1 = new Vector(0, -3, 2);
	Vector vUp2 = new Vector(1, 3, 3);
	Vector vTo2 = new Vector(0, -3, 2);
	Point cameraPoint = new Point(1, 1, 1);
	Camera camera = new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), Point.ZERO);

	/**
	 * test the Constructor
	 */
	@Test
	public void testConstructor() {
		try { // test if fail in case of not orthogonal vectors
			new Camera(vUp2, vTo2, cameraPoint);
			fail("expected IllegalArgumentException");
		} catch (Exception e) {
		}
		try { // test if success in case of orthogonal vectors
			new Camera(vUp1, vTo1, cameraPoint);
		} catch (Exception e) {
			fail("not expected IllegalArgumentException");
		}
	}

	/**
	 * camera test 3X3 & 4X4
	 */
	@Test
	public void testconstructorRay() {

		// camera test
		// view 3X3

		// Checks ray to pixel 1,1
		Ray ray11 = camera.constructRayThroughPixel(3, 3, 0, 0, 100, 150, 150);
		Ray expectedRay11 = new Ray(Point.ZERO, new Vector(-50.0, 50.0, 100.0));
		assertEquals("testing at 3X3 in pixel (1,1)", ray11, expectedRay11);

		// Checks ray to pixel 2,1
		Ray ray21 = camera.constructRayThroughPixel(3, 3, 1, 0, 100, 150, 150);
		Ray expectedRay21 = new Ray(Point.ZERO, new Vector(0, 50, 100));
		assertEquals("testing at 3X3 in pixel (2,1)", ray21, expectedRay21);

		// Checks ray to pixel 1,2
		Ray ray12 = camera.constructRayThroughPixel(3, 3, 0, 1, 100, 150, 150);
		Ray expectedRay12 = new Ray(Point.ZERO, new Vector(-50, 0, 100));
		assertEquals("testing at 3X3 in pixel (1,2)", ray12, expectedRay12);

		// Checks ray to pixel 2,2
		Ray ray22 = camera.constructRayThroughPixel(3, 3, 1, 1, 100, 150, 150);
		Ray expectedRay22 = new Ray(Point.ZERO, new Vector(0, 0, 100));
		assertEquals("testing at 3X3 in pixel (2,2)", ray22, expectedRay22);

		// -------------------------------------------------------------------
		// view 4X4

		// Checks ray to pixel 1,1
		Ray rayS11 = camera.constructRayThroughPixel(4, 4, 0, 0, 100, 200, 200);
		Ray expectedRayS11 = new Ray(Point.ZERO, new Vector(-75, 75, 100));
		assertEquals("testing at 4X4 in pixel (1,1)", rayS11, expectedRayS11);

		// Checks ray to pixel 2,2
		Ray rayS22 = camera.constructRayThroughPixel(4, 4, 1, 1, 100, 200, 200);
		Ray expectedRayS22 = new Ray(Point.ZERO, new Vector(-25, 25, 100));
		assertEquals("testing at 4X4 in pixel (2,2)", rayS22, expectedRayS22);

		// Checks ray to pixel 2,1
		Ray rayS32 = camera.constructRayThroughPixel(4, 4, 1, 0, 100, 200, 200);
		Ray expectedRayS32 = new Ray(Point.ZERO, new Vector(-25, 75, 100));
		assertEquals("testing at 4X4 in pixel (2,1)", rayS32, expectedRayS32);


	}

	/**
	 * intersections view plan 3x3 with sphere & triangle
	 */
	@Test
	public void testRaysConstruction() {

		Ray ray22 = camera.constructRayThroughPixel(3, 3, 1, 1, 100, 150, 150);

		// 2 cuts with sphere
		Sphere s1 = new Sphere(100, new Point(0, 0, 300));
		List<GeoPoint> intersectionsList = s1.findIntersections(ray22);
		assertEquals(2, intersectionsList.size());
		assertEquals(new Point(0, 0, 400), intersectionsList.get(0).point);
		assertEquals(new Point(0, 0, 200), intersectionsList.get(1).point);

		// 1 cuts with sphere
		Sphere s2 = new Sphere(300, new Point(0, 0, 100));
		List<GeoPoint> intersectionsList2 = s2.findIntersections(ray22);
		assertEquals(1, intersectionsList2.size());
		assertEquals(new Point(0, 0, 400), intersectionsList2.get(0).point);

		// 1 cuts with Triangle
		Triangle t1 = new Triangle(new Point(0, 75, 150), new Point(-75, -75, 150), new Point(75, -75, 150));
		List<GeoPoint> intersectionsList3 = t1.findIntersections(ray22);
		assertEquals(1, intersectionsList3.size());
		assertEquals(new Point(0, 0, 150), intersectionsList3.get(0).point);

		// 0 cuts with same Triangle
		Ray ray11 = camera.constructRayThroughPixel(3, 3, 0, 0, 100, 150, 150);
		List<GeoPoint> intersectionsList4 = t1.findIntersections(ray11);
		assertEquals(0, intersectionsList4.size());
	}

}
