package gui;

import java.util.Calendar;

import api.Weather;

public class Trip {
	private String start;
	private String dest;
	private Calendar arrivetime; // We are using calendar for ease of storing time
	private Calendar commutetime; // but we will only be using the time fields
	private boolean repeat[]; // [Sun, Mon, Tue, Wed, Thu, Fri, Sat]
	private Weather startWeather;
	private Weather destWeather;
	
	public Trip(String start, String dest, Calendar arrivetime, Calendar commutetime, boolean repeat[]) {
		this.setStart(start);
		this.setDest(dest);
		this.arrivetime = arrivetime;
		this.commutetime = commutetime;
		this.repeat = new boolean[7];
		System.arraycopy(repeat, 0, this.repeat, 0, repeat.length);
		//TODO get the actual weather
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}
	
	public String getLeaveTime() {
		return arrivetime.get(Calendar.HOUR) + ":" + arrivetime.get(Calendar.MINUTE);
	}
	
	public String getArriveTime() {
		return (arrivetime.get(Calendar.HOUR) + commutetime.get(Calendar.HOUR))
				+ ":" + (arrivetime.get(Calendar.MINUTE) + commutetime.get(Calendar.MINUTE));
	}
}
