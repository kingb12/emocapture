package emo_capture.emo_capture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class Capture {
	private Webcam webcam;
	
    public Capture() {
    	this.webcam = Webcam.getDefault();
    	System.out.print("Found Webcam");
    }
    
    public void getName() {
    	System.out.print(webcam.getName());
    }
    
    public void open() {
    	webcam.open();
    }
    public BufferedImage image() {
    	return webcam.getImage();
    }
    
    public boolean writeImage(String filepath) throws Exception {
    	BufferedImage i = this.image();
		try {
			ImageIO.write(i, "PNG", new File(filepath));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
    }
    
}
