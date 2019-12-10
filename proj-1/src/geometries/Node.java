package geometries;

import java.util.List;

import primitives.Ray;

/**
 * this class represent node boxes (leaf or edge)
 */
public class Node extends Intersectable {
	Intersectable keyIntersectable;
	Intersectable right;
	Intersectable left;
	boolean isLeaf = false;

	/**
	 * Constructor for leaf
	 * 
	 * receive geometry and make it at key make the min max by the field of geometry
	 * 
	 * @param flag is leaf
	 * @param geo
	 */
	public Node(boolean flag, Intersectable geo) {
		keyIntersectable = geo;
		isLeaf = flag;

		maxX = keyIntersectable.maxX;
		minX = keyIntersectable.minX;
		maxY = keyIntersectable.maxY;
		minY = keyIntersectable.minY;
		maxZ = keyIntersectable.maxZ;
		minZ = keyIntersectable.minZ;

	}

	/**
	 * Constructor for edge (not leaf)
	 * 
	 * Receive left son and right son (can be null) ta first do setvalues on left
	 * and if the right not null do setMinMax
	 * 
	 * @param left
	 * @param right
	 */
	public Node(Intersectable left, Intersectable right) {
		this.right = right;
		this.left = left;
		setvalues();
		if (right != null)
			setMinMax();
	}

	/**
	 * set the values of min max by comparison right and left son at first we set
	 * the value from left and now we comparison
	 */
	protected void setMinMax() {
		if (right.minX < minX)
			minX = right.minX;
		if (right.maxX > maxX)
			maxX = right.maxX;
		if (right.minY < minY)
			minY = right.minY;
		if (right.maxY > maxY)
			maxY = right.maxY;
		if (right.minZ < minZ)
			minZ = right.minZ;
		if (right.maxZ > maxZ)
			maxZ = right.maxZ;
	}

	/**
	 * set our value from the value of left son
	 */
	// values of left son
	private void setvalues() {
		maxX = left.maxX;
		minX = left.minX;
		maxY = left.maxY;
		minY = left.minY;
		maxZ = left.maxZ;
		minZ = left.minZ;
	}

	/**
	 * get the geometry
	 * 
	 * @return
	 */
	public Intersectable getKeyIntersectable() {
		return keyIntersectable;
	}

	/**
	 * set the geometry in key field
	 * 
	 * @param keyIntersectable
	 */
	public void setKeyIntersectable(Intersectable keyIntersectable) {
		this.keyIntersectable = keyIntersectable;
	}

	@Override
	protected List<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
