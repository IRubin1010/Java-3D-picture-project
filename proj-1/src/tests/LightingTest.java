
package tests;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * tests for all kinds of lights
 *
 */
public class LightingTest {

	// ------------------------------------tests 04/06/19 ----------//

	@Test
	public void spotLightTestShadou1() throws InterruptedException {

		Sphere sphere = new Sphere(500, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 10));

		Triangle triangle = new Triangle(new Point(-125, -225, -260), new Point(-225, -125, -260),
				new Point(-225, -225, -270), new Color(0, 0, 100), new Material(0.5, 0.5, 4, 2, 0));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle), new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point(-200, -200, -150), 0.000005, 0.00001,
				new Color(1255, 1500, 100)));
		ImageWriter imageWriter = new ImageWriter("צל על ספרה 1", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTestShadou2() throws InterruptedException {

		Sphere sphere = new Sphere(500, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Triangle triangle = new Triangle(new Point(-70, -170, -260), new Point(-170, -70, -260),
				new Point(-170, -170, -270), new Color(0, 0, 100), new Material(0.5, 0.5, 4, 2, 0));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle), new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point(-200, -200, -150), 0.000005, 0.00001,
				new Color(1255, 1500, 100)));
		ImageWriter imageWriter = new ImageWriter("צל על ספרה 2", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTestShadou3() throws InterruptedException {

		Sphere sphere = new Sphere(500, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Triangle triangle = new Triangle(new Point(-100, -200, -260), new Point(-200, -100, -260),
				new Point(-200, -200, -270), new Color(0, 0, 100), new Material(0.5, 0.5, 4, 2, 0));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle), new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point(-200, -200, -150), 0.000005, 0.00001,
				new Color(1255, 1500, 100)));

		ImageWriter imageWriter = new ImageWriter("צל על ספרה 3", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTestShadou4() throws InterruptedException {

		Sphere sphere = new Sphere(500, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Triangle triangle = new Triangle(new Point(-100, -200, -260), new Point(-200, -100, -260),
				new Point(-200, -200, -270), new Color(0, 0, 100), new Material(0.5, 0.5, 4, 2, 0));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle), new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point(-200, -200, -200), 0.000005, 0.00001,
				new Color(1255, 1500, 100)));

		ImageWriter imageWriter = new ImageWriter("צל על ספרה 3 אור קרוב ", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTestShadou5() throws InterruptedException {

		Sphere sphere = new Sphere(500, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Triangle triangle = new Triangle(new Point(-100, -200, -260), new Point(-200, -100, -260),
				new Point(-200, -200, -270), new Color(0, 0, 100), new Material(0.5, 0.5, 4, 2, 0));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle), new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point(-200, -200, -100), 0.000005, 0.00001,
				new Color(1255, 1500, 100)));

		ImageWriter imageWriter = new ImageWriter("צל על ספרה 3 אור רחוק ", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTest5() throws InterruptedException {

		Triangle triangle2 = new Triangle(new Point(100, 100, 149), new Point(100, -100, 70),
				new Point(-100, -100, 149), new Color(0, 0, 0), new Material(0.3, 0.3, 50, 0.8, 0));

		Triangle triangle3 = new Triangle(new Point(100, 100, 149), new Point(-100, 100, 150),
				new Point(-100, -100, 149), new Color(0, 0, 0), new Material(0.3, 0.3, 50, 0.8, 0));

		Sphere sphere = new Sphere(20, new Point(-50, 40, 70), new Color(0, 0, 200),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Sphere sphere2 = new Sphere(5, new Point(-50, 40, 70), new Color(200, 0, 0),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle2, triangle3, sphere, sphere2),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, 0)), 200);

		scene.addLight(new SpotLight(new Vector(0, 1, 0), new Point(-50, 0, 70), 0.000001, 0.0000005,
				new Color(500, 0, 0), 15));

		scene.addLight(new PointLight(new Point(0, 0, 0), 0.000001, 0.000005, new Color(500, 700, 500)));

		ImageWriter imageWriter = new ImageWriter(" שיקוף על משולשים", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTest7() throws InterruptedException {

		Triangle triangle2 = new Triangle(new Point(100, 100, 149), new Point(100, -100, 70),
				new Point(-100, -100, 149), new Color(0, 0, 0), new Material(0.3, 0.3, 50, 0.8, 0.2, 2));

		Triangle triangle3 = new Triangle(new Point(100, 100, 149), new Point(-100, 100, 150),
				new Point(-100, -100, 149), new Color(0, 0, 0), new Material(0.3, 0.3, 50, 0.8, 0.2, 2));

		Sphere sphere = new Sphere(15, new Point(-50, 40, 70), new Color(0, 0, 200),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Sphere sphere2 = new Sphere(5, new Point(-50, 40, 70), new Color(200, 0, 0),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Sphere sphere3 = new Sphere(15, new Point(50, 40, 70), new Color(200, 0, 0),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Sphere sphere4 = new Sphere(5, new Point(50, 40, 70), new Color(0, 0, 200),
				new Material(0.1, 0.1, 120, 0, 0.3));

		QuadPrism quadPrism = new QuadPrism(10, 10, 10, new Point(-40, -20, 40), new Color(200, 0, 200),
				new Material(0.1, 0.1, 120, 0, 0.3));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle2, triangle3, sphere, sphere2, sphere3, sphere4, quadPrism),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000);

		scene.addLight(new SpotLight(new Vector(0, 1, 0), new Point(-50, 0, 70), 0.000001, 0.0000005,
				new Color(500, 0, 0), 15));

		scene.addLight(new PointLight(new Point(0, 0, 0), 0.000001, 0.000005, new Color(500, 700, 500)));

		ImageWriter imageWriter = new ImageWriter(" שיקוף על משולשים עם הרבה עצמים ", 200, 200, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void spotLightTest2() throws InterruptedException {

		QuadPrism quadPrism1 = new QuadPrism(1000, 1000, 1000, new Point(-500, -1000, 1000), new Color(0, 0, 100),
				new Material(0.5, 0.5, 400, 0.3, 0.45));

		Sphere sphere = new Sphere(400, new Point(0, -1000, 1000), new Color(0, 0, 100),
				new Material(0.2, 0.2, 120, 0.2, 0.7));

		Scene scene = new Scene("image", new Color(192, 192, 192), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(quadPrism1, sphere),
				new Camera(new Vector(0, 1.7, 1), new Vector(0, -1, 1.7), new Point(0, -1700, -1000)), 1800);

		scene.addLight(new SpotLight(new Vector(0, 1, 0.2), new Point(0, -1100, 900), 0.000005, 0.00001,
				new Color(500, 500, 500)));

		scene.addLight(new DirectionalLight(new Vector(1, 1, 0), new Color(300, 300, 300)));
		ImageWriter imageWriter = new ImageWriter("מנסרה ועיגול", 1000, 2800, 500, 1400);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	/**
	 * rectrangleTest
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void rectrangleTest() throws InterruptedException {

		Rectangle rectangle1 = new Rectangle(new Point(2000, 1500, 500), new Point(2000, -1500, 500),
				new Point(1000, 1500, 2000), new Color(169, 169, 169), new Material(0, 0, 100, 0.8, 0));

		Rectangle rectangle2 = new Rectangle(new Point(-2000, -1500, 500), new Point(-2000, 1500, 500),
				new Point(-1000, -1500, 2000), new Color(169, 169, 169), new Material(0, 0, 100, 0.8, 0));

		Rectangle rectangle3 = new Rectangle(new Point(1000, 1500, 2000), new Point(1000, -1500, 2000),
				new Point(-1000, 1500, 2000), new Color(135, 105, 250), new Material(0.3, 1, 50));

		Rectangle rectangleback1 = new Rectangle(new Point(1000, 1500, 2000), new Point(1000, -1500, 2000),
				new Point(-1000, 1500, 2000), new Color(135, 105, 250), new Material(0.3, 1, 50));

		Rectangle rectangleback2 = new Rectangle(new Point(1000, 1500, 2000), new Point(1000, -1500, 2000),
				new Point(-1000, 1500, 2000), new Color(135, 105, 250), new Material(0.3, 1, 50));

		Rectangle rectangle4 = new Rectangle(new Point(-2000, 1500, 500), new Point(2000, 1500, 500),
				new Point(-2000, 1000, 2000), new Color(192, 192, 192), new Material(1, 1, 100));

		Rectangle rectangle5 = new Rectangle(new Point(2000, -1500, 500), new Point(-2000, -1500, 500),
				new Point(2000, -1000, 2000), new Color(150, 150, 150), new Material(1, 1, 100));

		Sphere sphere1 = new Sphere(450, new Point(0, -500, 1600), new Color(1000, 0, 100), new Material(2, 2, 200));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(10, 10, 10), 3),
				new Geometries(rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, sphere1),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, -300, -1500)), 300);

		ImageWriter imageWriter = new ImageWriter("מרובעים", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		imageWriter.writeToimage();
	}

//-------------------------------------------------------------------------------------------------------//
	@Test
	public void emmissionTest() throws InterruptedException {

		Sphere sphere = new Sphere(50, new Point(0.0, 0.0, -149), new Color(100, 0, 100),
				new Material(0.5, 0.5, 50, 0, 0));

		Triangle triangle = new Triangle(new Point(100, 0, -149), new Point(0, 100, -149), new Point(100, 100, -149),
				new Color(100, 0, 100), new Material(1, 1, 50));

		Triangle triangle2 = new Triangle(new Point(100, 0, -149), new Point(0, -100, -149), new Point(100, -100, -149),
				new Color(100, 0, 100), new Material(1, 1, 50));

		Triangle triangle3 = new Triangle(new Point(-100, 0, -149), new Point(0, 100, -149), new Point(-100, 100, -149),
				new Color(100, 0, 100), new Material(1, 1, 50));

		Triangle triangle4 = new Triangle(new Point(-100, 0, -149), new Point(0, -100, -149),
				new Point(-100, -100, -149), new Color(100, 0, 100), new Material(1, 1, 50));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle, triangle2, triangle3, triangle4),
				new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), Point.ZERO), 150);

		scene.addLight(new SpotLight(new Vector(-1, -1, -10), new Point(20, 20, -50), 0.0001, 0.000005,
				new Color(300, 300, 300)));

		ImageWriter imageWriter = new ImageWriter("spot light from side on sphere and triangles", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		imageWriter.writeToimage();
	}

	// -------------------------------------------------------------------------------------------------------------------//
	@Test
	public void spotLightTest() throws InterruptedException {

		Sphere sphere1 = new Sphere(50, new Point(0, 0, 100), new Color(1000, 0, 100), new Material(0.5, 0.5, 50));
		Sphere sphere2 = new Sphere(30, new Point(100, 50, 150), new Color(0, 0, 100), new Material(0.5, 0.5, 20));
		Sphere sphere3 = new Sphere(30, new Point(-100, 50, 150), new Color(0, 0, 100), new Material(0.5, 0.5, 20));
		Sphere sphere4 = new Sphere(7, new Point(0, -2, 50), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Triangle triangle = new Triangle(new Point(0, -15, 40), new Point(-20, -10, 50), new Point(20, -10, 50),
				new Color(100, 0, 100), new Material(1, 1, 50));

		Scene scene = new Scene("image", new Color(0, 100, 10), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere1, sphere2, sphere3, sphere4, triangle),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, 0)), 100);

		scene.addLight(new SpotLight(new Vector(0, 1, 0), new Point(0, -200, 100), 0.000001, 0.00005,
				new Color(500, 500, 500)));

		scene.addLight(new SpotLight(new Vector(0, -1, 0), new Point(0, 200, 100), 0.000001, 0.00005,
				new Color(500, 500, 500)));

		scene.addLight(new SpotLight(new Vector(-1, -1, 0), new Point(0, 250, 250), 0.0000001, 0.000005,
				new Color(1000, 1000, 1000)));

		scene.addLight(new DirectionalLight(new Vector(0, 0, 1), new Color(100, 100, 100)));

		scene.addLight(new PointLight(new Point(250, 250, 0), 0.0000001, 0.0000005, new Color(250, 250, 0)));

		ImageWriter imageWriter = new ImageWriter("sphere- 3 kinds lights", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void pointLightTest() throws InterruptedException {

		Sphere sphere = new Sphere(800, new Point(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere),
				new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), new Point(0.0, 0.0, 1000)), 200);

		scene.addLight(new PointLight(new Point(-200, -200, -100), 0.00001, 0.000005, new Color(255, 100, 100)));
		ImageWriter imageWriter = new ImageWriter("sphere- point light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	@Test
	public void directionLightTest() throws InterruptedException {
		Sphere sphere = new Sphere(800, new Point(0.0, 0, 1000), new Color(0, 0, 100), new Material(0.5, 0.5, 20));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0.0, 0.0, -1000)), 200);

		scene.addLight(new DirectionalLight(new Vector(0, 1, 0), new Color(255, 100, 100)));

		ImageWriter imageWriter = new ImageWriter("sphere- direction  light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	@Test
	public void pointLightTest2() throws InterruptedException {

		Sphere sphere = new Sphere(600, new Point(-700, -600, -600), new Color(0, 0, 100),
				new Material(0.4, 0.4, 2, 0, 0));

		Triangle triangle = new Triangle(new Point(3500, 3500, -2000), new Point(-3500, -3500, -1000),
				new Point(3500, -3500, -2000), new Color(220, 20, 20), new Material(0.5, 0.5, 20, 0.9, 0.1));

		Triangle triangle2 = new Triangle(new Point(3500, 3500, -2000), new Point(-3500, 3500, -200),
				new Point(-3500, -3500, -1000), new Color(110, 110, 110), new Material(0.5, 0.5, 20, 0, 0.1));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(sphere, triangle2, triangle),
				new Camera(new Vector(1, 0, 0), new Vector(0, 0, -1), new Point(100, 0, 2000)), 200);
		scene.addLight(new PointLight(new Point(200, 200, -100), 0.000001, 0.0000005, new Color(255, 1100, 100)));
		ImageWriter imageWriter = new ImageWriter("sphere + 2 trianlgle- point light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}

	// --------------------------------------------------------------------------------------------------------------------------//

	@Test
	public void emmissionTest3() throws InterruptedException {

		Triangle triangle2 = new Triangle(new Point(100, 100, -149), new Point(-100, -100, -149),
				new Point(100, -100, -130), new Color(100, 0, 100), new Material(0.3, 0.5, 50));

		Triangle triangle3 = new Triangle(new Point(-100, -100, -149), new Point(100, 100, -149),
				new Point(-100, 100, -130), new Color(100, 0, 100), new Material(0.5, 0.5, 50));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle3, triangle2),
				new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), Point.ZERO), 150);

		scene.addLight(new SpotLight(new Vector(-1, -1, -10), new Point(20, 20, -50), 0.0001, 0.000005,
				new Color(300, 300, 300), 1000));

		ImageWriter imageWriter = new ImageWriter("2 triangle- spot light, purple", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		imageWriter.writeToimage();
	}

	@Test
	public void emmissionTest4() throws InterruptedException {

		Triangle triangle2 = new Triangle(new Point(100, 100, -149), new Point(-100, -100, -149),
				new Point(100, -100, -130), new Color(15, 15, 15), new Material(0.5, 0.5, 50));

		Triangle triangle3 = new Triangle(new Point(-100, -100, -149), new Point(100, 100, -149),
				new Point(-100, 100, -130), new Color(15, 15, 15), new Material(0.5, 0.5, 50));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle3, triangle2),
				new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), Point.ZERO), 150);
		scene.addLight(new PointLight(new Point(20, 20, -50), 0.0000001, 0.0000005, new Color(300, 300, 300)));

		ImageWriter imageWriter = new ImageWriter("2 triangle- point light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		imageWriter.writeToimage();
	}

	@Test
	public void emmissionTest5() throws InterruptedException {

		Triangle triangle2 = new Triangle(new Point(100, 100, -149), new Point(-100, -100, -149),
				new Point(100, -100, -130), new Color(100, 0, 100), new Material(0.3, 0.5, 50));

		Triangle triangle3 = new Triangle(new Point(-100, -100, -149), new Point(100, 100, -149),
				new Point(-100, 100, -130), new Color(100, 0, 100), new Material(0.5, 0.5, 50));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle3, triangle2),
				new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), Point.ZERO), 150);

		scene.addLight(new DirectionalLight(new Vector(-1, -1, -10), new Color(300, 300, 300)));

		ImageWriter imageWriter = new ImageWriter("2 triangle- directional light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		imageWriter.writeToimage();
	}

	@Test
	public void spotLightTest3() throws InterruptedException {

		Triangle triangle = new Triangle(new Point(3500, 3500, -2000), new Point(-3500, -3500, -1000),
				new Point(3500, -3500, -2000), new Color(0, 0, 0), new Material(0.5, 0.5, 20));

		Triangle triangle2 = new Triangle(new Point(3500, 3500, -2000), new Point(-3500, 3500, -1000),
				new Point(-3500, -3500, -1000), new Color(0, 0, 0), new Material(0.5, 0.5, 20));

		Scene scene = new Scene("image", Color.BLACK, new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(triangle2, triangle), new Camera(new Vector(-1, 0, 0), new Vector(0, 0, -1), Point.ZERO),
				200);

		scene.addLight(new SpotLight(new Vector(-2, -2, -3), new Point(200, 200, -100), 0.000001, 0.0000005,
				new Color(255, 100, 100)));

		ImageWriter imageWriter = new ImageWriter("2 triangle- spot light", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
	}
	// -------------------------טסטים חדשים עם BVH -----------------------------//

	@Test
	public void spotLightTes() throws InterruptedException {
		Rectangle rectangle = new Rectangle(new Point(-400, 500, 5000), new Point(400, 500, 5000),
				new Point(-400, -300, 200), new Color(60, 70, 70), new Material(0.2, 0.4, 200, 1, 0, 15));

		Sphere sphere = new Sphere(150, new Point(0, 200, 2000), new Color(0, 0, 255),
				new Material(0.2, 0.2, 120, 0, 0, 0));
		Sphere sphere2 = new Sphere(50, new Point(140, 10, 800), new Color(0, 0, 255),
				new Material(0.2, 0.2, 120, 0, 0, 0));

		Scene scene = new Scene("image", new Color(192, 192, 192), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(rectangle, sphere, sphere2),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000);

		scene.addLight(new DirectionalLight(new Vector(0, -0.3, 1), new Color(300, 300, 300)));
		scene.addLight(new SpotLight(new Vector(0, 1, 0), new Point(0, -50, 2000), 0.0001, 0.000005,
				new Color(1000, 1000, 1000)));

		ImageWriter imageWriter = new ImageWriter("השתקפות עם לכלוך", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	@Test
	public void spotLig() throws InterruptedException {
		Rectangle rectangle = new Rectangle(new Point(-400, 150, 2300), new Point(400, 150, 2300),
				new Point(-400, 200, 0), new Color(0, 0, 0), new Material(0.4, 0.4, 200, 0, 0.9, 50));

		Sphere sphere = new Sphere(150, new Point(0, -50, 1700), new Color(255, 140, 0),
				new Material(0.2, 0.2, 120, 0.2, 0, 0));

		Scene scene = new Scene("image", new Color(192, 192, 192), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(rectangle, sphere),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000);

		scene.addLight(new SpotLight(new Vector(0, -1, 0), new Point(0, 400, 1600), 0.0001, 0.000005,
				new Color(1000, 1000, 1000)));

		ImageWriter imageWriter = new ImageWriter("4 התותח", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	@Test
	public void spotL() throws InterruptedException {
		Rectangle rectangle = new Rectangle(new Point(-400, 400, 1000), new Point(400, 400, 1000),
				new Point(-400, -400, 1000), new Color(0, 0, 0), new Material(0.1, 0.1, 200, 0, 0.9, 50));

		Sphere sphere1 = new Sphere(100, new Point(0, 0, 1600), new Color(255, 0, 0),
				new Material(0.2, 0.2, 120, 0, 0, 0));

		Sphere sphere2 = new Sphere(100, new Point(-300, 0, 1200), new Color(0, 255, 0),
				new Material(0.2, 0.2, 120, 0, 0, 0));

		Sphere sphere3 = new Sphere(100, new Point(300, 0, 2000), new Color(0, 0, 255),
				new Material(0.2, 0.2, 120, 0, 0, 0));

		Scene scene = new Scene("image", new Color(192, 192, 192), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(rectangle, sphere1, sphere2, sphere3),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000);

		scene.addLight(new PointLight(new Point(0, 0, -1500), 0.00005, 0.000001, new Color(1500, 1500, 1500)));

		ImageWriter imageWriter = new ImageWriter(" כדורים זכוכית  עם עמימות 50 לכלוך", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	@Test
	public void s() throws InterruptedException {
		Rectangle rectangle = new Rectangle(new Point(-400, 400, 2000), new Point(400, 400, 2000),
				new Point(-400, -400, 2000), new Color(0, 0, 255), new Material(0.1, 0.1, 200, 0, 0, 0));
		Sphere sphere = new Sphere(1000, new Point(0, 0, -1000), new Color(255, 0, 0),
				new Material(0.2, 0.2, 120, 0, 0.8, 10));
		Scene scene = new Scene("image", new Color(0, 0, 0), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(rectangle, sphere),
				new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000);
		ImageWriter imageWriter = new ImageWriter("6 התותח", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}

	@Test
	public void sp() throws InterruptedException {
		Scene scene = new Scene("image", new Color(0, 0, 0), new AmbientLight(new primitives.Color(0, 0, 0), 3),
				new Geometries(), new Camera(new Vector(0, 1, 0), new Vector(0, 0, 1), new Point(0, 0, -1000)), 1000,
				null, true, false, true);
		for (int j = 1; j < 1000; j += 10) {
			for (int i = 25; i < 1000; i += 10) {
				scene.addShape(new Sphere(5,
						new Point(-550 + ThreadLocalRandom.current().nextDouble(0, 1000),
								500 - ThreadLocalRandom.current().nextDouble(0, 1000),
								1100 + ThreadLocalRandom.current().nextDouble(0, 1000)),
						new Color(255, 0, 0), new Material(0.2, 0.2, 120, 0, 0)));
			}
		}

		scene.addLight(new PointLight(new Point(0, 0, 1050), 0.00005, 0.000001, new Color(1500, 1500, 1500)));
		ImageWriter imageWriter = new ImageWriter(" new", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();

	}
	

}
