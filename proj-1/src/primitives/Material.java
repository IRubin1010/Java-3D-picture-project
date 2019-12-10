package primitives;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * class represents material
 */
public class Material {

	public int _kDust;
	double _kD;
	double _kS;
	double _kR;
	double _kT;
	int _nShininess;



	/**
	 * constructor with dust
	 * 
	 * @param kd         Diffuse factor
	 * @param ks         Specular factor
	 * @param Kr         Reflection factor
	 * @param Kt         Transparency factor
	 * @param nShininess object Shininess
	 * @param kDust      The amount of opacity of the surface
	 */
	public Material(double kd, double ks, int nShininess, double kr, double kt, int kDust) {
		_kDust = kDust;
		_kD = kd;
		_kS = ks;
		_kR = kr;
		_kT = kt;
		_nShininess = nShininess;

	}

	/***************** Getteres ****************************/

	/**
	 * get Kd
	 * 
	 * @return the Diffuse factor
	 */
	public double getKd() {
		return _kD;
	}

	/**
	 * get Ks
	 * 
	 * @return the Specular factor
	 */
	public double getKs() {
		return _kS;
	}

	/**
	 * get nShininess
	 * 
	 * @return the object Shininess
	 */
	public int getNShininess() {
		return _nShininess;
	}

	/**
	 * @return the _kR
	 */
	public double getKr() {
		return _kR;
	}

	/**
	 * @return the _kT
	 */
	public double getKt() {
		return _kT;
	}

//#############################################################################################3
	/**
	 * constructor
	 * 
	 * @param kd         Diffuse factor
	 * @param ks         Specular factor
	 * @param Kr         Reflection factor
	 * @param Kt         Transparency factor
	 * @param nShininess object Shininess
	 */

	public Material(double kd, double ks, int nShininess, double kr, double kt) {
		_kD = kd;
		_kS = ks;
		_kR = kr;
		_kT = kt;
		_nShininess = nShininess;

	}
	public Material(double kd, double ks, int nShininess) {
		_kD = kd;
		_kS = ks;
		_nShininess = nShininess;

	}

}
