package gui;

import api.Trip;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
			HBox inputBox1 = new HBox();
			inputBox1.setPrefSize(30, 30);
			//Image locationImg = new Image(getClass().getResourceAsStream("ic_my_location_white_48dp.png"));
			//Throws an error when location ICON
			Label myLoc = new Label("Image");
			
			
			
			mainContentGrid.setId("tripPane");
			Text title = new Text("Edit Journey");
			title.setId("title");
			repeat = new CheckBox("Repeat");
			repeat.setText("Repeat");
			repeat.setSelected(false);

			title.setTextAlignment(TextAlignment.CENTER);

			TextField startingPoint = new TextField(trip.getStart() != null ? trip.getStart() : "");
			startingPoint.setMaxWidth(250);
			startingPoint.setId("textField");
			startingPoint.setPromptText("Starting Point");
			
			inputBox1.getChildren().addAll(myLoc, startingPoint);
			
			HBox inputBox2 = new HBox();
			inputBox1.setPrefSize(30, 30);
			Label icon2 = new Label("Image");
			
			TextField dest = new TextField(trip.getDest() != null ? trip.getDest() : "");
			dest.setMaxWidth(250);
			dest.setId("textField");
			dest.setPromptText("Destination");
			
			inputBox2.getChildren().addAll(icon2, dest);

			HBox inputBox3 = new HBox();
			inputBox1.setPrefSize(30, 30);
			Label icon3 = new Label("Image");
			
			TextField arrivalTime = new TextField(trip.getArriveTime() != null ? trip.getArriveTime() : "");
			arrivalTime.setMaxWidth(250);
			arrivalTime.setId("textField");
			arrivalTime.setPromptText("Arrival Time");
			
			inputBox3.getChildren().addAll(icon3, arrivalTime);
			
			HBox inputBox4 = new HBox();
			inputBox1.setPrefSize(30, 30);
			Label icon4 = new Label("Image");
		

			TextField commuteTime = new TextField(trip.getLeaveTime() != null ? trip.getLeaveTime() : "");
			commuteTime.setMaxWidth(250);
			// Need to calculate difference for commute time display
			commuteTime.setId("textField");
			commuteTime.setPromptText("Commute Time");
			
			inputBox4.getChildren().addAll(icon4, commuteTime );
			
			HBox daysButtons = new HBox();
			
			
			Button sunday = new Button("S");
			sunday.setId("daysButtons");
			
			Button monday = new Button("M");
			monday.setId("daysButtons");
			
			Button tuesday = new Button("T");
			tuesday.setId("daysButtons");
			
			Button wednesday = new Button("W");
			wednesday.setId("daysButtons");
			
			Button thursday = new Button("S");
			thursday.setId("daysButtons");
			
			Button friday = new Button("S");
			friday.setId("daysButtons");
			
			Button saturday = new Button("S");
			saturday.setId("daysButtons");
			
			daysButtons.getChildren().addAll(sunday,monday,tuesday,wednesday,thursday,friday,saturday);
			
			saturday.setOnAction(e-> {
				saturday.setStyle("-fx-background-color: #ce0000");
			});
			
			friday.setOnAction(e-> {
				friday.setStyle("-fx-background-color: #ce0000");
			});
			
			thursday.setOnAction(e-> {
				thursday.setStyle("-fx-background-color: #ce0000");
			});
			
			wednesday.setOnAction(e-> {
				wednesday.setStyle("-fx-background-color: #ce0000");
			});
			
			tuesday.setOnAction(e-> {
				tuesday.setStyle("-fx-background-color: #ce0000");
			});
			
			monday.setOnAction(e-> {
				monday.setStyle("-fx-background-color: #ce0000");
			});
			
			sunday.setOnAction(e-> {
				sunday.setStyle("-fx-background-color: #ce0000");
			});
			
			

			inputPane.getChildren().addAll(title,inputBox1, inputBox2, inputBox3, inputBox4, repeat, daysButtons);
			if (repeat.isSelected()) {
				//
			} else {
				//
			}
			/*
			 * repeat.selectedProperty().addListener(new
			 * ChangeListener<Boolean>() { public void changed(ObservableValue<?
			 * extends Boolean> ov, Boolean old_val, Boolean new_val) {
			 * System.out.println(repeat.isSelected()); } });
			 */

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
