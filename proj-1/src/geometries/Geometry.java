/**
 * abstract class for all shapes
 */
package geometries;

import primitives.Color;
import primitives.Material;
/**
 *  interface class Geometry 
 */
import primitives.Point;
import primitives.Vector;

public abstract class Geometry extends Intersectable {

	Color _emission;
	Material _material;

	/***************** Constructors **********************/

	/**
	 * constructor receive Color color, Material material
	 * 
	 * @param color    the emission color of the geometry
	 * @param material material of the geometry
	 */
	public Geometry(Color color, Material material) {
		_emission = color;
		_material = material;
	}

	/**
	 * copy constructor
	 * 
	 * @param other Geometry
	 */
	public Geometry(Geometry other) {
		_emission = other._emission;
		_material = other._material;
	}

	/***************** Getter ****************************/

	/**
	 * get emission
	 * 
	 * @return the emission color of the geometry
	 */
	public Color getEmmission() {
		return _emission;
	}

	/**
	 * get material
	 * 
	 * @return the material of the geometry
	 */
	public Material getMaterial() {
		return _material;
	}

	/***************** Operations ************************/

	/**
	 * get the normal to the geometry on a given point
	 * 
	 * @param point point to get the normal from
	 * @return vector normal
	 */
	public abstract Vector getNormal(Point point);

}
