package geometries;

import static primitives.Util.isZero;

import primitives.Color;
import primitives.Material;

/**
 * class RadialGeometry implements Geometry
 */

abstract class RadialGeometry extends Geometry {

	private double radius;

	// --------------------------------------------Constructors------------------------------------------------------------//
	/**
	 * temporary ctor Checks that the radius is positive ,for old tests
	 * 
	 * @param radius
	 */
	public RadialGeometry(double radius) throws IllegalArgumentException {
		super(new Color(0,0,0), new Material(0.5, 0.5, 20));// random values for old tests
		if (radius < 0 || isZero(radius)) {
			throw new IllegalArgumentException("radius cant be negativ");
		} else {
			this.radius = radius;
		}

	}

	/**
	 * ctor receive radius, Color, Material Checks that the radius is positive
	 * 
	 * @param radius
	 */
	public RadialGeometry(double radius, Color color, Material material) throws IllegalArgumentException {
		super(color, material);
		if (radius < 0 || isZero(radius)) {
			throw new IllegalArgumentException("radius cant be negativ");
		} else {
			this.radius = radius;
		}

	}

	/**
	 * copy ctor
	 * 
	 * @param other
	 */
	public RadialGeometry(RadialGeometry other) {
		super(other.getEmmission(), other.getMaterial());
		this.radius = other.getRadius();
	}

	// ---------------------------------------------Getters/Setters-------------------------------------------------//
	/**
	 * Getters
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}
}
//	// ------------------------------------------------Admin--------------------------------------------------------------//
//	@Override
//	public String toString() {
//		return "radius= " + radius + " ";
//	}
//}
