package gui;

import gui.PgOverview.ForecastPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import api.WeatherAPI;

public class PgTriplist extends Page{
	private ScrollPane scrollPane;
	private GridPane scrollContent;

	public PgTriplist() {
		name = "triplist";
	}
	
	@Override
	void createContent() {
		scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefSize(320, 432);
		
		scrollContent = new GridPane();
		scrollContent.setPrefWidth(320);
		
		WeatherAPI weather = new WeatherAPI("44418");
		
		Button btn1 = new Button();
		btn1.setGraphic((new ForecastPane(weather, 0)).getPane());
        btn1.setPrefSize(320, 108);
        btn1.setMaxHeight(108);
        btn1.setId("btn1");
        btn1.setOnAction(e -> {
        	System.out.println("changing to triplist");
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);
        scrollContent.getChildren().add(btn1);
        
        Button btn2 = new Button();
        btn2.setGraphic((new ForecastPane(weather, 1)).getPane());
        btn2.setPrefSize(320, 108);
        btn2.setMaxHeight(108);
        btn2.setId("btn2");
        btn2.setOnAction(e -> {
        	System.out.println("btn2");
        });
        GridPane.setRowIndex(btn2, 1);
        GridPane.setColumnIndex(btn2, 0);
        scrollContent.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setGraphic((new ForecastPane(weather, 2)).getPane());
        btn3.setPrefSize(320, 108);
        btn3.setMaxHeight(108);
        btn3.setId("btn3");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN3 pressed!");
            }
        });
        GridPane.setRowIndex(btn3, 2);
        GridPane.setColumnIndex(btn3, 0);
        scrollContent.getChildren().add(btn3);
        
        Button btn4 = new Button();
        btn4.setGraphic((new ForecastPane(weather, 3)).getPane());
        btn4.setPrefSize(320, 108);
        btn4.setMaxHeight(108);
        btn4.setId("btn4");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN4 pressed!");
            }
        });
        GridPane.setRowIndex(btn4, 3);
        GridPane.setColumnIndex(btn4, 0);
        scrollContent.getChildren().add(btn4);
        
        Button btn5 = new Button();
        btn5.setGraphic((new ForecastPane(weather, 3)).getPane());
        btn5.setPrefSize(320, 108);
        btn5.setMaxHeight(108);
        btn5.setId("btn4");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN5 pressed!");
            }
        });
        GridPane.setRowIndex(btn5, 4);
        GridPane.setColumnIndex(btn5, 0);
        scrollContent.getChildren().add(btn5);
        
        scrollPane.setContent(scrollContent);
        pageGrid.getChildren().add(scrollPane);
	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}

}
