package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * In this class we will examine inputs and outputs of the Triangle class
 */
public class TriangleTests {

	Triangle t = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
	Triangle tr = new Triangle(new Point(0, 0, 0), new Point(2.5, 0, 0), new Point(0, 2.5, 0));
	Vector v1 = new Vector(0, 0, 1);

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint() {
		// get normal to point on the triangle
		assertEquals(v1, tr.getNormal(tr.getPoint2()));
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#findIntersections(primitives.Point3D)}.
	 */ 
	@Test
	public void testIntersectionPoints() {
		// ----------------------------------EP--------------------------------------------------------//

		// Test 1 ray intersects the triangle
		Ray ray = new Ray(new Point(1, 1, -1), v1);
		assertEquals(new Point(1, 1, 0), tr.findIntersections(ray).get(0).point);

		// Test 2 "intersection" is in 1,-1,0 == no intersection
		ray = new Ray(new Point(1, -1, -1), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 3 "intersection is in 4,-1,0 == no intersection (between triangle's
		// vectors-but outside)
		ray = new Ray(new Point(4, -1, -1), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// -------------------------------------------BVA-----------------------------------------------//
		// Test 4 intersection is in p1 (0,0,0) - vertex of triangle. (ray is orthogonal
		// to one of the normals n1 n2 n3)
		ray = new Ray(new Point(0, 0, -1), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 5 the same case but ray start from the vertex
		ray = new Ray(new Point(0, 0, 0), v1);
		assertTrue(tr.findIntersections(ray).isEmpty()); // list already empty!

		// Test 6 intersection is in 0,1,0 - on the edge of triangle
		ray = new Ray(new Point(0, 1, -1), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 7 the same case but ray start from the "intersection point"
		ray = new Ray(new Point(0, 1, 0), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 8 intersection is in 3,0,0 - later on the vector of triangle's edge
		ray = new Ray(new Point(3, 0, -1), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 9 the same case but ray start from the "intersection point"
		ray = new Ray(new Point(3, 0, 0), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 10 the ray starts in the vertex of triangle
		ray = new Ray(new Point(2.5, 0, 0), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Test 11 check ray starts in negative of point end cuts
		ray = new Ray(new Point(2.5, 0, 0), v1);
		assertTrue(tr.findIntersections(ray).isEmpty());

		// Triangle tt = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new
		// Point(0, 0, 1));
		// Ray r = new Ray(new Point(-1, 0, 0.5),new Vector(1, 0, 0));
		// assertEquals(new Point(0, 0, 0.5), tt.findIntersections(ray).get(0));
	}
	// // *******************Intersections tests******************//
	//
	// /**
	// * EP case: the ray begin in the triangle ,there intersect point.
	// */
	//
	// @Test
	// public void intersectTheTriangle() {
	// Triangle triangle = new Triangle(new Point(0, 5, 10), new Point(0, -8, 1),
	// new Point(0, 10, 1));
	//
	// // The ray begin at the plane of triangle.
	// List<Point> list = triangle.findIntersections(new Ray(new Point(0, 0, 3), new
	// Vector(1, 0, 0)));
	// Iterator<Point> iterator = list.iterator();
	// // assertEquals("The intersect point isn't correct", new Point(0, 0, 3),
	// // iterator.next());
	//
	// // The ray begin "before" the plane of triangle.
	// list = triangle.findIntersections(new Ray(new Point(-8, 0, 4), new Vector(1,
	// 0, 0)));
	// iterator = list.iterator();
	// assertEquals("The intersect point isn't correct", new Point(0, 0, 4),
	// iterator.next());
	// }
	//
	// /**
	// * EP case: the ray begin out the triangle ,there is no intersect point.
	// */
	// @Test
	// public void doesntIntersectTheTriangle() {
	// Triangle triangle = new Triangle(new Point(0, 5, 10), new Point(0, -8, 1),
	// new Point(0, 10, 1));
	//
	// // Ray begin parallel to side of triangle.
	// List<Point> list = triangle.findIntersections(new Ray(new Point(0, 14, 5),
	// new Vector(1, -1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to side
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(0, -14, 5), new Vector(1,
	// -1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to side
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(0, 0, -5), new Vector(1,
	// -1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to side
	// of triangle. ", 0, list.size());
	//
	// // Ray begin parallel to corner of triangle.
	// list = triangle.findIntersections(new Ray(new Point(0, 5, 11), new Vector(1,
	// 1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to corner
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(0, -10, 0.5), new
	// Vector(1, 1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to corner
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(0, 12, 0), new Vector(1,
	// 1, 0)));
	// assertEquals("The intersect point isn't correct, ray begin parallel to corner
	// of triangle. ", 0, list.size());
	// }
	//
	// /**
	// * BVA case: the ray begin at or "before" the side of triangle.
	// */
	// @Test
	// public void intersectTheTriangleBeginOnSide() {
	// Triangle triangle = new Triangle(new Point(4, 0, 1), new Point(12, -2, -5),
	// new Point(-5, 12, -2));
	//
	// // Ray begin at side of triangle.
	// List<Point> list = triangle.findIntersections(new Ray(new Point(8, -1, -2),
	// new Vector(1, 1, 0)));
	// Iterator<Point> iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin at side of
	// triangle. ", new Point(8, -1, -2),
	//// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(3.5, 5, -3.5), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin at side of
	// triangle. ", new Point(3.5, 5, -3.5),
	//// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(-0.5, 6, -0.5), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin at side of
	// triangle. ", new Point(-0.5, 6, -0.5),
	//// iterator.next());
	//
	// // Ray begin "before" side of triangle.
	// list = triangle.findIntersections(new Ray(new Point(-2, -11, -2), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin before side of
	// triangle. ", new Point(8, -1, -2),
	//// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(-6.5, -5, -3.5), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin before side of
	// triangle. ", new Point(3.5, 5, -3.5),
	//// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(-10.5, -4, -0.5), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin before side of
	// triangle. ", new Point(-0.5, 6, -0.5),
	//// iterator.next());
	//
	// }
	//
	// /**
	// * BVA case: the ray begin at or "before" the corners of triangle.
	// */
	// @Test
	// public void intersectTheTriangleBeginOnCorners() {
	// Triangle triangle = new Triangle(new Point(0, 6, 0), new Point(5, 2, 0), new
	// Point(3, 0, -2));
	//
	// // Ray begin at corners of triangle.
	// List<Point> list = triangle.findIntersections(new Ray(new Point(0, 6, 0), new
	// Vector(1, 1, 0)));
	// Iterator<Point> iterator = list.iterator();
	// assertEquals("The intersect point isn't correct, ray begin at corner of
	// triangle. ", new Point(0, 6, 0),
	// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(5, 2, 0), new Vector(1,
	// 1, 0)));
	// iterator = list.iterator();
	// assertEquals("The intersect point isn't correct, ray begin at corner of
	// triangle. ", new Point(5, 2, 0),
	// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(3, 0, -2), new Vector(1,
	// 1, 0)));
	// iterator = list.iterator();
	// assertEquals("The intersect point isn't correct, ray begin at corner of
	// triangle. ", new Point(3, 0, -2),
	// iterator.next());
	//
	// // Ray begin "before" corners of triangle.
	// list = triangle.findIntersections(new Ray(new Point(-10, -4, 0), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	//// assertEquals("The intersect point isn't correct, ray begin before corner of
	// triangle. ", new Point(0, 6, 0),
	//// iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(-5, -8, 0), new Vector(1,
	// 1, 0)));
	// iterator = list.iterator();
	// // assertEquals("The intersect point isn't correct, ray begin before corner
	// of
	// // triangle. ", new Point(5, 2, 0),
	// // iterator.next());
	//
	// list = triangle.findIntersections(new Ray(new Point(-7, -10, -2), new
	// Vector(1, 1, 0)));
	// iterator = list.iterator();
	// // assertEquals("The intersect point isn't correct, ray begin before corner
	// of
	// // triangle. ", new Point(3, 0, -2),
	// // iterator.next());
	//
	// }
	//
	// /**
	// * BVA case: the ray begin at or "before" the tail of triangle sides.
	// */
	// @Test
	// public void doesntIntersectTheTriangleBeginOnTails() {
	// Triangle triangle = new Triangle(new Point(-5, 0, -1), new Point(-2, 0, -5),
	// new Point(-3.5, 1, -1));
	//
	// // Ray begin at tail of triangle sides (two sides of tail).
	// List<Point> list = triangle.findIntersections(new Ray(new Point(4, 0, -13),
	// new Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(-20, 0, 19), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(-6.5, 3, 7), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(5.5, -5, -25), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(-8, -2, -1), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(4, 6, -1), new Vector(-1,
	// 1, 0)));
	// assertEquals("Should be no intersect point , ray begin at tail of sides of
	// triangle. ", 0, list.size());
	//
	// // Ray begin "before" tail of triangle sides (two sides of tail).
	// list = triangle.findIntersections(new Ray(new Point(16, -12, -13), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin before tail of sides
	// of triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(-8, -12, 19), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin before tail of sides
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(1.5, -5, 7), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin on tail of sides of
	// triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(13.5, -13, -25), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin before tail of sides
	// of triangle. ", 0, list.size());
	//
	// list = triangle.findIntersections(new Ray(new Point(0, -10, -1), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin on tail of sides of
	// triangle. ", 0, list.size());
	// list = triangle.findIntersections(new Ray(new Point(12, -2, -1), new
	// Vector(-1, 1, 0)));
	// assertEquals("Should be no intersect point , ray begin before tail of sides
	// of triangle. ", 0, list.size());
	// }
	//
	// /**
	// * BVA case: ray converges with the plane of triangle.
	// */
	// @Test
	// public void rayConvergesWithThePlane() {
	// Triangle triangle = new Triangle(new Point(4, 0, 0), new Point(-6, 0, 0), new
	// Point(0, 0, 8));
	//
	// // BVA: ray converges with the plane of triangle.
	// List<Point> list = triangle.findIntersections(new Ray(new Point(-4, 0, 0),
	// new Vector(1, 0, 0)));
	// assertEquals("Should be no intersection points, ray converges with the plane
	// of triangle. ", 0, list.size());
	// }

}
