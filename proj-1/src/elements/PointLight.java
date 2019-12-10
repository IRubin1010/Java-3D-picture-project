/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * this class represent PointLight: extends Light implements LightSource
 *
 */
public class PointLight extends Light implements LightSource {

	Point _position;
	double _kC, _kL, _kQ;

	/***************** Constructor **********************/

	/**
	 * Constructor receive Point position, double Ki, double Kq, Color color.
	 * 
	 * make _kC const to 1
	 * 
	 * @param position position of the light in scene
	 * @param Kl       linear factor
	 * @param Kq       quadratic factor
	 * @param color    of the light
	 */
	public PointLight(Point position, double Ki, double Kq, Color color) {
		super(color);
		_position = new Point(position);
		_kC = 1;
		_kL = Ki;
		_kQ = Kq;
	}

	/***************** Operations ************************/

	@Override
	public Color getIntensity(Point point) {
		// IL = (I0*1/kc + kl*d+kq*d^2)
		double distanceFromPoint = _position.distance(point);
		double k = _kC + _kL * distanceFromPoint + _kQ * distanceFromPoint * distanceFromPoint;
		return getIntensity().reduce(k);
	}

	@Override
	public Vector getL(Point point) {
		return point.substract(_position).normalized_Vector();
	}

	@Override
	public Vector getD(Point point) {
		return getL(point);
	}

	@Override
	public double getDistance(Point point) {
		return point.distance(_position);
	}
}
