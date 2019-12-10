package tests;

/**
 * In this class we will examine inputs and outputs of the Tube class
 */
import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class TubeTests {

	/**
	 * test ctor chek Also for a case where the radius is less than zero
	 */
	@Test
	public void testCtor() {

		// create legal tube test
		try {
			new Tube(1, new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			fail("Didn't throw exception");
		}

		// create negative radius tube test
		try {
			new Tube(-1, new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * get normal test1
	 */
	@Test
	public void getNormalTest1() {
		Tube tube = new Tube(1, new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
		Vector expected = new Vector(0, 1, 0);
		assertEquals("tube test", expected, tube.getNormal(new Point(0, 1, 0)));
	}

	/**
	 * get_normal_test2
	 */
	@Test
	public void get_normal_test2() {
		Tube tube = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
		Vector expected = new Vector(1, 0, 0);
		assertEquals("tube2 test", tube.getNormal(new Point(1, 0, 0)), expected);
	}

}
