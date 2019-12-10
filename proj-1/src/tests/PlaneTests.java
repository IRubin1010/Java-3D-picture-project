package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * In this class we will examine inputs and outputs of the plane class
 */
//public class PlaneTests {

////	// represent plane xy
////	Plane plane = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(1, 1, 0));
////
////	/**
////	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
////	 */
////	@Test
////	public void testGetNormalPoint3D() {
////		// point on the plane
////		assertEquals(new Vector(0, 0, 1), plane.getNormal(plane.getPoint()));
////
////		plane = new Plane(new Point(2, 3.2, 5), new Point(1.4, 0.9, -0.1), new Point(1, 1, 0));
////		// more interesting plane and point...
////		assertEquals(1, plane.getNormal(plane.getPoint()).length(), 1e-10);
////	}
//
//	/**
//	 * Test method for
//	 * {@link geometries.Plane#findIntersections(primitives.Point3D)}.
//	 */
//	@Test
//	public void testIntersectionPoints() {
//		Point p0 = new Point(0, 0, 0);
//		Point p1 = new Point(0, 0, 1);
//		// ------------------------------------------EP-TESTS--------------------------------------------------------------//
//
//		// normal case
//		Ray ray = new Ray(new Point(0, 1, 1), new Vector(0, -1 / Math.sqrt(2), -1 / Math.sqrt(2)));
//		assertEquals(p0, plane.findIntersections(ray).get(0).point);
//
//		// Ray starts in the plane
//		ray = new Ray(p0, new Vector(1, 0, 0));
//		assertEquals(0, plane.findIntersections(ray).size());
//
//		// Ray is parallel but not Intersections
//		ray = new Ray(p1, new Vector(1, 0, 0));
//		assertEquals(0, plane.findIntersections(ray).size());
//
//		// -----------------------------------------------------------BVA-TESTS-------------------------------------------------//
//		// Ray is orthogonal && intersect the plane from above
//		ray = new Ray(p1, new Vector(0, 0, -1)); // list includes only p0 - such as test before
//		assertEquals(p0, plane.findIntersections(ray).get(0).point);
//
//		// vec is orthogonal but start in the plane
//		ray = new Ray(p0, new Vector(0, 0, -1)); // list includes only p0 - such as test before
//		assertEquals(p0, plane.findIntersections(ray).get(0).point);
//
//		// vec is orthogonal && intersect the plane from below
//		ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1)); // list includes only p0 - such as test before
//		assertEquals(p0, plane.findIntersections(ray).get(0).point);
//
//		// vec starts in the plane and go outside
//		ray = new Ray(p0, new Vector(1, 1, 1)); // list includes only p0 - such as test before
//		assertEquals(p0, plane.findIntersections(ray).get(0).point);
//
//		// ray start above plane and go in opposite direction
//		ray = new Ray(p1, new Vector(1, 1, 1));
//		assertTrue(plane.findIntersections(ray).isEmpty());
//		// the ray starts in the vertex of plane
//		ray = new Ray(new Point(1, 0, 0), new Vector(0, 0, 1));
//		assertEquals(new Point(1, 0, 0), plane.findIntersections(ray).get(0).point);
//	}
//}
//
//// Plane p = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0,
//// 1));
////
//// // @Test(expected = IllegalArgumentException.class)
//// // public void test_ctor() {
//// // Plane p = new Plane(new Point(1, 2, 3),new Point(2, 4, 6),new Point(3, 6,
//// // 9));
//// // }
//// /**
//// * test ctor
//// */
//// @Test
//// public void testCtor2() {
//// // create legal plane test
//// try {
//// Plane p1 = new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6,
//// 10));
//// assertTrue(true);
//// } catch (IllegalArgumentException e) {
//// fail("Didn't throw exception");
//// }
////
//// // problem points
//// try {
//// Plane p2 = new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6,
//// 9));
//// fail("Didn't throw exception");
//// } catch (IllegalArgumentException e) {
//// assertTrue(true);
//// }
//// }
////
//// /**
//// * test normal
//// */
//// @Test
//// public void testNormal() {
//// Vector expected = new Vector(-1, -1, -1).normalized_Vector();
//// assertEquals("normal test", expected, p.getNormal());
//// }
