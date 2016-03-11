package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class YWeatherConnection {
	
	private String API_KEY = "dj0yJmk9ZUVqWHRabkRxdlZLJmQ9WVdrOVNERlFibnBHTjJzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02ZA--";
	
	// Ghetto cache to speed up page refresh
	private static Map<String, String> queryResponseCache = new HashMap<String, String>();
	
	// Method to query the YQL server for a JSON response
	private static String sendQuery(String query) {
		String response = queryResponseCache.get(query);
		if(response == null) {
			response = "";
			try {
	            String outQuery = URLEncoder.encode(query, "UTF-8");
	            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ outQuery
	                    + "&format=json");
	            URLConnection connection = url.openConnection();
	            BufferedReader in = new BufferedReader(
	            		new InputStreamReader(connection.getInputStream()));
	            String inputLine = "";
	            while ((inputLine = in.readLine()) != null) {
	            	response += inputLine;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			queryResponseCache.put(query, response);
		}
        
        return response;
	}
	
	// Given a location name, return a list of cities
	public static List<City> searchLocation(String city) throws Exception {
		List<City> cities = new ArrayList<City>();
		
		String query = "select woeid, name, country, admin1 from geo.places where text='" 
				+ city.toLowerCase() + "'";
		
		String response = sendQuery(query);
		
		/* Check if we got a single result, multiple results, or no result.
		 * Yahoo weather has a terrible JSON structure and likes to use
		 * different types for single vs multiple results
		 * Single result = channel is a JSON object
		 * Multiple results = channel is a JSON array
		 * No results = null 
		 */
		if((new JSONObject(response)).getJSONObject("query").getInt("count") == 0) {
			// No results
		} else {
			// Get the result/results from the query
			JSONObject results = (new JSONObject(response)).getJSONObject("query").
					getJSONObject("results");
			
			// Check if single or multiple results
			// Add results to the list of cities
			JSONObject cityInfo;
			if((new JSONObject(response)).getJSONObject("query").getInt("count") == 1) {
				// Lovely, we got a single result
				cityInfo = results.getJSONObject("place");
				cities.add(new City(
						cityInfo.getInt("woeid"), cityInfo.getString("name"), 
						cityInfo.getJSONObject("country").getString("content"), 
						cityInfo.getJSONObject("admin1").getString("content")));
			} else if((new JSONObject(response)).getJSONObject("query").getInt("count") > 1) {
				// How unfortunate, we got multiple results
				JSONArray citiesInfos = results.getJSONArray("place");
				for(Object singleCityInfo : citiesInfos) {
					cityInfo = (JSONObject) singleCityInfo;
					cities.add(new City(
							cityInfo.getInt("woeid"), cityInfo.getString("name"), 
							cityInfo.getJSONObject("country").getString("content"), 
							cityInfo.getJSONObject("admin1").getString("content")));
				}
			} else {
				// Something unexpected happened
				throw new Exception("Oops, something went wrong with the weather query.");
			}
		}
		
		return cities;
	}
	
	// Get the 4 day weather for a specific location (woeid)
	public static List<Weather> getWeather(int woeid) {
		List<Weather> weatherData = new ArrayList<Weather>();
		
		String query = "select * from weather.forecast where u='c' and woeid=" + woeid;
		
		String response = sendQuery(query);
		
		// Get the result/results from the query
		JSONObject results = (new JSONObject(response)).getJSONObject("query").
				getJSONObject("results").getJSONObject("channel");
		
		int current;
		int hi, lo;
		int windDir, windSpeed;
		int condCode;
		String condText;
		Calendar date;
		String location;
		
		// First get the weather info for today
		JSONObject currentCond = results.getJSONObject("item").getJSONObject("condition");
		if(currentCond.getInt("code") == 3200) {
			condCode = 34; // We'll just set the weather to be fair
			condText = "";
		} else {
			condCode = currentCond.getInt("code");
			condText = currentCond.getString("text");
		}
		current = currentCond.getInt("temp");
		
		JSONArray forecast = results.getJSONObject("item").getJSONArray("forecast");
		hi = forecast.getJSONObject(0).getInt("high");
		lo = forecast.getJSONObject(0).getInt("low");
		
		windDir = results.getJSONObject("wind").getInt("direction");
		windSpeed = (int) results.getJSONObject("wind").getDouble("speed");
		
		SimpleDateFormat sdfmt = new SimpleDateFormat("d MMM yyyy");
		date = Calendar.getInstance();
		// Stupid Yahoo Weather doesn't update at midnight, so we are stuck with 
		// old weather data for an hour or so at midnight
		try {
			date.setTime(sdfmt.parse(forecast.getJSONObject(0).getString("date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		location = results.getJSONObject("location").getString("city");
		
		weatherData.add(new Weather(
				false, current, hi, lo, windDir, windSpeed, condCode, condText, date, location, woeid));
		
		// Get the forecast for next 3 days
		for(int i = 1; i < 4; i++) {
			condCode = forecast.getJSONObject(i).getInt("code");
			condText = forecast.getJSONObject(i).getString("text");
			
			hi = forecast.getJSONObject(i).getInt("high");
			lo = forecast.getJSONObject(i).getInt("low");
			
			try {
				date.setTime(sdfmt.parse(forecast.getJSONObject(i).getString("date")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			weatherData.add(new Weather(
					true, current, hi, lo, windDir, windSpeed, condCode, condText, date, location, woeid));
		}
		
		return weatherData;
	}
}
