// Yosef Naftali 303064315 yosef684@gmail.com
// Israel Rubin 312513377 srulik1132@gmail.com

package main;

import primitives.*;
import geometries.*;

public class proj {

	public static void main(String[] args) {

		try {
			Vector aVector = new Vector(0, 5.999999999999994, 0);
			System.out.println(aVector);
			System.out.println("sss");
			// -------------------------Coordinate----------------------------------//
//			Coordinate coordinate1 = new Coordinate(2.5);
//			Coordinate coordinate2 = new Coordinate(7.5);
//			Coordinate coordinate3 = coordinate1.subtract(coordinate2);
//			System.out.println("Coordinates:");
//			System.out.println(coordinate1 + " " + coordinate2 + " " + coordinate3);
//
//			// -----------------------------Point-----------------------------------//
//			Point point1 = new Point(1, 2, 3);
//			Point point2 = new Point(2, 4, 6);
//			Point point3 = new Point(5, 6, 9);
//			System.out.println("Points:");
//			System.out.println(point1);
//			System.out.println(point2);
//			System.out.println(point3);
//
//			// -----------------------------Vector----------------------------------//
//			Vector vector1 = new Vector(3.0, 1.0, 1.0);
//			Vector vector2 = new Vector(point1);
//			Vector vector3 = point1.substract(point2);
//			Vector vector4 = vector1.subtract(vector3);
//			Vector vector5 = vector1.scale(2);
//			Vector vector6 = vector1.crossProduct(vector2);
//			Vector vector7 = vector1.normalized_Vector();
//			System.out.println("Vectors:");
//			System.out.println(vector1);
//			System.out.println(vector2);
//			System.out.println(vector3);
//			System.out.println(vector4);
//			System.out.println(vector5);
//			System.out.println(vector6);
//			System.out.println(vector7);
//			System.out.println("menurmal " + vector7.length());
//
//			// -------------------------------Ray------------------------------------//
//			Ray ray1 = new Ray(point1, vector1);
//			Ray ray2 = new Ray(ray1);
//			System.out.println("Rays:");
//			System.out.println(ray1);
//			System.out.println(ray2);

			// ------------------------------Tube-----------------------------------------//
//			Tube tube = new Tube(2.5, ray1);
//			System.out.println("Tube:");
//			System.out.println(tube);
//
//			// -------------------------Cylinder-----------------------------------------//
//			Cylinder cylinder = new Cylinder(1.5, ray1, 1.5, 1.5);
//			System.out.println("Cylinder:");
//			System.out.println(cylinder);
//
//			// ---------------------------Plane------------------------------------------------//
//			Plane plane = new Plane(vector1.getPoint(), vector1.normalized_Vector());
//			System.out.println("Plane:");
//			System.out.println(plane);
//
//			// ---------------------------Sphere-------------------------------------------------//
//			Sphere sphere = new Sphere(4, new Point(0, 0, 0));
//			System.out.println("Sphere:");
//			System.out.println(sphere);
//
//			// ----------------------------Triangle--------------------------------------------------//
//			Triangle triangle = new Triangle(point1, point2, point3);
//			System.out.println("Triangle:");
//			System.out.println(triangle);
//
//			// ----------------------------------------------------------------------------------------//
//			Tube tusbe = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
//			System.out.println("Tube:");			System.out.println(tusbe.getNormal(new Point(1,0,0)));
//			Tube tube2 = new Tube(1, new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
//			System.out.println(tube2.getNormal(new Point(0,0,4)));
////			Point point1 = new Point(0, 0, 4);
////			Point point2 = new Point(0, 0, 4);
////			point1.substract(point2);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
