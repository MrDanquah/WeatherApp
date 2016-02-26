package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PgTriplist extends Page{

	public PgTriplist() {
		name = "triplist";
	}
	
	@Override
	void createButtons() {
		Button btn1 = new Button();
        btn1.setText("1");
        btn1.setPrefSize(320, 108);
        btn1.setId("btn1");
        btn1.setOnAction(e -> {
        	System.out.println("changing to overview");
        	WeatherApp.changePage("overview");
        });
        GridPane.setRowIndex(btn1, 1);
        GridPane.setColumnIndex(btn1, 1);
        pageGrid.getChildren().add(btn1);
	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}

}
