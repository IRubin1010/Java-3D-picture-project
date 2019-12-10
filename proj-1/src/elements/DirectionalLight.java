package elements;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class represents Directional light like light from son
 */
public class DirectionalLight extends Light implements LightSource {

	Vector _direction;

	/***************** Constructors **********************/

	/**
	 * constructor
	 * 
	 * @param direction direction of the light
	 * @param color     the color of the light
	 */
	public DirectionalLight(Vector direction, Color color) {
		super(color);
		_direction = new Vector(direction);
	}

	/***************** Operations ************************/

	@Override
	public Color getIntensity(Point point) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point point) {
		return new Vector(_direction).normalized_Vector();
	}

	@Override
	public Vector getD(Point point) {
		return new Vector(_direction).normalized_Vector();
	}

	@Override
	public double getDistance(Point point) {
		return Double.POSITIVE_INFINITY;
	}

}
