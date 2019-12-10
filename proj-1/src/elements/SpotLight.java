package elements;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * this class represent class SpotLight: extends PointLight
 *
 */
public class SpotLight extends PointLight {

	Vector _direction;
	int _pow = 1;

	/***************** Constructor **********************/

	/**
	 * Constructor receive Vector direction, Point position, double Ki, double Kq,
	 * Color color
	 * 
	 * @param direction
	 *            direction of the spot
	 * @param position
	 *            position of the light in scene
	 * @param Kl
	 *            linear factor
	 * @param Kq
	 *            quadratic factor
	 * @param color
	 *            of the light
	 */
	public SpotLight(Vector direction, Point position, double Ki, double Kq, Color color, int pow) {
		super(position, Ki, Kq, color);
		_pow = pow;
		_direction = new Vector(direction).normalized_Vector();
	}

	public SpotLight(Vector direction, Point position, double Ki, double Kq, Color color) {
		super(position, Ki, Kq, color);
		_direction = new Vector(direction).normalized_Vector();
	}

	/***************** Operations ************************/

	@Override
	public Color getIntensity(Point point) {
		double angleBetweenDirectionAndLBetweenDirectionAndL = _direction
				.dotProduct(point.substract(_position).normalized_Vector()); // add
		// y
		// if the direction is negative
		if (angleBetweenDirectionAndLBetweenDirectionAndL < 0)
			return Color.BLACK;

		// IL = IL from pointLight * angelBetweenDirectionAndL
		return super.getIntensity(point).scale(Math.pow(angleBetweenDirectionAndLBetweenDirectionAndL, _pow));
	}

	@Override
	public Vector getL(Point point) {
		return point.substract(_position).normalized_Vector();
	}

	@Override
	public Vector getD(Point point) {
		return new Vector(_direction);
	}
}
