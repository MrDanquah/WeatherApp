package api;

import java.util.Calendar;

public class Weather {
	private boolean isForecast; // Determines whether [current] is valid
	private int current;
	private int hi, lo;
	private int hourly[];
	private int windDir, windSpeed;
	private int condCode;
	private Calendar date;
	private String location;
	
	public Weather(boolean isForecast, int current, int hi, int lo, int hourly[], int windDir, int windSpeed,
			int condCode, Calendar date, String location) {
		
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
}
