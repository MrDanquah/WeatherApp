package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import api.Trip;
import api.Weather;
import api.YWeatherConnection;

public class IpadPgTripdetail extends IpadPage{

	private class TripdetailPane {
		public VBox infoPane;
		
		public TripdetailPane(Trip trip, boolean isStart) {
			// First get the weather for this trip on the day we are looking at
			Weather weather = YWeatherConnection.
					getWeather(isStart ? trip.getStartWoeid() : trip.getDestWoeid()).
					get(WeatherApp.currentlyViewingDayIdx);
			
			infoPane = new VBox();
			infoPane.setAlignment(Pos.CENTER);
			infoPane.setPrefHeight(700);
			infoPane.setStyle("-fx-background-color: " + WeatherApp.
	        		colorMap[WeatherApp.currentlyViewingDay]);
			
			// Special region used to help with centering
			Region topRegion = new Region();
			VBox.setVgrow(topRegion, Priority.ALWAYS);
			infoPane.getChildren().add(topRegion);
			
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
			temps.setPrefWidth(160);
			
			Label exactTemp = new Label(weather.getCurrentTemp() + "\u2103");
			exactTemp.getStyleClass().add("tripdetailexacttemp");
			exactTemp.setAlignment(Pos.CENTER);
			temps.getChildren().add(exactTemp);
			
			HBox hilo = new HBox();
			hilo.setAlignment(Pos.CENTER);
			
			Label lo = new Label(weather.getLo() + "\u00b0");
			lo.getStyleClass().add("tripdetailhilo");
			lo.setId("darkgreytext");
			lo.setPadding(new Insets(0, 3, 0, 0));
			hilo.getChildren().add(lo);
			
			Label hi = new Label(weather.getHi() + "\u00b0");
			hi.getStyleClass().add("tripdetailhilo");
			hilo.getChildren().add(hi);
			
			temps.getChildren().add(hilo);
			weatherDetails.getChildren().add(temps);
			
			Label weatherIcon = new Label(
					WeatherApp.weatherIconMap[weather.getCondCode()]);
			weatherIcon.getStyleClass().add("weathericon");
			weatherIcon.setId("tripdetailwicon");
			weatherIcon.setPrefWidth(192);
			weatherIcon.setTranslateY(-10);
			weatherIcon.setAlignment(Pos.CENTER);
			weatherDetails.getChildren().add(weatherIcon);
			
			VBox windDetails = new VBox();
			windDetails.setAlignment(Pos.CENTER);
			windDetails.setPrefWidth(160);
			
			Label windIcon = new Label(WeatherApp.
					windIconMap[((weather.getWindDir() + 22)%360)/45]);
			windIcon.getStyleClass().add("weathericon");
			windIcon.setId("tripdetailwind");
			windIcon.setTranslateY(-10);
			windDetails.getChildren().add(windIcon);
			
			Label windSpeed = new Label(weather.getWindSpeed() + " mph");
			windSpeed.getStyleClass().add("tripdetailwindspeed");
			windDetails.getChildren().add(windSpeed);
			
			weatherDetails.getChildren().add(windDetails);
			
			infoPane.getChildren().add(weatherDetails);
			
			// Special region used to help with centering
			Region bottomRegion = new Region();
			VBox.setVgrow(bottomRegion, Priority.ALWAYS);
			infoPane.getChildren().add(bottomRegion);
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	public IpadPgTripdetail() {
		super("tripdetail", "Trip Detail", "Back", 
				WeatherApp.getTripsofDay(WeatherApp.currentlyViewingDay).size() > 1 ? 
						"Next Trip" : "");
	}
	
	@Override
	void createContent() {
		TripdetailPane start = new TripdetailPane(WeatherApp.currentlyViewingTrip, true);
		TripdetailPane dest = new TripdetailPane(WeatherApp.currentlyViewingTrip, false);
		
		start.getPane().setBorder(new Border(new BorderStroke(
				Color.web("#7d7d7d"), 
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(0, 1, 0, 0))));
		dest.getPane().setBorder(new Border(new BorderStroke(
				Color.web("#7d7d7d"), 
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(0, 0, 0, 1))));
		
		leftContentGrid.getChildren().add(start.getPane());
		rightContentGrid.getChildren().add(dest.getPane());
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
