
package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import org.junit.experimental.theories.Theories;

import renderer.ImageWriter;
import sun.awt.im.InputMethodManager;

/**
 * tests for ImageWriter makes grid and circle
 */
public class ImageWriterTest {

	ImageWriter image1 = new ImageWriter("yosef circle", 1000, 1000, 1000, 1000);
	ImageWriter image2 = new ImageWriter("israel grid", 500, 500, 500, 500);

	/**
	 * "yosef test" circle "israel test" grid
	 */
	@Test
	public void test() {

		for (int i = 1; i < 1000; i++) {// test circle
			for (int j = 1; j < 1000; j++) {
				double d = Math.sqrt((500 - i) * (500 - i) + (500 - j) * (500 - j));
				if (d < 310 && d > 290) {// For thick view
					this.image1.writePixel(i, j, 200, 0, 0);
				}
			}
		}
		this.image1.writeToimage();

		for (int i = 0; i < 500; i++) {// test grid
			if (i % 50 == 0) {
				for (int j = 0; j < 500; j++) {
					this.image2.writePixel(i, j, 255, 255, 255);
					this.image2.writePixel(j, i, 255, 255, 255);
				}
			}
		}

		this.image2.writeToimage();

	}

}
