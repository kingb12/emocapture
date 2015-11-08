package emo_capture.emo_capture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;

public class CaptureDriver {
	public static void main(String[] args) {
		Capture c = new Capture();
		c.open();
		BufferedImage i = c.image();
		try {
			ImageIO.write(i, "PNG", new File("activecaptures/test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = EmoVu.makeCall("activecaptures/test.png");
		for(Object a: result.entrySet()) {
			System.out.println(a);
		}
	}
}
