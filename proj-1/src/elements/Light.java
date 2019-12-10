
package elements;

import primitives.Color;
import primitives.Point;

/**
 * this abstract class represent Light
 *
 */
public abstract class Light {
	protected Color _color;

	/***************** Constructor **********************/

	/**
	 * constructor receive Color
	 * 
	 * @param color the color of the light
	 */
	public Light(Color color) {
		_color = new Color(color);
	}

	/***************** Operations ************************/

	/**
	 * get the intensity of the light
	 * 
	 * @return the real intensity
	 */
	public Color getIntensity() {
		return _color;
	}

}