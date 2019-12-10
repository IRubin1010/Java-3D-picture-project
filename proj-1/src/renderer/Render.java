
package renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import elements.LightSource;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import static geometries.Intersectable.GeoPoint;

/**
 * this class represents the rendering functions of the image
 */
public class Render {
	int numOfThreads = Runtime.getRuntime().availableProcessors();
	Scene _scene;
	ImageWriter _imageWriter;
	Random rand = new Random();
	private static final double EPS = 1.0;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final int MAX_CALC_COLOR_LEVEL = 15;
	private static final int NUM_OF_DUST_RAYS = 14;
	private static final int REDUCE_OF_MAIN_RAYS = 15;
	private static final int REDUCE_OF_SECONDARY_RAYS = 15;
	private static final int DISTANCE_CIRCLE = 250;

//-------------------------Constructor-----------------------------------------//
	/**
	 * Constructor receiving scene and imagewriter objects
	 * 
	 * @param _scene      The scene object
	 * @param imageWriter The ImageWriter object
	 */
	public Render(Scene _scene, ImageWriter imageWriter) {
		this._scene = _scene;
		this._imageWriter = imageWriter;
	}

//--------------------------------------------------------------------//
	/**
	 * Shell function. Build tree hierarchy if need to and send to correct function
	 * 
	 * @throws InterruptedException
	 */
	public void renderImage() throws InterruptedException {
		if (_scene._toBuildTree)
			_scene.getGeometries().settBvhTree(); // Build tree hierarchy in scene
		if (_scene._withThreds)
			renderImageWithThreads();
		else
			renderImageWithoutThreads();

	}

	/**
	 * this function renders the image with use all processor of CPU
	 */
	public void renderImageWithThreads() throws InterruptedException {

		final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numOfThreads);

		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		double width = this._imageWriter.getWidth();
		double height = this._imageWriter.getHeight();
		double screenDistance = this._scene.getDistance();
		java.awt.Color Background = this._scene.getBackground().getColor();

		for (int i = 0; i < Nx; i++) {
			for (int j = 0; j < Ny; j++) {
				final int ii = i;
				final int jj = j;
				executor.execute(() -> {
					Ray ray = _scene.getCamera().constructRayThroughPixel(Nx, Ny, ii, jj, screenDistance, width,
							height);
					GeoPoint closestPoint = findClosestIntersection(ray);
					if (closestPoint == null)
						_imageWriter.writePixel(ii, jj, Background);
					else
						_imageWriter.writePixel(ii, jj, calcColor(closestPoint, ray).getColor());
				});
			}
		}
		executor.shutdown();
		executor.awaitTermination(550, TimeUnit.MINUTES);
		this._imageWriter.writeToimage();
	}

	/**
	 * this function renders the image in regular way
	 */
	public void renderImageWithoutThreads() {
		Ray ray;
		GeoPoint closestPoint;
		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		double width = this._imageWriter.getWidth();
		double height = this._imageWriter.getHeight();
		double screenDistance = this._scene.getDistance();
		// Point p0 = _scene.getCamera().getP0();
		java.awt.Color Background = this._scene.getBackground().getColor();
		// Geometries geometries = this._scene.getGeometries();

		for (int i = 0; i < Nx; i++) {
			for (int j = 0; j < Ny; j++) {
				ray = _scene.getCamera().constructRayThroughPixel(Nx, Ny, i, j, screenDistance, width, height);
				closestPoint = findClosestIntersection(ray);
				if (closestPoint == null)
					_imageWriter.writePixel(i, j, Background);
				else
					_imageWriter.writePixel(i, j, calcColor(closestPoint, ray).getColor());
			}
		}
		this._imageWriter.writeToimage();
	}

	/**
	 * this func print grid to picture the row and colom will by the i modolo
	 * inteval
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		int Nx = _imageWriter.getNx();
		for (int i = 0; i < Nx; i++) {
			if (i % interval == 0) {
				for (int j = 0; j < _imageWriter.getNy(); j++) {
					_imageWriter.writePixel(i, j, 255, 255, 255);
					_imageWriter.writePixel(j, i, 255, 255, 255);
				}
			}
		}
	}

	/**
	 * Shell function for calcColor send to calcColor with counter of level end k=1
	 * for stop condition
	 * 
	 * @param geopoint to calculate the color
	 * @param Ray      that hit in point
	 * @return the color of this point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
	}

	/**
	 * help function This function calculates the color of a point
	 * 
	 * @param Ray   that hit in point
	 * @param p     3D model point to calculate the color at
	 * @param level of specific recurse for stop condition
	 * @param k     for stop condition
	 * @return resulting color
	 */
	public Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K) // stop condition
			return Color.BLACK;

		Color color = geopoint.geometry.getEmmission();
		Vector v = inRay.getVector();
		Vector v1 = geopoint.point.substract(_scene.getCamera().getP0()).normalized_Vector();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		int nShininess = geopoint.geometry.getMaterial().getNShininess();
		double kd = geopoint.geometry.getMaterial().getKd();
		double ks = geopoint.geometry.getMaterial().getKs();
		for (LightSource lightSource : _scene.getLights()) { // <-chek if need to change for save timeRun = dan said no
			Vector l = lightSource.getL(geopoint.point);
			// if the direction of camera and light is same so add
			if (n.dotProduct(l) * n.dotProduct(v1) > 0) {
				double ktr = transparency(l, n, geopoint);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}

		}
		int kDust = geopoint.geometry.getMaterial()._kDust;
		// Recursive call for a reflected ray
		double kr = geopoint.geometry.getMaterial().getKr();
		if (kr > 0) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, v);
			color = calcDust(level, k, color, n, reflectedRay, kr, kDust);
		}
		// Recursive call for a refracted ray
		double kt = geopoint.geometry.getMaterial().getKt();
		if (kt > 0) {
			Ray refractedRay = constructRefractedRay(n, geopoint.point, v);
			color = calcDust(level, k, color, n, refractedRay, kt, kDust);
		}

		return color;
	}

	/**
	 * help function to calculate the color after the effect of opacity
	 * 
	 * @param level    the parameter for calcColor
	 * @param k        the parameter for calcColor
	 * @param color    the parameter that need to add color
	 * @param n        normal
	 * @param refRay   ray reflected or ray retraction
	 * @param refParam double reflected or retraction
	 * @param kDust    The amount of opacity of the surface
	 * @return color after the effect of opacity
	 */
	private Color calcDust(int level, double k, Color color, Vector n, Ray refRay, double refParam, int kDust) {
		Color addColor = Color.BLACK;
		double kk = k * refParam;
		List<Ray> rays;
		int size = 1;
		if (kDust > 0) {
			rays = constructDustRays(n, kDust, refRay);
			for (Ray ray : rays) {
				GeoPoint dustGeoPoint = findClosestIntersection(ray);
				if (dustGeoPoint != null) {
					addColor = addColor.add(calcColor(dustGeoPoint, ray, level - 1, kk).scale(refParam));
				}
			}
			size += rays.size();
			color = color.add(addColor.reduce(size));
		}
		GeoPoint refPoint = findClosestIntersection(refRay);
		if (refPoint != null) {
			Color mainColor = calcColor(refPoint, refRay, level - 1, kk).scale(refParam);
			if (size > 1)
				mainColor = mainColor.reduce(size);
			color = color.add(mainColor);
		}
		return color;
	}

	/**
	 * The function calculates the random distribution of rays to the area where the
	 * beam should have been sent and returns the collection of points that have hit
	 * them
	 * 
	 * @param n              vector normal
	 * @param kDust          The blurring of the surface
	 * @param reflectedRay   the original reflected ray
	 * @param reflectedPoint
	 * @return GeoPoints that exist in radius of dust
	 */
	private List<Ray> constructDustRays(Vector n, int kDust, Ray reflectedRay) {
		List<Ray> retList = new ArrayList<>();
		Point p0 = reflectedRay.getPoint();
		Vector v0 = reflectedRay.getVector();
		Point centerOfCircle = p0.add(v0.scale(DISTANCE_CIRCLE));
		Ray dustRay;
		Point pointOnCircle;

		Vector planeVector1;// plumb vector to ray (Linear algebra A)
		try {
			planeVector1 = new Vector(v0.getPoint().getyCoordinate().get(), -v0.getPoint().getxCoordinate().get(), 0)
					.normalized_Vector();
		} catch (Exception e) {
			planeVector1 = new Vector(v0.getPoint().getzCoordinate().get(), 0, 0).normalized_Vector();
		}
		Vector planeVector2 = v0.crossProduct(planeVector1).normalized_Vector();

		for (int i = 1; i <= NUM_OF_DUST_RAYS; i++) {
			double r1 = ThreadLocalRandom.current().nextDouble(-1, 1);
			double r2 = Math.sqrt(1 - r1 * r1);

			// if we want cone shape rays
			double number = ThreadLocalRandom.current().nextDouble(-kDust, kDust);
			pointOnCircle = centerOfCircle;
			if (r1 != 0)
				pointOnCircle = pointOnCircle.add(planeVector1.scale(r1 * number));
			if (r2 != 0)
				pointOnCircle = pointOnCircle.add(planeVector2.scale(r2 * number));

			Vector v = pointOnCircle.substract(p0);
			dustRay = new Ray(p0, v);
			int nv0 = v0.dotProduct(n) >= 0 ? 1 : -1;
			int nv = v.dotProduct(n) >= 0 ? 1 : -1;

			if (nv0 == nv)
				retList.add(dustRay);
		}
		// double random = Math.random() * 49 + 1;
		return retList;
	}

	/**
	 * func for transparency constructRefractedRay
	 * 
	 * @param point
	 * @param v     vector from the camera
	 * @return ray With a little sliding addition in the v direction
	 */
	private Ray constructRefractedRay(Vector n, Point point, Vector v) {
		Point epsPoint = addEpsToPoints(n, point, v);
		// Point epsPoint = point.add(v);
		return new Ray(epsPoint, v);
	}

	/**
	 * construct the reflected ray
	 * 
	 * @param n     normal to the point
	 * @param v     ray vector
	 * @param point the point to calculate the reflection
	 * @return the reflected ray With a little sliding addition in the normal
	 *         direction
	 */
	private Ray constructReflectedRay(Vector n, Point point, Vector v) {
		Vector RayVector = v.subtract(n.scale(v.dotProduct(n) * 2)).normalized_Vector();
		// Point epsPoint = point.add(RayVector); // check if need to add eps Precisely
		// from normal??
		Point epsPoint = addEpsToPoints(n, point, RayVector);
		return new Ray(epsPoint, RayVector);
	}

	/**
	 * add epsilon to point With a little sliding addition in the good normal
	 * direction
	 * 
	 * @param normal normal from the point
	 * @param point  point for add epsilon
	 * @param v      vector to light
	 * @return the point added by epsilon
	 */
	private Point addEpsToPoints(Vector n, Point point, Vector v) {
		Vector epsVector = n.scale(n.dotProduct(v) > 0 ? EPS : -EPS);
		return point.add(epsVector);
	}

	/**
	 * calculate the closets intersection of a ray
	 * 
	 * @param ray ray to get the closest intersection
	 * @return the closets intersection of the ray, if its empty return null
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersectionList = _scene.getGeometries().findIntersections(ray);
		if (intersectionList.isEmpty())
			return null;
		else
			return getClosestPoint(intersectionList, ray.getPoint());

	}

	/**
	 * calculate the diffusive color
	 * 
	 * @param Kd             diffusive factor
	 * @param l              the light direction
	 * @param n              normal
	 * @param lightIntensity the light intensity
	 * @return the diffusive color
	 */
	private Color calcDiffusive(double Kd, Vector l, Vector n, Color lightIntensity) {
		double angleCos = Math.abs(l.dotProduct(n)); // <- Change without abs func = dan said no
		return lightIntensity.scale(Kd * angleCos);
	}

	/**
	 * calculate the specular color
	 * 
	 * @param ks             specular factor
	 * @param l              the light direction
	 * @param n              normal
	 * @param v              vector from the camera to the geometry
	 * @param nShinines      nShinines factor
	 * @param lightIntensity the light intensity
	 * @return the specular color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShinines, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalized_Vector();
		double vr = r.dotProduct(v);
		// if direction of r and vCamera aren't the same
		if (vr < 0) {
			double angleCos = Math.pow(-vr, nShinines);
			return lightIntensity.scale(ks * angleCos);

		} else // the camera not see the Specular
			return Color.BLACK;

	}

	/**
	 * this func receiving list of points and returns the closest point to p0
	 * 
	 * @param       List<GeoPoint> points of intersection
	 * @param point to return the near point
	 * @return the closest point to the p0 from list of intersections
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> points, Point p0) { // to check if points is empty //
																		// "exception"??????????????
		GeoPoint result = points.get(0);
		double distance = p0.squareDistance(result.point);
		double tempDistance;
		for (GeoPoint p : points) {
			tempDistance = p0.squareDistance(p.point);
			if (tempDistance < distance) {
				distance = tempDistance;
				result = p;
			}
		}
		return result;
	}

	/**
	 * this func receiving Vector l, GeoPoint geopoint
	 * 
	 * @param l        vector from light to geopoint
	 * @param n        vector normal
	 * @param geopoint
	 * @return percent of light that suppose to get to the point
	 */
	private double transparency(Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Point point = addEpsToPoints(n, geopoint.point, lightDirection);
//		Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
//		Point point = geopoint.point.add(epsVector);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
		double ktr = 1;
		for (GeoPoint gp : intersections)
			ktr *= gp.geometry.getMaterial().getKt();
		return ktr;
	}

	// ###################################################################################################
	/**
	 * old func
	 * 
	 * @param geopoint
	 * @return
	 */
	public Color calcColor(GeoPoint geopoint) {
		Color color = _scene.getAmbientLight().getIntensity();
		color = color.add(geopoint.geometry.getEmmission());
		Vector v = geopoint.point.substract(_scene.getCamera().getP0()).normalized_Vector();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		int nShininess = geopoint.geometry.getMaterial().getNShininess();
		double kd = geopoint.geometry.getMaterial().getKd();
		double ks = geopoint.geometry.getMaterial().getKs();
		for (LightSource lightSource : _scene.getLights()) { // <-chek if need to change for save timeRun = dan said no
			Vector l = lightSource.getL(geopoint.point);
			// if the direction of camera and light is same so add
			if (n.dotProduct(l) * n.dotProduct(v) > 0) {
				if (unshaded(l, n, geopoint)) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		return color;
	}

	/**
	 * this func receiving Vector l, GeoPoint geopoint
	 * 
	 * @param l        vector from light to geopoint
	 * @param geopoint
	 * @return true if the ray from light has no shapes on its way to the point
	 */
	private boolean unshaded(Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Point point = addEpsToPoints(n, geopoint.point, lightDirection);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
		return intersections.isEmpty();
	}
}
