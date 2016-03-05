package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;


public class YWeatherConnection {

	public void searchQuery(String city) {
		String query = "select * from weather.forecast where woeid in "
				+ "(select woeid from geo.places where text='" + city.toLowerCase() + "')";
		String API_KEY = "dj0yJmk9ZUVqWHRabkRxdlZLJmQ9WVdrOVNERlFibnBHTjJzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02ZA--";

	    URL url;
        try {
            query = URLEncoder.encode(query, "UTF-8");
            url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ query
                    + "&format=json");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
            		new InputStreamReader(connection.getInputStream()));
            String receivedJsonString = "";
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
            	receivedJsonString += inputLine;
                System.out.println(receivedJsonString);
            }
            
            parseWeatherJSON(receivedJsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void parseWeatherJSON(String jsonString) throws Exception {
		// Get the result/results from the query
		Object results = (new JSONObject(jsonString)).getJSONObject("query").
				getJSONObject("results").get("channel");
		// TODO need to make sure results is not null
		
		/* Check if we got a single result, multiple results, or no result.
		 * Yahoo weather has a terrible JSON structure and likes to use
		 * different types for single vs multiple results
		 * Single result = channel is a JSON object
		 * Multiple results = channel is a JSON array
		 * No results = results is the string "null" 
		 */
		JSONObject weatherData;
		if(results instanceof JSONObject) {
			// Lovely, we got a single result
			weatherData = ((JSONObject) results);
		} else if(results instanceof JSONArray) {
			// How unfortunate, we got multiple results
			// TODO need to actually let the user know
			weatherData = ((JSONArray) results).getJSONObject(0);
		} else {
			// Something really bad happened
			throw new Exception("Oops, something went wrong with the weather query.");
		}
		
		JSONObject currentCond = weatherData.getJSONObject("item").getJSONObject("condition");
		// TODO apparently sometimes the condition code is 3200. need to take care of that

		String outstr = currentCond.getString("text");
		System.out.println(outstr);
		System.out.println(results.getClass().getName());
	}
	
	public static void main(String args[]) {
		YWeatherConnection myWeather = new YWeatherConnection();
		
		myWeather.searchQuery("London, UK");
	}

}
