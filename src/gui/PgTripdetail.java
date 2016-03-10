package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import api.Trip;

public class PgTripdetail extends Page{

	private class TripdetailPane {
		public VBox infoPane;
		
		public TripdetailPane(Trip trip, boolean isStart) {
			infoPane = new VBox();
			infoPane.setAlignment(Pos.CENTER);
			infoPane.getStyleClass().add("bordered");
			
			// Location
			Label name = new Label(isStart ? trip.getStart() : trip.getDest());
			name.getStyleClass().add("tripdetailname");
			name.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(name);
			
			// Time
			Label leaveTime = new Label(isStart ? 
					"Leave " + trip.getLeaveTime() : "Arrive " + trip.getArriveTime());
			leaveTime.getStyleClass().add("tripdetailtime");
			leaveTime.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(leaveTime);
			
			// Weather
			HBox weatherDetails = new HBox();
			weatherDetails.setAlignment(Pos.CENTER);
			
			VBox temps = new VBox();
			temps.setAlignment(Pos.CENTER);
			temps.setPrefWidth(100);
			
			Label exactTemp = new Label("1");
			exactTemp.getStyleClass().add("tripdetailexacttemp");
			exactTemp.setAlignment(Pos.CENTER);
			temps.getChildren().add(exactTemp);
			
			HBox hilo = new HBox();
			hilo.setAlignment(Pos.CENTER);
			
			Label lo = new Label("1");
			lo.getStyleClass().add("tripdetailhilo");
			lo.setAlignment(Pos.CENTER);
			hilo.getChildren().add(lo);
			
			Label hi = new Label("2");
			hi.getStyleClass().add("tripdetailhilo");
			hi.setAlignment(Pos.CENTER);
			hilo.getChildren().add(hi);
			
			temps.getChildren().add(hilo);
			weatherDetails.getChildren().add(temps);
			
			Label weatherIcon = new Label("1");
			weatherIcon.getStyleClass().add("weathericon");
			weatherIcon.setId("tripdetailwicon");
			weatherIcon.setPrefWidth(120);
			weatherIcon.setTranslateY(-5);
			weatherIcon.setAlignment(Pos.CENTER);
			weatherDetails.getChildren().add(weatherIcon);
			
			VBox windDetails = new VBox();
			windDetails.setAlignment(Pos.CENTER);
			windDetails.setPrefWidth(100);
			
			Label windIcon = new Label(".");
			windIcon.getStyleClass().add("weathericon");
			windIcon.setId("tripdetailwicon");
			windIcon.setTranslateY(-5);
			windIcon.setAlignment(Pos.CENTER);
			windDetails.getChildren().add(windIcon);
			
			Label windSpeed = new Label("X mph");
			windSpeed.getStyleClass().add("tripdetailwindspeed");
			windSpeed.setAlignment(Pos.CENTER);
			windDetails.getChildren().add(windSpeed);
			
			weatherDetails.getChildren().add(windDetails);
			
			infoPane.getChildren().add(weatherDetails);
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	public PgTripdetail() {
		super("tripdetail", "Trip Detail", "Back", 
				WeatherApp.getTripsofDay(WeatherApp.currentlyViewingDay).size() > 1 ? 
						"Next Trip" : "");
	}
	
	@Override
	void createContent() {
		TripdetailPane start = new TripdetailPane(WeatherApp.currentlyViewingTrip, true);
		TripdetailPane dest = new TripdetailPane(WeatherApp.currentlyViewingTrip, false);
		
		GridPane.setColumnIndex(start.getPane(), 0);
		GridPane.setRowIndex(start.getPane(), 0);
		mainContentGrid.getChildren().add(start.getPane());
		
		GridPane.setColumnIndex(dest.getPane(), 0);
		GridPane.setRowIndex(dest.getPane(), 1);
		mainContentGrid.getChildren().add(dest.getPane());
	}
	
	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	void leftButtonAction() {
		WeatherApp.changePage("triplist");
	}

	@Override
	void rightButtonAction() {
		// Depends on whether there is a next trip
	}
}
