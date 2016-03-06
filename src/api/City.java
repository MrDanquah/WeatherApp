package api;

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
