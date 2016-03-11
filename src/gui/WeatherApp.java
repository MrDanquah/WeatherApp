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
import javafx.stage.StageStyle;

public class WeatherApp extends Application{
	private static Stage window;
	public static boolean isIphone = true; // Whether we are running iPhone or iPad version
	private static Map<String, Page> iPhonePages = new HashMap<String, Page>();
	private static Map<String, IpadPage> iPadPages = new HashMap<String, IpadPage>();
	public static List<Trip> trips = new ArrayList<Trip>();
	// We use these global references/variables to allow different pages to know  
	// what day and trip we are looking at.
	public static int currentlyViewingDayIdx; // 0 is for today, 1 for tomorrow, 2..., 3...
	public static int currentlyViewingDay;
	public static Trip currentlyViewingTrip;
	public static String currentPageName;
	
	// Color map for each day of the week
	public static final String[] colorMap = {
		"#00bff3", "#fbaf5d", "#3cb878", "#f26d7d", "#c7b299", "#605ca8", "#65722d"
	};
	
	// Weather icon map for different weather conditions
	// Based on Yahoo Weather condition codes
	public static final String[] weatherIconMap = {
			":", "O", "O", "Q", "Q", 
			"W", "U", "I", "M", "M",
			"U", "U", "U", "I", "I",
			"I", "I", "W", "W", "…",
			"Z", "Z", "…", ",", ",",
			"“", "3", "3", "3", "a",
			"A", "6", "1", "6", "2",
			"W", "‘", "Y", "Y", "Y",
			"G", "I", "W", "I", "A",
			"S", "W", "Y"
	};
	
	// Wind icon map
	public static final String[] windIconMap = {
		"\u0023", "\u002e", "\u00b7", "\u003f", "\u00bf",  "\"",  "\u0027", "\u003b"
	};
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	// Load the custom weather icons font
        //primaryStage.initStyle(StageStyle.UNDECORATED);

    	Font.loadFont(getClass().getResource("/artillclean_fixed.otf").toExternalForm(), 12f);
    	
    	window = primaryStage;
    	window.setTitle("Commuter weather app");
    	window.setResizable(false); // Not resizable
    	
    	// Add two demo trips
    	Calendar start = Calendar.getInstance();
		start.set(0, 0, 0, 10, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(0, 0, 0, 1, 0);
		
		trips.add(new Trip("Brent", 12695823, "Tower Hamlets", 12695817, start, end, 
				new boolean[]{false, true, true, true, true, true, false}));
		
		start.set(0, 0, 0, 15, 0);
		end.set(0, 0, 0, 0, 30);
		
		trips.add(new Trip("Tower Hamlet", 12695817, "Southwark", 12695816, start, end, 
				new boolean[]{false, false, false, false, true, true, false}));
		
		currentlyViewingDay = 1;
		currentlyViewingTrip = trips.get(0);
    	
		// Create iPhone pages
    	Page overview = new PgOverview();
    	Page triplist = new PgTriplist();
    	Page tripplanner = new PgTripplanner();
    	Page tripdetail = new PgTripdetail();
    	Page editTrip = new PgEdittrip();
    	iPhonePages.put(overview.getName(), overview);
    	iPhonePages.put(triplist.getName(), triplist);
    	iPhonePages.put(tripplanner.getName(), tripplanner);
    	iPhonePages.put(tripdetail.getName(), tripdetail);
    	iPhonePages.put(editTrip.getName(), editTrip);
    	
    	
    	// Create iPad pages
    	IpadPage ipadoverview = new IpadPgOverview();
    	IpadPage ipadtriplist = new IpadPgTriplist();
    	IpadPage ipadtripplanner = new IpadPgTripplanner();
    	IpadPage ipadtripdetail = new IpadPgTripdetail();
    	IpadPage ipadeditTrip = new IpadPgEdittrip();
    	iPadPages.put(ipadoverview.getName(), ipadoverview);
    	iPadPages.put(ipadtriplist.getName(), ipadtriplist);
    	iPadPages.put(ipadtripplanner.getName(), ipadtripplanner);
    	iPadPages.put(ipadtripdetail.getName(), ipadtripdetail);
    	iPadPages.put(ipadeditTrip.getName(), ipadeditTrip);
        
    	if(isIphone) {
    		window.setScene(overview.getScene());
    	} else {
    		window.setScene(ipadoverview.getScene());
    	}
    	currentPageName = "overview";
        window.show();
    }
    
    public static void changePage(String name) {
    	if(isIphone) {
    		iPhonePages.get(name).refreshPage();
        	window.setScene(iPhonePages.get(name).getScene());
    	} else {
    		iPadPages.get(name).refreshPage();
        	window.setScene(iPadPages.get(name).getScene());
    	}
    	currentPageName = name;
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
