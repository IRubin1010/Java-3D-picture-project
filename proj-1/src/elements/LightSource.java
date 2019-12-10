package elements;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface light source
 */
public interface LightSource {
	/**
	 * get intensity of the light on a specific point
	 * 
	 * @param Point point to get the intensity for
	 * @return the intensity of the color
	 */
	public Color getIntensity(Point point);

	/**
	 * get vector from the light to the point
	 * 
	 * @param Point point to get vector to
	 * @return Vector from light position to the point on the geometry
	 */
	public Vector getL(Point point);

	/**
	 * get the direction of the light
	 * 
	 * @param Point point to calculate the direction to
	 * @return vector of the light direction
	 */
	public Vector getD(Point point);

	/**
	 * get the distance from the light to the point
	 * 
	 * @param Point point to calculate the distance
	 * @return distance between the point and the light
	 */
	double getDistance(Point point);
}
