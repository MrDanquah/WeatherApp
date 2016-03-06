package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class YWeatherConnection {
	
	public class City {
		private int woeid;
		private String name;
		private String country;
		private String admin1;
		
		public City(int woeid, String name, String country, String admin1) {
			this.woeid = woeid;
			this.name = name;
			this.country = country;
			this.admin1 = admin1;
		}
		
		public int getWOEID() {
			return woeid;
		}
		
		public String getName() {
			return name;
		}
		
		public String getCountry() {
			return country;
		}
		
		public String getAdmin1() {
			return admin1;
		}
		
		public String getFullName() {
			return name + ", " + admin1 + ", " + country;
		}
	}
	
	private String API_KEY = "dj0yJmk9ZUVqWHRabkRxdlZLJmQ9WVdrOVNERlFibnBHTjJzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02ZA--";
	
	private String sendQuery(String query) {
		String response = "";
        try {
            query = URLEncoder.encode(query, "UTF-8");
            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ query
                    + "&format=json");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
            		new InputStreamReader(connection.getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
            	response += inputLine;
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return response;
	}
	
	public List<City> searchLocation(String city) throws Exception {
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
	
	public void getWeather(int woeid) {
		String query = "select * from weather.forecast where woeid=" + woeid;
		
		String response = sendQuery(query);
		
		parseWeatherJSON(response);
	}
	
	private void parseWeatherJSON(String jsonString) {
		// Get the result/results from the query
		JSONObject results = (new JSONObject(jsonString)).getJSONObject("query").
				getJSONObject("results").getJSONObject("channel");
		
		JSONObject currentCond = results.getJSONObject("item").getJSONObject("condition");
		// TODO apparently sometimes the condition code is 3200. need to take care of that

		String outstr = currentCond.getString("text");
		System.out.println(outstr);
		System.out.println(results.getClass().getName());
	}
	
	public static void main(String args[]) {
		YWeatherConnection myWeather = new YWeatherConnection();
		
		try {
			List<City> cities = myWeather.searchLocation("London, UK");
			if(cities.isEmpty()) {
				// empty
			} else {
				myWeather.getWeather(cities.get(0).getWOEID());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
