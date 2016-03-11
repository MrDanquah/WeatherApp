package api;

import java.util.Calendar;

public class Weather {
	private boolean isForecast; // Determines whether [current] is valid
	private int current;
	private int hi, lo;
	private int hourly[];
	private int windDir, windSpeed;
	private int condCode;
	private int humidity;
	private double visibility;
	private String condText;
	private Calendar date;
	private String location;
	private int woeid;
	
	public Weather(boolean isForecast, int current, int hi, int lo, int windDir, int windSpeed,
			int humidity, double visibility, int condCode, String condText, Calendar date, 
			String location, int woeid) {
		this.isForecast = isForecast;
		this.current = current;
		this.hi = hi;
		this.lo = lo;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
		this.humidity = humidity;
		this.visibility = visibility;
		this.condCode = condCode;
		this.condText = condText;
		this.date = (Calendar) date.clone();
		this.location = location;
		this.woeid = woeid;
	}
	
	public boolean getIsForecast() {
		return isForecast;
	}
	
	public int getCurrentTemp() {
		// Because we can't get hourly
		if(isForecast) {
			return (hi+lo)/2;
		} else {
			return current;
		}
	}
	
	public int getHi() {
		return hi;
	}
	
	public int getLo() {
		return lo;
	}
	
	public int getWindDir() {
		return windDir;
	}
	
	public int getWindSpeed() {
		return windSpeed;
	}
	
	public int getHumidity() {
		return humidity;
	}
	
	public double getVisibility() {
		return visibility;
	}
	
	public int getCondCode() {
		return condCode;
	}
	
	public String getCondText() {
		return condText;
	}
	
	public Calendar getDate() {
		return (Calendar) date.clone();
	}
}
