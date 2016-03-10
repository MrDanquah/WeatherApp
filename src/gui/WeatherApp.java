package gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.Trip;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WeatherApp extends Application{
	private static Stage window;
	private static Map<String, Page> pages = new HashMap<String, Page>();
	public static List<Trip> trips = new ArrayList<Trip>();
	// We use these global references/variables to allow different pages to know  
	// what day and trip we are looking at.
	public static int currentlyViewingDay;
	public static Trip currentlyViewingTrip;
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	// Load the custom weather icons font
    	Font.loadFont(getClass().getResource("/artillclean_fixed.otf").toExternalForm(), 12f);
    	
    	window = primaryStage;
    	window.setTitle("Commuter weather app");
    	window.setResizable(false); // Not resizable
    	
    	// Add two demo trips
    	Calendar start = Calendar.getInstance();
		start.set(0, 0, 0, 10, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(0, 0, 0, 1, 0);
		
		trips.add(new Trip("Brent", "Tower Hamlet", start, end, 
				new boolean[]{false, true, true, true, true, true, false}));
		
		start.set(0, 0, 0, 15, 0);
		end.set(0, 0, 0, 0, 30);
		
		trips.add(new Trip("Tower Hamlet", "Southwark", start, end, 
				new boolean[]{false, false, false, false, true, true, false}));
		
		currentlyViewingDay = 1;
		currentlyViewingTrip = trips.get(0);
    	
    	Page overview = new PgOverview();
    	Page triplist = new PgTriplist();
    	Page tripplanner = new PgTripplanner();
    	Page tripdetail = new PgTripdetail();
    	Page editTrip = new PgEdittrip();
    	pages.put(overview.getName(), overview);
    	pages.put(triplist.getName(), triplist);
    	pages.put(tripplanner.getName(), tripplanner);
    	pages.put(tripdetail.getName(), tripdetail);
    	pages.put(editTrip.getName(), editTrip);
    	
        window.setScene(overview.getScene());
        window.show();
    }
    
    public static void changePage(String name) {
    	window.setScene(pages.get(name).getScene());
    }
    
    /*
     * day is the day of week
     * 0 = Sun, 1 = Mon, 2 = Tue, ... , 6 = Sat
     */
    public static List<Trip> getTripsofDay(int day) {
    	List<Trip> filteredTrips = new ArrayList<Trip>();
    	
    	for(Trip trip : trips) {
    		if(trip.getRepeat()[day]) {
    			filteredTrips.add(trip);
    		}
    	}
    	
    	return filteredTrips;
    }
}
