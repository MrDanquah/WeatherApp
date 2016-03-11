package gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import api.Weather;
import api.YWeatherConnection;

public class IpadPgOverview extends IpadPage{
	
	private class ForecastPane {
		public HBox infoPane;
		private Label weatherIcon;
		
		public ForecastPane(Weather weather) {
			infoPane = new HBox();
			infoPane.setAlignment(Pos.CENTER);
			
			// Day and date
			VBox dayAndDate = new VBox();
			dayAndDate.setAlignment(Pos.CENTER_LEFT);
			dayAndDate.setPadding(new Insets(0, 0, 0, 10));
			dayAndDate.setPrefWidth(176);
			
			SimpleDateFormat sdfmt = new SimpleDateFormat("EEE");
			Calendar date = weather.getDate();

			String dayText = weather.getIsForecast() ? sdfmt.format(date.getTime()) : "TODAY";
			Label day = new Label(dayText.toUpperCase());
			day.getStyleClass().add("overviewday");
			day.setAlignment(Pos.CENTER_LEFT);
			dayAndDate.getChildren().add(day);
			
			sdfmt = new SimpleDateFormat("MMM d");
			Label monthAndDate = new Label(sdfmt.format(date.getTime()).toUpperCase());
			monthAndDate.getStyleClass().add("overviewdate");
			monthAndDate.setAlignment(Pos.CENTER_LEFT);
			dayAndDate.getChildren().add(monthAndDate);
			
			infoPane.getChildren().add(dayAndDate);
			
			weatherIcon = new Label(WeatherApp.weatherIconMap[weather.getCondCode()]);
			weatherIcon.getStyleClass().add("weathericon");
			weatherIcon.setId("overviewwicon");
			weatherIcon.setTranslateY(-5);
			weatherIcon.setTranslateX(5);
			weatherIcon.setPrefWidth(160);
			weatherIcon.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(weatherIcon);
			
			VBox tempAndText = new VBox();
			tempAndText.setAlignment(Pos.CENTER);
			tempAndText.setPrefWidth(176);
			
			if(weather.getIsForecast()) {
				HBox hilo = new HBox();
				hilo.setAlignment(Pos.CENTER);
				
				Label lo = new Label(weather.getLo() + "°");
				lo.getStyleClass().add("overviewhilo");
				lo.setId("darkgreytext");
				hilo.getChildren().add(lo);
				
				Label hi = new Label(weather.getHi() + "°");
				hi.getStyleClass().add("overviewhilo");
				hilo.getChildren().add(hi);
				
				tempAndText.getChildren().add(hilo);
			} else {
				Label temp = new Label(weather.getCurrentTemp() + "\u2103");
				temp.getStyleClass().add("overviewtemp");
				
				tempAndText.getChildren().add(temp);
			}
			
			Label condText = new Label(weather.getCondText());
			condText.getStyleClass().add("overviewtext");
			condText.setAlignment(Pos.CENTER);
			tempAndText.getChildren().add(condText);

			infoPane.getChildren().add(tempAndText);
		}
		
		public HBox getPane() {
			return infoPane;
		}
	}
	
	private class CurrentWeatherPane {
		public VBox infoPane;
		
		public CurrentWeatherPane(Weather weather) {
			infoPane = new VBox();
			infoPane.setAlignment(Pos.TOP_CENTER);
			infoPane.setPrefHeight(700);
			infoPane.setStyle("-fx-background-color: " + WeatherApp.
	        		colorMap[weather.getDate().get(Calendar.DAY_OF_WEEK) - 1]);
			
			
			
			// Location
			Label name = new Label("Current London Weather");
			name.getStyleClass().add("tripdetailname");
			name.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(name);
			
			// Time
			SimpleDateFormat sdfmt = new SimpleDateFormat("h:mm a");
			Calendar time = Calendar.getInstance();
			
			Label curTime = new Label(sdfmt.format(time.getTime()));
			curTime.getStyleClass().add("tripdetailtime");
			curTime.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(curTime);
			
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
			
			Label lo = new Label(weather.getLo() + "°");
			lo.getStyleClass().add("tripdetailhilo");
			lo.setId("darkgreytext");
			lo.setPadding(new Insets(0, 3, 0, 0));
			hilo.getChildren().add(lo);
			
			Label hi = new Label(weather.getHi() + "°");
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
			
			VBox moreinfo = new VBox();
			moreinfo.setPadding(new Insets(0, 0, 0, 15));
			moreinfo.setAlignment(Pos.CENTER_LEFT);
			
			Label humidity = new Label("Humidity: " + weather.getHumidity() + "%");
			humidity.getStyleClass().add("tripdetailmoreinfo");
			moreinfo.getChildren().add(humidity);
			
			String visibilityText = weather.getVisibility() > 2 ? "Good" : "Bad";
			Label visibility = new Label("Visibility: " + visibilityText);
			visibility.getStyleClass().add("tripdetailmoreinfo");
			moreinfo.getChildren().add(visibility);
			
			infoPane.getChildren().add(moreinfo);
			
			// Special region used to help with centering
			Region bottomRegion = new Region();
			VBox.setVgrow(bottomRegion, Priority.ALWAYS);
			infoPane.getChildren().add(bottomRegion);
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	public IpadPgOverview() {
		super("overview", "Overview", "Overview", "Trip Planner");
	}
	
	int weathercount = 0;
	
	@Override
	void createContent() {
		// Get the weather for London
		List<Weather> londonWeather = YWeatherConnection.getWeather(44418);
		
		Button btn1 = new Button();
		btn1.setGraphic((new ForecastPane(londonWeather.get(0))).getPane());
        btn1.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
        btn1.setStyle("-fx-background-color: " + WeatherApp.
        		colorMap[londonWeather.get(0).getDate().get(Calendar.DAY_OF_WEEK) - 1]);
        btn1.setOnAction(e -> {
        	WeatherApp.currentlyViewingDayIdx = 0;
        	WeatherApp.currentlyViewingDay = 
        			londonWeather.get(0).getDate().get(Calendar.DAY_OF_WEEK) - 1;
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);
        leftContentGrid.getChildren().add(btn1);
        
        Button btn2 = new Button();
        btn2.setGraphic((new ForecastPane(londonWeather.get(1))).getPane());
        btn2.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
        btn2.setStyle("-fx-background-color: " + WeatherApp.
        		colorMap[londonWeather.get(1).getDate().get(Calendar.DAY_OF_WEEK) - 1]);
        btn2.setOnAction(e -> {
        	WeatherApp.currentlyViewingDayIdx = 1;
        	WeatherApp.currentlyViewingDay = 
        			londonWeather.get(1).getDate().get(Calendar.DAY_OF_WEEK) - 1;
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn2, 1);
        GridPane.setColumnIndex(btn2, 0);
        leftContentGrid.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setGraphic((new ForecastPane(londonWeather.get(2))).getPane());
        btn3.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
        btn3.setStyle("-fx-background-color: " + WeatherApp.
        		colorMap[londonWeather.get(2).getDate().get(Calendar.DAY_OF_WEEK) - 1]);
        btn3.setOnAction(e -> {
        	WeatherApp.currentlyViewingDayIdx = 2;
        	WeatherApp.currentlyViewingDay = 
        			londonWeather.get(2).getDate().get(Calendar.DAY_OF_WEEK) - 1;
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn3, 2);
        GridPane.setColumnIndex(btn3, 0);
        leftContentGrid.getChildren().add(btn3);
        
        Button btn4 = new Button();
        btn4.setGraphic((new ForecastPane(londonWeather.get(3))).getPane());
        btn4.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
        btn4.setStyle("-fx-background-color: " + WeatherApp.
        		colorMap[londonWeather.get(3).getDate().get(Calendar.DAY_OF_WEEK) - 1]);
        btn4.setOnAction(e -> {
        	WeatherApp.currentlyViewingDayIdx = 3;
        	WeatherApp.currentlyViewingDay = 
        			londonWeather.get(3).getDate().get(Calendar.DAY_OF_WEEK) - 1;
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn4, 3);
        GridPane.setColumnIndex(btn4, 0);
        leftContentGrid.getChildren().add(btn4);
        
        // Add the current london weather pane to the right
        CurrentWeatherPane curWeather = new CurrentWeatherPane(londonWeather.get(0));
        rightContentGrid.getChildren().add(curWeather.getPane());
	}

	@Override
	void leftButtonAction() {
		
	}

	@Override
	void rightButtonAction() {
		WeatherApp.changePage("tripplanner");
	}
}
