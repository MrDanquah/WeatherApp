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
import javafx.scene.layout.VBox;
import api.Weather;
import api.YWeatherConnection;

public class PgOverview extends Page{
	
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
			dayAndDate.setPrefWidth(110);
			
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
			weatherIcon.setPrefWidth(100);
			weatherIcon.setAlignment(Pos.CENTER);
			infoPane.getChildren().add(weatherIcon);
			
			VBox tempAndText = new VBox();
			tempAndText.setAlignment(Pos.CENTER);
			tempAndText.setPrefWidth(110);
			
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
	
	public PgOverview() {
		super("overview", "Overview", "Overview", "Trip Planner");
	}
	
	int weathercount = 0;
	
	@Override
	void createContent() {
		// Get the weather for London
		List<Weather> londonWeather = YWeatherConnection.getWeather(44418);
		
		Button btn1 = new Button();
		btn1.setGraphic((new ForecastPane(londonWeather.get(0))).getPane());
        btn1.setPrefSize(320, 108);
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
        mainContentGrid.getChildren().add(btn1);
        
        Button btn2 = new Button();
        btn2.setGraphic((new ForecastPane(londonWeather.get(1))).getPane());
        btn2.setPrefSize(320, 108);
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
        mainContentGrid.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setGraphic((new ForecastPane(londonWeather.get(2))).getPane());
        btn3.setPrefSize(320, 108);
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
        mainContentGrid.getChildren().add(btn3);
        
        Button btn4 = new Button();
        btn4.setGraphic((new ForecastPane(londonWeather.get(3))).getPane());
        btn4.setPrefSize(320, 108);
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
        mainContentGrid.getChildren().add(btn4);
	}

	@Override
	void leftButtonAction() {
		
	}

	@Override
	void rightButtonAction() {
		WeatherApp.changePage("tripplanner");
	}
}
