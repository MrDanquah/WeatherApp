package gui;

import java.util.Calendar;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import api.Trip;

public class PgTriplist extends Page{
	private ScrollPane scrollPane;
	private VBox scrollContent;
	
	private class TriplistPane {
		public HBox infoPane;
		
		public TriplistPane(Trip trip) {
			infoPane = new HBox();
			infoPane.setAlignment(Pos.CENTER);
			infoPane.getStyleClass().add("bordered");
			
			// Start location info
			VBox startPane = new VBox();
			startPane.getStyleClass().add("bordered");
			startPane.setPrefWidth(150);
			
			Label startName = new Label(trip.getStart());
			startName.getStyleClass().add("locname");
			startName.setAlignment(Pos.CENTER_LEFT);
			startPane.getChildren().add(startName);
			
			HBox startWeatherPane = new HBox();
			startWeatherPane.setAlignment(Pos.CENTER);
			
			Label startWeatherIcon = new Label("1");
			startWeatherIcon.getStyleClass().add("weathericon");
			startWeatherIcon.setId("triplistwicon");
			startWeatherIcon.setPrefWidth(75);
			startWeatherIcon.setTranslateY(-5);
			startWeatherIcon.setAlignment(Pos.CENTER);
			startWeatherPane.getChildren().add(startWeatherIcon);
			
			Label startTemp = new Label("1");
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
			destName.getStyleClass().add("locname");
			destName.setAlignment(Pos.CENTER_RIGHT);
			destName.setTextAlignment(TextAlignment.RIGHT);
			destPane.getChildren().add(destName);
			
			HBox destWeatherPane = new HBox();
			destWeatherPane.setAlignment(Pos.CENTER);
			
			Label destWeatherIcon = new Label("1");
			destWeatherIcon.getStyleClass().add("weathericon");
			destWeatherIcon.setId("triplistwicon");
			destWeatherIcon.setPrefWidth(75);
			destWeatherIcon.setTranslateY(-5);
			destWeatherIcon.setAlignment(Pos.CENTER);
			destWeatherPane.getChildren().add(destWeatherIcon);
			
			Label destTemp = new Label("1");
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
		
		Calendar today = Calendar.getInstance();
		for(Trip trip : WeatherApp.trips) {
			// Display only trips for this day
			if(trip.getRepeat()[today.get(Calendar.DAY_OF_WEEK) - 1]) {
				// Add to list
			}
		}
		
		Calendar start = Calendar.getInstance();
		start.set(2016, 3, 4, 9, 0);
		
		Calendar end = Calendar.getInstance();
		System.out.println(end.get(Calendar.DAY_OF_WEEK));
		end.set(0, 0, 0, 1, 0);
		
		Trip testTrip = new Trip("Start", "Dest", start, end, 
				new boolean[]{false, false, false, false, false, false, false});
		
		Button btn1 = new Button();
		btn1.setGraphic((new TriplistPane(testTrip)).getPane());
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
