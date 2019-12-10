package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

/**
 * In this class we will examine inputs and outputs of the vector class
 */

public class Vector_tests {
	Vector v1 = new Vector(2, 2, 2);
	Vector v2 = new Vector(1, 3, 3);
	Vector v3 = new Vector(1, 0, 0);
	Vector v4 = new Vector(-1, 0, 0);
	Vector v5 = new Vector(0, 1, 0);
	Vector vTemp;
	Vector expected;

	/**
	 * test of ctor end exeption in (0,0,0)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ctor() {
		expected = new Vector(0.00000000000001, 0, 0);
	}

	/**
	 * ADD testing
	 */
	@Test
	public void test_add() {
		expected = new Vector(3, 5, 5);
		assertEquals("add test", v1.add(v2), expected);

		try {
			// vector (1, 0, 0)+(-1, 0, 0) cent be
			vTemp = v3.add(v4);
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * sub testing
	 */
	@Test
	public void test_substruct() {
		expected = new Vector(1, -1, -1);
		assertEquals("substruct test ", v1.subtract(v2), expected);
		// vector (1, 0, 0) - (1, 0, 0) cent be
		try {
			vTemp = v3.subtract(v3);
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	/**
	 * scale testing
	 */
	@Test
	public void test_scale() {
		double scalar = 2;
		assertEquals("scale test ", new Vector(4, 4, 4), v1.scale(scalar));

		try {
			// vector (0,0,0) cent be
			vTemp = v2.scale(0);
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * test dotProduct
	 */
	@Test
	public void test_dotProduct() {
		// assertTrue((V1.dotProduct(V2) == 14));
		assertEquals(1, v2.dotProduct(v3), 1e-10);
		assertEquals(-2, v1.dotProduct(v4), 1e-10);
		assertEquals(14, v1.dotProduct(v2), 1e-10);
		assertEquals(0.000000000001, v4.dotProduct(v5), 1e-10);
	}

	/**
	 * test crossProduct
	 */
	@Test
	public void test_crossProduct() {
		expected = new Vector(0, -4, 4);
		assertEquals(v1.crossProduct(v2), expected);

		try {
			// check Vector product on the same straight
			vTemp = v2.crossProduct(v2.scale(4));
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	/**
	 * test length
	 */
	@Test
	public void test_length() {
		double expected = Math.sqrt(12);
		assertTrue("length test", v1.length() == expected);
	}

	/**
	 * test Normalize
	 */
	@Test
	public void testNormalize() {
		expected = v1.normalized_Vector();
		assertEquals("testNormalize ", 1, expected.length(), 1e-10);
	}

}
