package gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import api.Trip;
import api.Weather;
import api.YWeatherConnection;

public class PgTriplist extends Page{
	private ScrollPane scrollPane;
	private VBox scrollContent;
	
	private class TriplistPane {
		public HBox infoPane;
		
		public TriplistPane(Trip trip) {
			// First get the weather for this trip on the day we are looking at
			Weather startWeather = YWeatherConnection.
					getWeather(trip.getStartWoeid()).get(WeatherApp.currentlyViewingDayIdx);
			Weather destWeather = YWeatherConnection.
					getWeather(trip.getDestWoeid()).get(WeatherApp.currentlyViewingDayIdx);
			
			infoPane = new HBox();
			infoPane.setAlignment(Pos.CENTER);
			infoPane.getStyleClass().add("bordered");
			
			// Start location info
			VBox startPane = new VBox();
			startPane.getStyleClass().add("bordered");
			startPane.setPrefWidth(150);
			
			Label startName = new Label(trip.getStart());
			startName.getStyleClass().add("triplistname");
			startName.setAlignment(Pos.CENTER_LEFT);
			startPane.getChildren().add(startName);
			
			HBox startWeatherPane = new HBox();
			startWeatherPane.setAlignment(Pos.CENTER);
			
			Label startWeatherIcon = new Label(
					WeatherApp.weatherIconMap[startWeather.getCondCode()]);
			startWeatherIcon.getStyleClass().add("weathericon");
			startWeatherIcon.setId("triplistwicon");
			startWeatherIcon.setPrefWidth(75);
			startWeatherIcon.setTranslateY(-5);
			startWeatherIcon.setAlignment(Pos.CENTER);
			startWeatherPane.getChildren().add(startWeatherIcon);
			
			Label startTemp = new Label(startWeather.getCurrentTemp() + "\u2103");
			startTemp.getStyleClass().add("triplisttemp");
			startTemp.setPrefWidth(75);
			startTemp.setAlignment(Pos.CENTER);
			startWeatherPane.getChildren().add(startTemp);
			
			startPane.getChildren().add(startWeatherPane);
			
			Label leaveTime = new Label("Leave " + trip.getLeaveTime());
			leaveTime.getStyleClass().add("triplisttime");
			leaveTime.setAlignment(Pos.CENTER_LEFT);
			startPane.getChildren().add(leaveTime);
			
			infoPane.getChildren().add(startPane);
			
			// Arrow
			Label arrow = new Label(">");
			arrow.getStyleClass().add("arrow");
			arrow.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(arrow);
			
			// Destination location info
			VBox destPane = new VBox();
			destPane.getStyleClass().add("bordered");
			destPane.setPrefWidth(150);
			
			Label destName = new Label(trip.getDest());
			destName.getStyleClass().add("triplistname");
			destName.setAlignment(Pos.CENTER_RIGHT);
			destName.setTextAlignment(TextAlignment.RIGHT);
			destPane.getChildren().add(destName);
			
			HBox destWeatherPane = new HBox();
			destWeatherPane.setAlignment(Pos.CENTER);
			
			Label destWeatherIcon = new Label(
					WeatherApp.weatherIconMap[destWeather.getCondCode()]);
			destWeatherIcon.getStyleClass().add("weathericon");
			destWeatherIcon.setId("triplistwicon");
			destWeatherIcon.setPrefWidth(75);
			destWeatherIcon.setTranslateY(-5);
			destWeatherIcon.setAlignment(Pos.CENTER);
			destWeatherPane.getChildren().add(destWeatherIcon);
			
			Label destTemp = new Label(destWeather.getCurrentTemp() + "\u2103");
			destTemp.getStyleClass().add("triplisttemp");
			destTemp.setPrefWidth(75);
			destTemp.setAlignment(Pos.CENTER);
			destWeatherPane.getChildren().add(destTemp);
			
			destPane.getChildren().add(destWeatherPane);
			
			Label arriveTime = new Label("Arrive " + trip.getArriveTime());
			arriveTime.getStyleClass().add("triplisttime");
			arriveTime.setAlignment(Pos.CENTER_RIGHT);
			arriveTime.setTextAlignment(TextAlignment.RIGHT);
			destPane.getChildren().add(arriveTime);
			
			infoPane.getChildren().add(destPane);
		}
		
		public HBox getPane() {
			return infoPane;
		}
	}

	public PgTriplist() {
		super("triplist", "Trip List", "Back", "Clothing Suggestion");
	}
	
	@Override
	void createContent() {
		// Set up the scroll pane
		scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefSize(320, 432);
		
		// Set up and create the content in the scroll pane
		scrollContent = new VBox();
		scrollContent.setPrefWidth(320);
		
		List<Trip> todayTrips = new ArrayList<Trip>();
		
		for(Trip trip : WeatherApp.trips) {
			// Display only trips for this day
			if(trip.getRepeat()[WeatherApp.currentlyViewingDay]) {
				todayTrips.add(trip);
			}
		}
		
		Button btn1 = new Button();
		btn1.setGraphic((new TriplistPane(todayTrips.get(0))).getPane());
        btn1.setPrefSize(320, 108);
        btn1.setMaxHeight(108);
        btn1.setId("btn1");
        btn1.setOnAction(e -> {
        	System.out.println("changing to tripdetail");
        	WeatherApp.changePage("tripdetail");
        });
        scrollContent.getChildren().add(btn1);
        
        scrollPane.setContent(scrollContent);
        mainContentGrid.getChildren().add(scrollPane);
	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void leftButtonAction() {
		WeatherApp.changePage("overview");
	}

	@Override
	void rightButtonAction() {
		WeatherApp.changePage("clothsuggest");
	}

}
