package gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import api.Trip;
import api.YWeatherConnection;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PgEdittrip extends Page {
	TextField startingPoint;
	TextField arrivalTime;
	TextField dest;
	TextField commuteTime;
	boolean repeat[] = {false, false, false, false, false, false, false};

	private class EditTripPane {
		public VBox inputPane;
		// boolean isClicked = false;

		public EditTripPane(Trip trip) {
			inputPane = new VBox();
			inputPane.setAlignment(Pos.TOP_CENTER);
			inputPane.setSpacing(4);
			inputPane.setPrefSize(320, 432);
			
			mainContentGrid.setId("tripPane");
			Label title = new Label("Edit Trip");
			title.setId("edittriptitle");
			title.setTextAlignment(TextAlignment.CENTER);
			
			// Start
			HBox inputBox1 = new HBox();
			inputBox1.setPrefSize(300, 30);
			inputBox1.setAlignment(Pos.CENTER);
			
			ImageView start = new ImageView("start.png");
			start.setFitWidth(30);
			start.setPreserveRatio(true);
			start.setCache(true);

			startingPoint = new TextField(trip != null ? trip.getStart() : "");
			startingPoint.setPrefWidth(270);
			startingPoint.setId("textField");
			startingPoint.setPromptText("Starting Point");

			inputBox1.getChildren().addAll(start, startingPoint);

			// Destination
			HBox inputBox2 = new HBox();
			inputBox2.setPrefSize(300, 30);
			inputBox2.setAlignment(Pos.CENTER);
			
			ImageView destination = new ImageView("dest.png");
			destination.setFitWidth(30);
			destination.setPreserveRatio(true);
			destination.setCache(true);

			dest = new TextField(trip != null ? trip.getDest() : "");
			dest.setPrefWidth(270);
			dest.setId("textField");
			dest.setPromptText("Destination");

			inputBox2.getChildren().addAll(destination, dest);

			// Arrival time
			HBox inputBox3 = new HBox();
			inputBox3.setPrefSize(300, 30);
			inputBox3.setAlignment(Pos.CENTER);
			
			ImageView arrive = new ImageView("arrive.png");
			arrive.setFitWidth(30);
			arrive.setPreserveRatio(true);
			arrive.setCache(true);

			arrivalTime = new TextField(trip != null ? trip.getArriveTime() : "");
			arrivalTime.setPrefWidth(270);
			arrivalTime.setId("textField");
			arrivalTime.setPromptText("Arrival Time");

			inputBox3.getChildren().addAll(arrive, arrivalTime);
				
			// Commute time
			HBox inputBox4 = new HBox();
			inputBox4.setPrefSize(300, 30);
			inputBox4.setAlignment(Pos.CENTER);
			
			ImageView commute = new ImageView("commute.png");
			commute.setFitWidth(30);
			commute.setPreserveRatio(true);
			commute.setCache(true);

			commuteTime = new TextField(trip != null ? trip.getLeaveTime() : "");
			commuteTime.setPrefWidth(270);
			// Need to calculate difference for commute time display
			commuteTime.setId("textField");
			commuteTime.setPromptText("Commute Time");

			inputBox4.getChildren().addAll(commute, commuteTime);

			// Repeat buttons
			HBox daysButtons = new HBox();
			daysButtons.setPrefWidth(Page.BTN_WIDTH);
			daysButtons.setAlignment(Pos.CENTER);
			daysButtons.setSpacing(2);

			Button sunday = new Button("S");
			sunday.setId("daysButtons");
			sunday.setOnAction(e -> {
				if(repeat[0]) {
					repeat[0] = false;
					sunday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[0] = true;
					sunday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button monday = new Button("M");
			monday.setId("daysButtons");
			monday.setOnAction(e -> {
				if(repeat[1]) {
					repeat[1] = false;
					monday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[1] = true;
					monday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button tuesday = new Button("T");
			tuesday.setId("daysButtons");
			tuesday.setOnAction(e -> {
				if(repeat[2]) {
					repeat[2] = false;
					tuesday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[2] = true;
					tuesday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button wednesday = new Button("W");
			wednesday.setId("daysButtons");
			wednesday.setOnAction(e -> {
				if(repeat[3]) {
					repeat[3] = false;
					wednesday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[3] = true;
					wednesday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button thursday = new Button("T");
			thursday.setId("daysButtons");
			thursday.setOnAction(e -> {
				if(repeat[4]) {
					repeat[4] = false;
					thursday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[4] = true;
					thursday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button friday = new Button("F");
			friday.setId("daysButtons");
			friday.setOnAction(e -> {
				if(repeat[5]) {
					repeat[5] = false;
					friday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[5] = true;
					friday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			Button saturday = new Button("S");
			saturday.setId("daysButtons");
			saturday.setOnAction(e -> {
				if(repeat[6]) {
					repeat[6] = false;
					saturday.setStyle("-fx-background-color: rgba(0,0,0,0)");
				} else {
					repeat[6] = true;
					saturday.setStyle("-fx-background-color: #f26c4f");
				}
			});

			daysButtons.getChildren().addAll(sunday, monday, tuesday, wednesday, thursday, friday, saturday);

			inputPane.getChildren().addAll(title, inputBox1, inputBox2, inputBox3, inputBox4, daysButtons);
		}

		public VBox getPane() {
			return inputPane;
		}

	}

	public PgEdittrip() {
		super("edittrip", "Edit Trip", "Back", "Save");
	}

	@Override
	void leftButtonAction() {
		WeatherApp.changePage("tripplanner");
	}

	@Override
	void rightButtonAction() {
		Calendar arrTime = Calendar.getInstance();

		SimpleDateFormat sdfmt1 = new SimpleDateFormat("h:mm a");
		try {
			arrTime.setTime(sdfmt1.parse(arrivalTime.getText()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SimpleDateFormat sdfmt2 = new SimpleDateFormat("h:mm");
		Calendar commTime = Calendar.getInstance();
		try {
			commTime.setTime(sdfmt2.parse(commuteTime.getText()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int startwoeid = 44418;
		int destwoeid = 44418;
		
		try {
			startwoeid = YWeatherConnection.
					searchLocation(startingPoint.getText()).get(0).getWOEID();
			destwoeid = YWeatherConnection.
					searchLocation(dest.getText()).get(0).getWOEID();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(WeatherApp.currentlyViewingTrip == null) {
			Trip newtrip = new Trip(startingPoint.getText(), 
					startwoeid, dest.getText(), destwoeid, arrTime, commTime, repeat);
			WeatherApp.trips.add(newtrip);
		} else {
			WeatherApp.currentlyViewingTrip.setArriveTime(arrTime);
			WeatherApp.currentlyViewingTrip.setCommuteTime(commTime);
			WeatherApp.currentlyViewingTrip.setStart(startingPoint.getText());
			WeatherApp.currentlyViewingTrip.setDest(dest.getText());
			WeatherApp.currentlyViewingTrip.setRepeat(repeat);
			WeatherApp.currentlyViewingTrip.setStartWoeid(startwoeid);
			WeatherApp.currentlyViewingTrip.setDestWoeid(destwoeid);
		}
		
		WeatherApp.changePage("tripplanner");
	}

	@Override
	void createContent() {
		repeat = new boolean[]{false, false, false, false, false, false, false};
		EditTripPane start = new EditTripPane(WeatherApp.currentlyViewingTrip);

		GridPane.setColumnIndex(start.getPane(), 0);
		GridPane.setRowIndex(start.getPane(), 0);
		mainContentGrid.getChildren().add(start.getPane());
	}
}
