package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * In this class we will examine inputs and outputs of the Sphere class
 */
public class SphereTests {
	Sphere s1 = new Sphere(4, new Point(0, 0, 0));

	/**
	 * test normal
	 */
	@Test
	public void testNormal() {
		Vector expected = new Vector(1, 0, 0).normalized_Vector();
		assertEquals("normal test", expected, s1.getNormal(new Point(1, 0, 0)));
	}

	/**
	 * In this function we will examine the func findIntersections(ray)
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(4, Point.ZERO);
		Vector vector = new Vector(1, 0, 0);
		Vector v1 = new Vector(1, 0, 0);
		Point p1 = new Point(-4, 0, 0);
		Point p2 = new Point(4, 0, 0);
		Point p1r2 = new Point(Math.sqrt(15) * (-1), 1, 0);
		Point p2r2 = new Point(Math.sqrt(15), 1, 0);
		Ray ray;
		List<GeoPoint> retList = new ArrayList<GeoPoint>();

		// ------------------------------------------------EP----------------------------------------//
		// Test 1 no intersection
		ray = new Ray(new Point(0, 0, 50), vector);
		assertTrue("test 1 - no intersection", sphere.findIntersections(ray).isEmpty());

		// Test 2
		ray = new Ray(new Point(-5, 1, 0), vector);
		retList = sphere.findIntersections(ray);
		assertTrue("test 2 - 2 intersection", retList.get(1).point.equals(new Point((Math.sqrt(15)) * (-1), 1, 0))
				&& retList.get(0).point.equals(new Point(Math.sqrt(15), 1, 0)));

		// Test 3
		ray = new Ray(new Point(0, 1, 0), v1);
		assertEquals("test 3 - ray starts inside the sphere", s1.findIntersections(ray).get(0).point, p2r2);

		// Test 4
		Ray r4 = new Ray(new Point(5, 1, 0), v1);
		// IntersectionsList2 is the intersections of the opposite direction of r4
		List<GeoPoint> intersectionsList2 = s1.findIntersections(new Ray(r4.getPoint(), r4.getVector().scale(-1)));
		retList = s1.findIntersections(r4);
		assertTrue("test 4 - 2 intersections with tail",
				retList.isEmpty() && intersectionsList2.get(1).point.equals(p2r2) && intersectionsList2.get(0).point.equals(p1r2));

		// ------------------------------------------------BVA---------------------------------------//
		// Test 5
		Ray r5 = new Ray(new Point(-5, 0, 0), v1);
		retList = s1.findIntersections(r5);
		assertTrue("test 5 - 2 intersections and passing through the center (the O point)",
				retList.get(1).point.equals(p1) && retList.get(0).point.equals(p2));

		// Test 6
		Ray r6 = new Ray(p1, v1);
		// assertEquals(p1, s1.findIntersections(r6).get(0));
		assertTrue(
				"test 6 - ray starts on the sphere and has 1 intersection and passing through"
						+ "the center (the O point)",
				s1.findIntersections(r6).get(0).point.equals(p1) && s1.findIntersections(r6).get(1).point.equals(p2));

		// Test 7
		Ray r7 = new Ray(p2, v1);
		assertTrue(
				"test 7 - ray starts on the sphere with no other intersection and its' tail is passing through the center (the O point)",
				s1.findIntersections(r7).get(0).point.equals(p2));

		// Test 8
		Ray r8 = new Ray(new Point(5, 0, 0), v1);
		retList = s1.findIntersections(r8);
		intersectionsList2 = s1.findIntersections(new Ray(r8.getPoint(), r8.getVector().scale(-1)));
		assertTrue("test 8 - 2 intersections with ray tail and passing through the center (the O point)",
				retList.isEmpty() && intersectionsList2.get(0).point.equals(p1) && intersectionsList2.get(1).point.equals(p2));

		// Test 9
		Ray r9 = new Ray(Point.ZERO, v1);
		assertTrue("test 9 - ray starts in the center (the O point)", s1.findIntersections(r9).get(0).point.equals(p2));

		// Test 10 ray starts on the sphere and has 1 intersection and NOT passing
		// through the center
		Ray r10 = new Ray(p1r2, v1);
		assertTrue("test 10 - ray starts on the sphere and has 1 intersection and NOT passing through the center",
				s1.findIntersections(r10).get(0).point.equals(p1r2) && s1.findIntersections(r10).get(1).point.equals(p2r2));

		// Test 11
		Ray r11 = new Ray(p2r2, v1);
		assertTrue(
				"test 11 - ray starts on the sphere and has NO intersection and its' tail is NOT passing through the center",
				s1.findIntersections(r11).get(0).point.equals(p2r2));

		// Test 12
		Ray r12 = new Ray(new Point(-2, 0, 4), v1);
		assertTrue("test 12 - ray is tangent to the sphere",
				s1.findIntersections(r12).get(0).point.equals(new Point(0, 0, 4)));

		// Test 13
		Ray r13 = new Ray(new Point(0, 0, 4), v1);
		assertTrue("test 13 - ray is tangent to the sphere and starts on the sphere",
				s1.findIntersections(r13).get(0).point.equals(new Point(0, 0, 4)));

		// Test 14
		Ray r14 = new Ray(new Point(2, 0, 4), v1);
		assertTrue("test 14 - rays tail is tangent to the sphere", s1.findIntersections(r14).isEmpty());

		// Test 15
		Ray r15 = new Ray(new Point(0, 0, 5), v1);
		assertTrue("test 15 - ray is orthogonal to the sphere in the start point", s1.findIntersections(r15).isEmpty());
	}

}
