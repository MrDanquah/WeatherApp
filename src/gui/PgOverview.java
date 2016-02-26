package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PgOverview extends Page{
	
	public PgOverview() {
		name = "overview";
	}
	
	@Override
	void createButtons() {
		Button btn1 = new Button();
        btn1.setText("1");
        btn1.setPrefSize(320, 108);
        btn1.setId("btn1");
        btn1.setOnAction(e -> {
        	System.out.println("changing to triplist");
        	WeatherApp.changePage("triplist");
        });
        GridPane.setRowIndex(btn1, 1);
        GridPane.setColumnIndex(btn1, 1);
        pageGrid.getChildren().add(btn1);
        
        Button btn2 = new Button();
        btn2.setText("A");
        btn2.setPrefSize(320, 108);
        btn2.setId("btn2");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN2 pressed!");
            }
        });
        GridPane.setRowIndex(btn2, 2);
        GridPane.setColumnIndex(btn2, 1);
        pageGrid.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setText("F");
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
        btn4.setText("W");
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
