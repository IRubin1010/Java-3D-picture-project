package scene;

import java.util.ArrayList;
import java.util.List;
import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import elements.LightSource;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * this class represent scene
 */
public class Scene {
	String _scene;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	List<LightSource> _lights;
	double _distance;
	public static boolean _boxing = false;
	public boolean _withThreds = false;
	public static boolean _toBuildTree = false;

	// -------------------------------------Constructors------------------------------------------------------------//
	/**
	 * Constructor receiving scene name background color ambientLight geometries
	 * camera and distance
	 * 
	 * @param scene
	 * @param background
	 * @param ambientLight
	 * @param geometries
	 * @param camera
	 * @param distance
	 * @param              List<LightSource> lights
	 */
	public Scene(String scene, Color background, AmbientLight ambientLight, Geometries geometries, Camera camera,
			double distance, List<LightSource> lights, boolean boxing, boolean withThreds , boolean toBuildTree) {
		_scene = scene;
		_background = background;
		_ambientLight = ambientLight;
		_geometries = geometries;
		_camera = camera;
		_distance = distance;
		_lights = new ArrayList<LightSource>();
		_boxing = boxing;
		_withThreds = withThreds;
		_toBuildTree=toBuildTree;
	}

	/**
	 * old
	 * 
	 * Constructor receiving scene name background color ambientLight geometries
	 * camera and distance
	 * 
	 * @param scene
	 * @param background
	 * @param ambientLight
	 * @param geometries
	 * @param camera
	 * @param distance
	 * @param              List<LightSource> lights
	 */
	public Scene(String scene, Color background, AmbientLight ambientLight, Geometries geometries, Camera camera,
			double distance, List<LightSource> lights) {
		_scene = scene;
		_background = background;
		_ambientLight = ambientLight;
		_geometries = geometries;
		_camera = camera;
		_distance = distance;
		_lights = new ArrayList<LightSource>();
	}

	/**
	 * for old tests Constructor receiving scene name background color ambientLight
	 * geometries camera and distance
	 * 
	 * @param scene
	 * @param background
	 * @param ambientLight
	 * @param geometries
	 * @param camera
	 * @param distance
	 */
	public Scene(String scene, Color background, AmbientLight ambientLight, Geometries geometries, Camera camera,
			double distance) {
		_scene = scene;
		_background = background;
		_ambientLight = ambientLight;
		_geometries = geometries;
		_camera = camera;
		_distance = distance;
		_lights = new ArrayList<LightSource>();

	}

	/**
	 * copy constructor
	 * 
	 * @param scene other
	 */
	public Scene(Scene scene) {
		_scene = scene._scene;
		_background = scene._background;
		_geometries = scene._geometries;
		_camera = scene._camera;
		_distance = scene._distance;
		_ambientLight = scene.getAmbientLight();
		_lights = scene.getLights();
	}

	/**
	 * Constructor receiving only scene name _geometries will empty
	 * 
	 * @param scene
	 */
	public Scene(String scene) {
		_scene = scene;
		_geometries = new Geometries();
		_ambientLight = new AmbientLight(Color.BLACK, 0);
		_lights = new ArrayList<LightSource>();
	}

	// -------------------------------getters and
	// settes-------------------------------------------//

	/**
	 * @return the _background
	 */
	public Color getBackground() {
		return _background;
	}

	/**
	 * @param background the _background to set
	 */
	public void setBackground(Color background) {
		_background = background;
	}

	/**
	 * @return the _ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}

	/**
	 * @param ambientLight the _ambientLight to set
	 */
	public void setAmbientLight(AmbientLight ambientLight) {
		_ambientLight = ambientLight;
	}

	/**
	 * @return the _geometries
	 */
	public Geometries getGeometries() {
		return _geometries;
	}

	/**
	 * @param geometries the _geometries to set
	 */
	public void setGeometries(Geometries geometries) {
		_geometries = geometries;
	}

	/**
	 * add shape or shapes to list of shapes
	 * 
	 * @param geometries
	 */
	public void addShape(Intersectable geometries) {
		_geometries.addShape(geometries);
	}

	/**
	 * @return the scene
	 */
	public String getScene() {
		return _scene;
	}

	/**
	 * @return the _camera
	 */
	public Camera getCamera() {
		return _camera;
	}

	/**
	 * @return the _distance
	 */
	public double getDistance() {
		return _distance;
	}

	/**
	 * 
	 * @param camera the _camera to set & _distance the _distance to set
	 */
	public void setCameraAndDistance(Camera camera, double distance) {
		_camera = camera;
		_distance = distance;
	}

	/**
	 * @return the _lights
	 */
	public List<LightSource> getLights() {
		return _lights;
	}

	/**
	 * @param lights the _lights to set
	 */
	public void setLights(List<LightSource> lights) {
		_lights = lights;
	}

	/**
	 * this func get all kinds of lights and add them to the list
	 * 
	 * @param lights
	 */
	public void addLight(LightSource... lights) {
		for (LightSource light : lights)
			_lights.add(light);

	}

	/**
	 * make the static filed boxing active or not
	 * 
	 * @param true or false
	 */
	public void setUnboxing(boolean unboxing) {
		_boxing = unboxing;
	}

	/**
	 * make the static filed withThreds active or not
	 * 
	 * @param true or false
	 */
	public void setWithThreds(boolean withThreds) {
		_withThreds = withThreds;
	}

	// ---------------------------------------------------------Operations-------------------------------------------------------//
//	/**
//	 * 
//	 * @return iterator to the list of geometries
//	 */
//	public Iterator<Intersectable> getGeometriesIterator() {// to check
//		return (this._geometries.geometries).iterator();
	// }

}
