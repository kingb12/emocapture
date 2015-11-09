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
    	//The camera
    	Capture cam = new Capture();
    	int signal = 0; //default value for no significant signal
    	int lastSignal = 0;
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
				e.printStackTrace();
			}
    		emotiondata = EmoVu.makeCall(IMAGE_PATH);
    		try {
    			delay(1000);
    			if (emotiondata != null) {
    				signal = getState();
    			}
    		} catch (NullPointerException e) {
    			System.out.println("no face in view");
    			signal = -1;
    		}

    		//saw a face but it wont buzz
    		if(signal >= 0) {
    				if(signal != 0 && signal != lastSignal) {
    					arm.send(signal);
    				} else {
    					System.out.print(0);
    				}
    				//doesn't handle an extreme edge case: someone smiles, no face is seen again for some time, another smiles
    				lastSignal = signal;
    		}
    	}	
    }
	public static void delay(int i) {
		try {
			TimeUnit.MILLISECONDS.sleep(i);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
    public static int getState() {
		
		double anger = 0;
		double joy = 0;
		double attn = 0;
		double disgust = 0;
		double surprise = 0;
		if (emotiondata == null) {
			return -1;
		}
		 anger = (Double) emotiondata.get("Anger");
		 joy = (Double) emotiondata.get("Joy");
		 surprise = (Double) emotiondata.get("Surprise");
		 attn = (Double) emotiondata.get("Attention");
		 disgust = (Double) emotiondata.get("Disgust");
		
		//Anger
		if((anger > 0.08 && attn > 0.5) || anger > 0.15) {
			System.out.println("\nanger");
			return 1;
		}
		//Joy
		if((joy > 0.08 && attn > 0.5) || joy > 0.25) {
			System.out.println("\njoy");
			return 2;
		}
		//Disgust
		if((disgust > 0.15 && attn > 0.5) || disgust > 0.3) {
			System.out.println("\ndisgust");
			return 3;
		}
		//Surprise
		if((surprise > 0.10 && attn > 0.5) || surprise > 0.3) {
			System.out.println("\nsurprise");
			return 4;
		}
		return 0;
	}
}
