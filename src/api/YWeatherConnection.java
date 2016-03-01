package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class YWeatherConnection {

	public void searchQuery(String city) {
		
		city.toLowerCase();
		String query = "select * from weather.forecast where woeid in (select woeid from geo.places where text='san francisco, ca')";
		String API_KEY = "dj0yJmk9ZUVqWHRabkRxdlZLJmQ9WVdrOVNERlFibnBHTjJzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02ZA--";

	    URL url;
        try {
            query = URLEncoder.encode(query, "UTF-8");
            url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ query
                    + "&format=json");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                    System.out.println(query + "  " + inputLine);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
     

	}
	
	public static void main(String args[]) {
		YWeatherConnection myWeather = new YWeatherConnection();
		
		myWeather.searchQuery("London");
	}

}
