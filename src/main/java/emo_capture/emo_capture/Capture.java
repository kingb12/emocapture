package emo_capture.emo_capture;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class Capture {
	private Webcam webcam;
	
    public Capture() {
    	this.webcam = Webcam.getDefault();
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
    
}
