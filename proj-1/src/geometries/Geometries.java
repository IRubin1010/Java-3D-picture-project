package geometries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.util.Collections.sort;
import static java.lang.Double.compare;

import primitives.Ray;
import scene.Scene;

/**
 * class Geometries extends Intersectable
 */
public class Geometries extends Intersectable {

	private Intersectable _geometry;
	public List<Intersectable> geometries = new ArrayList<Intersectable>();
	public Node tBvhTree;

	// --------------------------------------------------Constructors---------------------------------------------------------------------//
	/**
	 * copy ctor receive geometries(Intersectables) and input them in geometries and
	 * make setMinMax for min max values
	 * 
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		for (Intersectable geom : geometries) {
			this.geometries.add(geom);
			_geometry = geom;
			setMinMax();
		}

	}

	// ------------------------------------------------Getters/Setters-----------------------------------------------------------------//
	/**
	 * get Geometries
	 * 
	 * @return List<Geometry>
	 */
	public List<Intersectable> getGeometries() {
		return geometries;
	}

	/**
	 * @return the tBvhTree
	 */
	public Intersectable gettBvhTree() {
		return tBvhTree;
	}

	/**
	 * @param tBvhTree the tBvhTree to set
	 */
	public void settBvhTree() {
		this.tBvhTree = setTreeHirarcia(sortGeometries());
	}

	// ---------------------------------------------------Operations-----------------------------------------------------------------//
	/**
	 * this function put the geometries in boxes (as a laef) and sort them by
	 * location parameter
	 * 
	 * @return List<Node> of boxes (as a laef) after sort
	 */
	private List<Node> sortGeometries() {
		List<Node> list = new ArrayList<>();
		for (Intersectable geo : geometries) {
			Node tempNode = new Node(true, geo);
			tempNode.location = (tempNode.maxX) * (tempNode.maxX) + (tempNode.maxY) * (tempNode.maxY)
					+ (tempNode.maxZ) * (tempNode.maxZ);
			list.add(tempNode);
		}
		sort(list, (u1, u2) -> compare(u1.location, u2.location));
		return list;
	}

	/**
	 * add shapes to list
	 */
	public void addShape(Intersectable... geometries) {
		for (Intersectable geom : geometries) {
			this.geometries.add(geom);
			_geometry = geom;
			setMinMax();
		}
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		if (tBvhTree == null) {
			if (Scene._boxing)
				return findIntersectionsWithBoxingWithoutTree(ray);
			else
				return findIntersectionsWithoutBoxing(ray);
		} else
			return findIntersectionsWithTree(ray, tBvhTree);
	}

	/**
	 * Recursive function
	 * 
	 * if is not leaf and have son so recursive call on son if is leaf do getKey ->
	 * findIntersections
	 * 
	 * @param ray  that we look for intersection with
	 * @param node box that contain ege or leaf
	 * @return all the intersections between ray and geometries
	 */
	public List<GeoPoint> findIntersectionsWithTree(Ray ray, Intersectable node) {
		List<GeoPoint> intersectionsList = new ArrayList<GeoPoint>();

		if (!((Node) node).isLeaf) {
			if (((Node) node).right != null && ((Node) node).right.isIntersectedBox(ray))
				intersectionsList.addAll(findIntersectionsWithTree(ray, ((Node) node).right));
			if (((Node) node).left != null && ((Node) node).left.isIntersectedBox(ray))
				intersectionsList.addAll(findIntersectionsWithTree(ray, ((Node) node).left));
		} else // isLeaf
			intersectionsList.addAll(((Node) node).getKeyIntersectable().findBoxedIntersections(ray));

		return intersectionsList;
	}

	/**
	 * @return all the intersections between ray and geometries
	 */
	public List<GeoPoint> findIntersectionsWithoutBoxing(Ray ray) {
		List<GeoPoint> intersectionsList = new ArrayList<GeoPoint>();
		for (Intersectable item : geometries) {
			intersectionsList.addAll(item.findBoxedIntersections(ray));
		}
		return intersectionsList;
	}

	/**
	 * @return all the intersections between ray and geometries
	 */
	public List<GeoPoint> findIntersectionsWithBoxingWithoutTree(Ray ray) {
		List<GeoPoint> intersectionsList = new ArrayList<GeoPoint>();
		if (isIntersectedBox(ray)) {
			for (Intersectable item : geometries) {
				// לאסוף וקפסאותץ שהקרן חותכת
				intersectionsList.addAll(item.findBoxedIntersections(ray));
			}
		}
		return intersectionsList;
	}

	@Override
	protected void setMinMax() {
		if (_geometry.minX < minX)
			minX = _geometry.minX;
		if (_geometry.maxX > maxX)
			maxX = _geometry.maxX;
		if (_geometry.minY < minY)
			minY = _geometry.minY;
		if (_geometry.maxY > maxY)
			maxY = _geometry.maxY;
		if (_geometry.minZ < minZ)
			minZ = _geometry.minZ;
		if (_geometry.maxZ > maxZ)
			maxZ = _geometry.maxZ;
	}

	/**
	 * Recursive function
	 * 
	 * this function receive List<Node> and make tree by hierarchy the func puts
	 * them in pairs and sends them collection again in recursive
	 * 
	 * @param sortedList the geometries in sort
	 * @return sortedList.get(0) ==> the head
	 */
	protected Node setTreeHirarcia(List<Node> sortedList) {
		// stop condition
		if (sortedList.size() == 1)
			return sortedList.get(0);

		// odd size so add 1 (null)
		List<Node> list = new ArrayList<>();

		Iterator<Node> it = sortedList.iterator();
		while (it.hasNext())
			list.add(new Node(it.next(), it.hasNext() ? it.next() : null));

		return setTreeHirarcia(list);
	}
}
