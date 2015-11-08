package emo_capture.emo_capture;

import java.util.Map;

public class Emotion {
	public static int getState(Map<String, Object> emotiondata) {
		
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
