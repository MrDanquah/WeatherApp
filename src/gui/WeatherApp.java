package gui;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class WeatherApp extends Application{
	static Stage window;
	private static Map<String, Page> pages = new HashMap<String, Page>();
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	window = primaryStage;
    	window.setTitle("Hello World!");
    	window.setResizable(false); // Not resizable
    	
    	Page overview = new PgOverview();
    	Page triplist = new PgTriplist();
    	pages.put(overview.getName(), overview);
    	pages.put(triplist.getName(), triplist);
    	
        window.setScene(overview.pageScene);
        window.show();
    }
    
    public static void changePage(String name) {
    	window.setScene(pages.get(name).getScene());
    }
}
