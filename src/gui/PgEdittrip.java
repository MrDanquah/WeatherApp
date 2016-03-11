package gui;

import api.Trip;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PgEdittrip extends Page {
	
	private class EditTripPane {
		public VBox inputPane;
		public CheckBox repeat;
		EditTripPane(Trip trip) {
			inputPane = new VBox();
			inputPane.setAlignment(Pos.TOP_CENTER);
			inputPane.setSpacing(4);
			inputPane.setPrefSize(320, 432);
			mainContentGrid.setId("tripPane");
			Text title = new Text("Edit Journey");
			title.setId("title");
			repeat = new CheckBox("Repeat");
			repeat.setText("Repeat");
			repeat.setTextAlignment(TextAlignment.LEFT);
			repeat.setSelected(false);

			title.setTextAlignment(TextAlignment.CENTER);

			TextField startingPoint = new TextField(trip.getStart() != null ? trip.getStart() : "");
			startingPoint.setMaxWidth(250);
			startingPoint.setId("textField");
			startingPoint.setPromptText("Starting Point");

			TextField dest = new TextField(trip.getDest() != null ? trip.getDest() : "");
			dest.setMaxWidth(250);
			dest.setId("textField");
			dest.setPromptText("Destination");

			TextField arrivalTime = new TextField(trip.getArriveTime() != null ? trip.getArriveTime() : "");
			arrivalTime.setMaxWidth(250);
			arrivalTime.setId("textField");
			arrivalTime.setPromptText("Arrival Time");

			TextField commuteTime = new TextField(trip.getLeaveTime() != null ? trip.getLeaveTime() : "");
			commuteTime.setMaxWidth(250);
			// Need to calculate difference for commute time display
			commuteTime.setId("textField");
			commuteTime.setPromptText("Commute Time");
			

			inputPane.getChildren().addAll(title, startingPoint, dest, arrivalTime, commuteTime, repeat);
			if(repeat.isSelected()) {
				//
			}else {
				//
			}
			/*repeat.selectedProperty().addListener(new ChangeListener<Boolean>() {
		           public void changed(ObservableValue<? extends Boolean> ov,
		             Boolean old_val, Boolean new_val) {
		             System.out.println(repeat.isSelected());
		          }
		        }); */

		}

		public VBox getPane() {
			return inputPane;
		}
		
	}

	public PgEdittrip() {
		super("edittrip", "Edit Trip", "Back", "Save");
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

}
