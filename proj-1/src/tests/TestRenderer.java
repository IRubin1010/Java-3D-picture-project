package tests;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * tests for render
 */
public class TestRenderer {
	/**
	 * test for render
	 * @throws InterruptedException 
	 */
	@Test
	public void basicRendering1() throws InterruptedException {

		Sphere sphere = new Sphere(50, new Point(0.0, 0.0, -150));

		Triangle triangle = new Triangle(new Point(100, 0, -149), new Point(0, 100, -149), new Point(100, 100, -149));

		Triangle triangle2 = new Triangle(new Point(100, 0, -149), new Point(0, -100, -149),
				new Point(100, -100, -149));

		Triangle triangle3 = new Triangle(new Point(-100, 0, -149), new Point(0, 100, -149),
				new Point(-100, 100, -149));

		Triangle triangle4 = new Triangle(new Point(-100, 0, -149), new Point(0, -100, -149),
				new Point(-100, -100, -149));

		Scene scene = new Scene("image", new primitives.Color(0, 0, 0),
				new AmbientLight(new primitives.Color(50, 50, 100), 3),
				new Geometries(sphere, triangle, triangle2, triangle3, triangle4),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, -1), new Point(0, 0, 1)), 149);

		ImageWriter imageWriter = new ImageWriter("Render test2", 500, 500, 1000, 1000);

		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();

	}

	/**
	 * test 2 for render
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void basicRendering() throws InterruptedException {
		Scene scene = new Scene("Test scene");
		scene.setCameraAndDistance(new Camera(new Vector(0, -1, 0), new Vector(0, 0, 1), new Point(0, 0, 0)), 100);
		scene.setBackground(new primitives.Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		scene.setAmbientLight(new AmbientLight(new primitives.Color(0, 200, 100), 3));
		geometries.addShape(new Sphere(50, new Point(0, 0, 150)));

		geometries.addShape(new Triangle(new Point(100, 0, 149), new Point(0, 100, 149), new Point(100, 100, 149)));

		geometries.addShape(new Triangle(new Point(100, 0, 149), new Point(0, -100, 149), new Point(100, -100, 149)));

		geometries.addShape(new Triangle(new Point(-100, 0, 149), new Point(0, 100, 149), new Point(-100, 100, 149)));

		geometries.addShape(new Triangle(new Point(-100, 0, 149), new Point(0, -100, 149), new Point(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();
		// render.writeToImage();
	}

	/**
	 * 
	 * test Render.getClosestPoint test if the point from points is the closed point
	 */
	@Test
	public void getClosestPointTest() {
		List<Intersectable.GeoPoint> intersectionPoints = new ArrayList<Intersectable.GeoPoint>();

		Intersectable.GeoPoint geoPoint1 = new Intersectable.GeoPoint();
		geoPoint1.point = (new Point(10, 10, 10));
		Intersectable.GeoPoint geoPoint2 = new Intersectable.GeoPoint();
		geoPoint2.point = (new Point(20, 20, 20));
		Intersectable.GeoPoint geoPoint3 = new Intersectable.GeoPoint();
		geoPoint3.point = (new Point(30, 30, 30));
		Intersectable.GeoPoint geoPoint4 = new Intersectable.GeoPoint();
		geoPoint4.point = (new Point(2, 2, 2));

		intersectionPoints.add(geoPoint1);
		intersectionPoints.add(geoPoint2);
		intersectionPoints.add(geoPoint3);
		intersectionPoints.add(geoPoint4);

		Scene scene = new Scene("Test scene");
		scene.setCameraAndDistance(new Camera(new Vector(0, 0, 1), new Vector(0, -1, 0), new Point(0, 0, 0)), 100);
		scene.setAmbientLight(new AmbientLight(new primitives.Color(0, 200, 100), 3));
		scene.setBackground(new primitives.Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		Intersectable.GeoPoint closedPoint = render.getClosestPoint(intersectionPoints, scene.getCamera().getP0());
		assertEquals(new Point(2, 2, 2), closedPoint.point);

	}
}
