/**
 * 
 */
package elements;

import primitives.*;

/**
 * This class represents a camera
 */
public class Camera {
	Point _p0;
	Vector _vUp, _vTo, _vRight;

	/**
	 * ctor receive Vector vUp, Vector vTo, Point cameraPoint
	 * 
	 * @param vUp
	 * @param vTo
	 * @param cameraPoint
	 */
	public Camera(Vector vUp, Vector vTo, Point cameraPoint) {
		// if the vector are not vertical to each other
		if (!(Util.isZero(vUp.dotProduct(vTo)))) {
			throw new IllegalArgumentException("Vectors are not vertical to each other");
		}
		this._vUp = vUp.normalized_Vector();
		this._vTo = vTo.normalized_Vector();
		this._vRight = new Vector(vUp.crossProduct(vTo).normalized_Vector());
		this._p0 = cameraPoint;
	}

	public Point getP0() {
		return _p0;
	}

	public Vector getVUp() {
		return _vUp;
	}

	public Vector getVTo() {
		return _vTo;
	}

	public Vector getVRight() {
		return _vRight;
	}

	/*********** Administration ***********/

	/**
	 * @param Nx             number of pixels in the width
	 * @param Ny             number of pixels in the height
	 * @param                i,j index of the viewPlane where the ray
	 * @param screenDistance distance from the camera to the viewplane
	 * @param width          width of the viewplane
	 * @param height         height of the viewplane We assume that the pixels we
	 *                       received are within the boundaries of the view plane
	 * @return the ray from the camera to the viewplane
	 */
//	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth, // לישראל עשיתי פה סוופ (J-I)
//			double screenHight) {
//		
//		Point pc = _p0.add(_vTo.scale(screenDistance)); // pc is the center of the view plane
//		double Ry = screenHight / Ny; // height of pixel
//		double Rx = screenWidth / Nx; // width of pixel
//		double pcX = ((double) Nx + 1) / 2;
//		double pcY = ((double) Ny + 1) / 2;
//		Point Pij = pc;
//		if (pcX != i) // if i==pcX then exception vector zero
//			Pij = Pij.add(_vRight.scale((i - pcX) * Rx));
//		if (pcY != j) // if j==pcY then exception vector zero
//			Pij = Pij.add(_vUp.scale((pcY - j) * Ry));
//		Vector Vij = Pij.substract(_p0);
//		Ray ray = new Ray(_p0, Vij);
//		return ray;
//	}
//	
	/**
	 * 
	 * @param nX             number of pixels X
	 * @param nY             number of pixels Y
	 * @param i
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return Ray construct Ray Through Pixel i,j
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {
		if (i < 0 && i >= nX && j < 0 && j >= nY)
			throw new IllegalArgumentException("illegal argument exception");

		Point pCenter = this._p0.add(this._vTo.scale(screenDistance));
		double rY = screenHeight / nY;
		double rX = screenWidth / nX;
		double yJ = (j - (nY / 2.0)) * rY + rY / 2.0;
		double xI = (i - (nX / 2.0)) * rX + rX / 2.0;
		Point pIJ = pCenter;
		if (xI != 0)
			pIJ = pIJ.add(this._vRight.scale(xI));
		if (yJ != 0)
			pIJ = pIJ.add(this._vUp.scale(-yJ));
		Vector vIJ = pIJ.substract(this._p0);
		return new Ray(this._p0, vIJ); // ctor of ray performs the normalization
	}
}
