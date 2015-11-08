package emo_capture.emo_capture;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.joda.time.Instant;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String IMAGE_PATH = "activecaptures/image.png";
	private static Armband arm = new Armband();
	private static Map<String, Object> emotiondata = null;
    public static void main( String[] args ) {
    	System.out.println("Capturing Emotion! Enter q to terminate");
    	Instant time = Instant.now(); 
    	//The camera
    	Capture cam = new Capture();
    	int signal = 0; //default value for no significant signal
    	//Turn the camera on
    	cam.open();
    	//turn it off by force
    	while(true) {
    		//catches exception in case write image fails
    		try {
				cam.writeImage(IMAGE_PATH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("cam.writeImage() failed");
			}

    		int timestamp = time.compareTo(Instant.now());
    		emotiondata = EmoVu.makeCall(IMAGE_PATH, timestamp);
    		try {
    			try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			if (emotiondata != null) {
    				signal = getState();
    			}
    		} catch (NullPointerException e) {
    			
    			System.out.println("it was null");
    			//.printStackTrace();
    		}

    		//signal is significant
    		if(signal != 0) {
    			arm.send(signal);
    			try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	
    	
    }
    public static int getState() {
		
		int result = 0;
		double anger = 0;
		double joy = 0;
		double attn = 0;
		double disgust = 0;
		double surprise = 0;
		if (emotiondata != null) {
		 anger = (Double) emotiondata.get("Anger");
		 joy = (Double) emotiondata.get("Joy");
		 surprise = (Double) emotiondata.get("Surprise");
		 attn = (Double) emotiondata.get("Attention");
		 disgust = (Double) emotiondata.get("Disgust");
		}
		//Anger
		if((anger > 0.25 && attn > 0.5) || anger > 0.45) {
			result = 1;
		}
		//Joy
		if((joy > 0.25 && attn > 0.5) || joy > 0.45) {
			result = 2;
		}
		//Disgust
		if((disgust > 0.3 && attn > 0.8) || disgust > 0.5) {
			result = 3;
		}
		//Surprise
		if((surprise > 0.25 && attn > 0.5) || surprise > 0.7) {
			result = 4;
		}
		
		return result;
	}
}
