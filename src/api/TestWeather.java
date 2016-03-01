package api;

public class TestWeather
{

	public static void main(String[] args)
	{
		WeatherAPI weather = new WeatherAPI("44418");
		//System.out.println(weather.theWeatherRSS);
		for(int i = 0; i < weather.weatherForecastList.size(); i++)
		{
			System.out.println("Low temperature: " + weather.weatherForecastList.get(i).lowTemp + " High Temperature: " +
			weather.weatherForecastList.get(i).highTemp + " " + weather.weatherForecastList.get(i).dateTemp + " " + weather.weatherForecastList.get(i).textTemp);
		}
	}
}
