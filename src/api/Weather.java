package api;

import java.util.Calendar;

public class Weather {
	private boolean isForecast; // Determines whether [current] is valid
	private int current;
	private int hi, lo;
	private int hourly[];
	private int windDir, windSpeed;
	private int condCode;
	private String condText;
	private Calendar date;
	private String location;
	
	public Weather(boolean isForecast, int current, int hi, int lo, int windDir, int windSpeed,
			int condCode, String condText, Calendar date, String location) {
		this.isForecast = isForecast;
		this.current = current;
		this.hi = hi;
		this.lo = lo;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
		this.condCode = condCode;
		this.condText = condText;
		this.date = date;
		this.location = location;
	}
	
	public int getCurrentTemp() {
		return current;
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
	
	public int getCondCode() {
		return condCode;
	}
	
	public String getCondText() {
		return condText;
	}
}
