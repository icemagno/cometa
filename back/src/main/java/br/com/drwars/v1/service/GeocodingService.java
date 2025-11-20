package br.com.drwars.v1.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {
	
	public JSONObject getCoordinates( String endereco ) {
		RestTemplate rt = new RestTemplate();
		String url = "https://geocode.maps.co/search?api_key=65dbe0643d818430885212kyc2a582f&q=" + endereco;
		String response = rt.getForObject(url, String.class);
		JSONArray resp = new JSONArray( response );
		JSONObject result = new JSONObject();
		if( resp.length() > 0 ) {
			JSONObject temp = resp.getJSONObject(0);
			result.put("lat", temp.getString("lat") );
			result.put("lon", temp.getString("lon")  );
			result.put("display_name", temp.getString("display_name") );

		}
		return result;
	}
	
}
