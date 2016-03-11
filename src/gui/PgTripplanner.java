package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import api.Trip;

public class PgTripplanner extends Page{
	private ScrollPane scrollPane;
	private VBox scrollContent;
	
	private class TripplannerPane {
		public VBox infoPane;
		
		private String days[] = {
				", Sun", ", Mon", ", Tue", ", Wed", ", Thu", ", Fri", ", Sat"};
		
		public TripplannerPane(Trip trip, int idx) {
			/*
			 * Due to the delete button being embedded inside the main large
			 * button, we can't actually use a regular button to represent the
			 * large button. Instead, we attach an event handler to the VBox
			 * so that it behaves like a button when clicked.
			 */
			infoPane = new VBox();
			infoPane.setPrefSize(Page.BTN_WIDTH, Page.BTN_HEIGHT);
			infoPane.setStyle("-fx-background-color: " + WeatherApp.
	        		colorMap[idx%7]);
			
			// Trip start and dest names
			HBox tripNames = new HBox();
			tripNames.setAlignment(Pos.TOP_CENTER);
			
			Label startName = new Label(trip.getStart());
			startName.getStyleClass().add("tripplannername");
			startName.setPrefWidth(150);
			startName.setAlignment(Pos.CENTER_LEFT);
			tripNames.getChildren().add(startName);
			
			// Arrow
			ImageView arrow = new ImageView("arrow_forward.png");
			arrow.setFitWidth(20);
			arrow.setPreserveRatio(true);
			arrow.setCache(true);
			tripNames.getChildren().add(arrow);
			
			Label destName = new Label(trip.getDest());
			destName.getStyleClass().add("tripplannername");
			destName.setPrefWidth(150);
			destName.setAlignment(Pos.CENTER_RIGHT);
			destName.setTextAlignment(TextAlignment.RIGHT);
			tripNames.getChildren().add(destName);
			
			infoPane.getChildren().add(tripNames);
			
			// Arrival time
			Region aboveTime = new Region();
			VBox.setVgrow(aboveTime, Priority.ALWAYS);
			infoPane.getChildren().add(aboveTime);
			
			Label arrivalTime = new Label(trip.getArriveTime());
			arrivalTime.getStyleClass().add("tripplannertime");
			arrivalTime.setPrefWidth(Double.MAX_VALUE);
			arrivalTime.setAlignment(Pos.CENTER_RIGHT);
			arrivalTime.setTextAlignment(TextAlignment.RIGHT);
			infoPane.getChildren().add(arrivalTime);
			
			Region belowTime = new Region();
			VBox.setVgrow(belowTime, Priority.ALWAYS);
			infoPane.getChildren().add(belowTime);
			
			// Repeated days and delete button
			HBox repeatAndDelete = new HBox();
			repeatAndDelete.setPrefWidth(Double.MAX_VALUE);
			repeatAndDelete.setAlignment(Pos.BASELINE_CENTER);
			repeatAndDelete.setPadding(new Insets(0, 5, 0, 5));
			
			String repeatDays = "";
			for(int i = 0; i < 7; i++) {
				if(trip.getRepeat()[i]) {
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
			
			ImageView delete = new ImageView("delete.png");
			delete.setFitWidth(20);
			delete.setPreserveRatio(true);
			delete.setCache(true);
			Button deleteBtn = new Button();
			deleteBtn.setGraphic(delete);
			deleteBtn.setOnAction(e -> {
	        	WeatherApp.trips.remove(idx);
	        	refreshPage();
	        });
			deleteBtn.setAlignment(Pos.CENTER_RIGHT);
			repeatAndDelete.getChildren().add(deleteBtn);
			
			infoPane.getChildren().add(repeatAndDelete);
			infoPane.setOnMouseClicked(e -> {
				WeatherApp.currentlyViewingTrip = trip;
	        	WeatherApp.changePage("edittrip");
	        });
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	public PgTripplanner() {
		super("tripplanner", "Trip Planner", "Overview", "Trip Planner");
	}

	@Override
	void createContent() {
		// Set up the scroll pane
		scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefSize(Page.BTN_WIDTH, Page.BTN_HEIGHT * 4);
		
		// Set up and create the content in the scroll pane
		scrollContent = new VBox();
		scrollContent.setPrefWidth(Page.BTN_WIDTH);
		
		int idx = 0;
		for(Trip trip : WeatherApp.trips) {
			scrollContent.getChildren().add(
	        		(new TripplannerPane(trip, idx)).getPane());
			idx++;
		}
		
		Button add = new Button();
		add.setId("addbtn");
		add.setPrefSize(Page.BTN_WIDTH, Page.BTN_HEIGHT);
		add.setText("+");
        add.setOnAction(e -> {
        	WeatherApp.currentlyViewingTrip = null;
        	WeatherApp.changePage("edittrip");
        });
        scrollContent.getChildren().add(add);
		
		scrollPane.setContent(scrollContent);
        mainContentGrid.getChildren().add(scrollPane);
	}

	@Override
	void leftButtonAction() {
		WeatherApp.changePage("overview");
	}

	@Override
	void rightButtonAction() {
		// Do nothing
	}

}
