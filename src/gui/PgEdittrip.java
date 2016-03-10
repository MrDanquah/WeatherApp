package gui;

import api.Trip;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PgEdittrip extends Page {

	private class EditTripPane {
		public VBox inputPane;

		EditTripPane(Trip trip) {
			inputPane = new VBox();
			inputPane.setAlignment(Pos.CENTER.TOP_CENTER);
			inputPane.setPrefSize(320, 432);

			Text title = new Text("Edit Journey");

			title.setTextAlignment(TextAlignment.CENTER);

			TextField startingPoint = new TextField(trip.getStart() != null ? "" : trip.getStart());

			startingPoint.setPromptText("Starting Point");

			TextField dest = new TextField(trip.getDest() != null ? "" : trip.getDest());

			dest.setPromptText("Destination");

			TextField arrivalTime = new TextField(trip.getArriveTime() != null ? "" : trip.getArriveTime());

			arrivalTime.setPromptText("Arrival Time");

			TextField commuteTime = new TextField(trip.getLeaveTime()!= null ? "" : trip.getLeaveTime());
			// Need to calculate difference for commute time display
			commuteTime.setPromptText("Commute Time");
			
			inputPane.getChildren().addAll(title,startingPoint, dest, arrivalTime, commuteTime);

		}
		
		public VBox getPane() {
			return inputPane;
		}
	}

	public PgEdittrip() {
		super("editTrip", "Add Trips", "Back", "Save");
		// TODO Auto-generated constructor stub
	}

	@Override
	void leftButtonAction() {
		WeatherApp.changePage("overview");

	}

	@Override
	void rightButtonAction() {
		rButton.setOnAction(e -> {

		});

	}

	@Override
	void createContent() {

		EditTripPane start = new EditTripPane(WeatherApp.currentlyViewingTrip);
		
		GridPane.setColumnIndex(start.getPane(), 0);
		GridPane.setRowIndex(start.getPane(), 0);
		mainContentGrid.getChildren().add(start.getPane());
		// TODO Auto-generated method stub

	}

	@Override
	void refreshPage() {
		// TODO Auto-generated method stub

	}

}
