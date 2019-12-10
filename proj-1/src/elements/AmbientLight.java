package elements;

import primitives.Color;

/**
 * This class represents fill light for a pixel
 */
public class AmbientLight extends Light {

	double _Ka;

//------------------------------------Constructor----------------------------------------------------------//

	/**
	 * constructor with 4 double parameters
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param Ka    factor of light
	 */
	public AmbientLight(double red, double green, double blue, double Ka) {
		super(new Color(red, green, blue).scale(Ka));
		_Ka = Ka;
	}

	/**
	 * constructor with Color and Ka
	 * 
	 * @param color
	 * @param Ka    factor of light
	 */
	public AmbientLight(Color color, double Ka) {
		super(color.scale(Ka));
		_Ka = Ka;
	}
}
