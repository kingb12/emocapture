package emo_capture.emo_capture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


public class EmoVu {
		
	public static Map<String, Object> makeCall(String imagePath)  {
		// These code snippets use an open-source library.
		HttpResponse<JsonNode> response;	
		try {
			response = Unirest.post("http://api.emovu.com/api/image/")
					.header("LicenseKey", "11016217615942693089611712482576111520002601814012667106011556263176911502")
					.field("imageFile", new File(imagePath))
					.asJson();
			Map<String, Object> result = new HashMap<String, Object>();
			JSONArray arr = response.getBody().getArray();
			List<?> list = new ArrayList<String>();
			//Fills a map with values from a JSON Structure of JSONArrays + JSONObjects
			parseJSON((Object) arr, result, null);
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	/*public static void main(String[] args) {
		Map<String, Object> result = makeCall("ObamaSmiling.png");
		for (Object b: result.entrySet()) {
			System.out.println(b);
		}
	}*/
	
	private static void parseJSON(Object obj, Map<String, Object> result, String key) {
		//obj is a JSONArray
		if (obj.getClass().equals(JSONArray.class)) {
			JSONArray arr = (JSONArray) obj;
			for (int i = 0; i < arr.length(); i++) {
				parseJSON(arr.getJSONObject(i), result, null);
			}
		} 
		//obj is a JSONObject
		else if (obj.getClass().equals(JSONObject.class)) {
			JSONObject o = (JSONObject) obj;
			for (Object k: o.keySet()) {
				parseJSON(o.get((String) k), result, (String) k);
			}
			
		//obj is (hopefully) a String, but could be something else	
		} else {
			result.put(key, (Double) obj);
		}		
	}
}

