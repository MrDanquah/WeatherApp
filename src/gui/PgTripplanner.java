package gui;

import java.util.Calendar;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import api.Trip;

public class PgTripplanner extends Page{
	private ScrollPane scrollPane;
	private GridPane scrollContent;
	
	private class TripplannerPane {
		public VBox infoPane;
		
		private String days[] = {
				", Sun", ", Mon", ", Tue", ", Wed", ", Thu", ", Fri", ", Sat"};
		
		public TripplannerPane(Trip trip) {
			infoPane = new VBox();
			infoPane.getStyleClass().add("bordered");
			
			// Trip start and dest names
			HBox tripNames = new HBox();
			tripNames.getStyleClass().add("bordered");
			tripNames.setAlignment(Pos.TOP_CENTER);
			
			Label startName = new Label(trip.getStart());
			startName.getStyleClass().add("locname");
			startName.setPrefWidth(150);
			startName.setAlignment(Pos.CENTER_LEFT);
			tripNames.getChildren().add(startName);
			
			Label arrow = new Label(">");
			arrow.getStyleClass().add("arrow");
			arrow.setAlignment(Pos.CENTER);
			tripNames.getChildren().add(arrow);
			
			Label destName = new Label(trip.getDest());
			destName.getStyleClass().add("locname");
			destName.setPrefWidth(150);
			destName.setAlignment(Pos.CENTER_RIGHT);
			destName.setTextAlignment(TextAlignment.RIGHT);
			tripNames.getChildren().add(destName);
			
			infoPane.getChildren().add(tripNames);
			
			// Arrival time
			Label arrivalTime = new Label(trip.getArriveTime());
			arrivalTime.getStyleClass().add("tripplannertime");
			arrivalTime.setPrefWidth(Double.MAX_VALUE);
			arrivalTime.setAlignment(Pos.CENTER_RIGHT);
			arrivalTime.setTextAlignment(TextAlignment.RIGHT);
			infoPane.getChildren().add(arrivalTime);
			
			// Repeated days and delete button
			HBox repeatAndDelete = new HBox();
			repeatAndDelete.getStyleClass().add("bordered");
			repeatAndDelete.setPrefWidth(Double.MAX_VALUE);
			repeatAndDelete.setAlignment(Pos.BASELINE_CENTER);
			
			String repeatDays = "";
			for(int i = 0; i < 7; i++) {
				if(WeatherApp.trips.get(0).getRepeat()[i]) {
					repeatDays += days[i];
				}
			}
			if(!repeatDays.isEmpty()) {
				repeatDays = repeatDays.substring(2);
			}
			
			Label repeats = new Label(repeatDays);
			repeats.getStyleClass().add("tripplannerrepeat");
			repeats.setAlignment(Pos.CENTER_LEFT);
			repeatAndDelete.getChildren().add(repeats);
			
			// We need to use this special region to allow the text to be left aligned
			// and the button to be right aligned.
			Region region = new Region();
			HBox.setHgrow(region, Priority.ALWAYS);
			repeatAndDelete.getChildren().add(region);
			
			Button delete = new Button("D");
			delete.getStyleClass().add("deletebtn");
			delete.setOnAction(e -> {
	        	System.out.println("delete");
	        });
			delete.setAlignment(Pos.CENTER_RIGHT);
			repeatAndDelete.getChildren().add(delete);
			
			infoPane.getChildren().add(repeatAndDelete);
			infoPane.setOnMouseClicked(e -> {
	        	System.out.println("clicked infopane");
	        });
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	public PgTripplanner() {
		name = "tripplanner";
		displayName = "Trip Planner";
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
		scrollContent = new GridPane();
		scrollContent.setPrefWidth(320);
		
		Button btn1 = new Button();
		btn1.setGraphic((new TripplannerPane(WeatherApp.trips.get(0))).getPane());
        btn1.setPrefSize(320, 108);
        btn1.setMaxHeight(108);
        btn1.setId("btn1");
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);
        scrollContent.getChildren().add(btn1);
		
		scrollPane.setContent(scrollContent);
        pageGrid.getChildren().add(scrollPane);
	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub
		
	}

}
