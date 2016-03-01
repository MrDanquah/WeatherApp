package gui;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import api.WeatherAPI;

public class PgOverview extends Page{
	
	public class ForecastPane {
		public BorderPane pane;
		private Label weatherIcon;
		
		private String[] weatherIconMap = {
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
		
		public ForecastPane(WeatherAPI weather, int daysAhead) {
			pane = new BorderPane();
			
			Label date = new Label(weather.weatherForecastList.get(daysAhead).dateTemp);
			date.getStyleClass().add("date");
			//date.setPrefWidth(110);
			BorderPane.setAlignment(date, Pos.CENTER);
			pane.setLeft(date);
			
			weatherIcon = new Label("1");
			weatherIcon.getStyleClass().add("weathericon");
			//weatherIcon.setPrefWidth(100);
			BorderPane.setAlignment(weatherIcon, Pos.CENTER);
			pane.setCenter(weatherIcon);
			
			Label temps = new Label(weather.weatherForecastList.get(daysAhead).lowTemp + " " + 
					weather.weatherForecastList.get(daysAhead).highTemp);
			temps.getStyleClass().add("temps");
			//temps.setPrefWidth(110);
			BorderPane.setAlignment(temps, Pos.CENTER);
			pane.setRight(temps);
			
		}
		
		public BorderPane getPane() {
			return pane;
		}
		
		public void changeWeatherIcon(int weather) {
			weatherIcon.setText(weatherIconMap[weather]);
		}
	}
	
	public PgOverview() {
		name = "overview";
	}
	
	int weathercount = 0;
	
	@Override
	void createButtons() {
		WeatherAPI weather = new WeatherAPI("44418");
		
		Button btn1 = new Button();
		btn1.setGraphic((new ForecastPane(weather, 0)).getPane());
        btn1.setPrefSize(320, 108);
        btn1.setId("btn1");
        btn1.setOnAction(e -> {
        	System.out.println("changing to triplist");
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn1, 1);
        GridPane.setColumnIndex(btn1, 1);
        pageGrid.getChildren().add(btn1);
        
        ForecastPane forePane = new ForecastPane(weather, 1);
        
        Button btn2 = new Button();
        btn2.setGraphic(forePane.getPane());
        btn2.setPrefSize(320, 108);
        btn2.setId("btn2");
        btn2.setOnAction(e -> {
        	weathercount++;
        	System.out.println(weathercount);
        	forePane.changeWeatherIcon(weathercount);
        });
        GridPane.setRowIndex(btn2, 2);
        GridPane.setColumnIndex(btn2, 1);
        pageGrid.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setGraphic((new ForecastPane(weather, 2)).getPane());
        btn3.setPrefSize(320, 108);
        btn3.setId("btn3");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN3 pressed!");
            }
        });
        GridPane.setRowIndex(btn3, 3);
        GridPane.setColumnIndex(btn3, 1);
        pageGrid.getChildren().add(btn3);
        
        Button btn4 = new Button();
        btn4.setGraphic((new ForecastPane(weather, 3)).getPane());
        btn4.setPrefSize(320, 108);
        btn4.setId("btn4");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN4 pressed!");
            }
        });
        GridPane.setRowIndex(btn4, 4);
        GridPane.setColumnIndex(btn4, 1);
        pageGrid.getChildren().add(btn4);
	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}

}
