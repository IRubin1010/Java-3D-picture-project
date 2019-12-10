package tests;

/**
 * In this class we will examine inputs and outputs of the Cylinder class
 */
import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class CylinderTests {

	/**
	 * test ctor check Also for a case where the radius is less than zero
	 */
	@Test
	public void testCtor() {
		Cylinder cylinder1 = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 5);

		try {
			Cylinder cylinder2 = new Cylinder(-1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 5);
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * get normal test1 test point on the bases end point on casing
	 */
	@Test
	public void getNormalTest1() {
		Cylinder cylinder = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 5);

		// Check The point is at the bottom base
		Vector downExpected = new Vector(0, 0, -1);
		assertEquals("cylinder test", cylinder.getNormal(new Point(1, 0, 0)), downExpected);

		// Check The point is at the top base
		Vector upExpected = new Vector(0, 0, 1);
		assertEquals("cylinder test", cylinder.getNormal(new Point(1, 0, 5)), upExpected);

		// Check The point is at
		Vector midExpected = new Vector(1, 0, 0);
		assertEquals("cylinder test", cylinder.getNormal(new Point(1, 0, 4)), midExpected);
	}

}
