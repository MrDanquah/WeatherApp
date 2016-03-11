package gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import api.Trip;
import api.YWeatherConnection;

public class IpadPgTripplanner extends IpadPage{
	private ScrollPane scrollPane;
	private VBox scrollContent;
	
	TextField startingPoint;
	TextField arrivalTime;
	TextField dest;
	TextField commuteTime;
	boolean repeat[] = {false, false, false, false, false, false, false};
	
	private boolean isEditing = false;
	
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
			infoPane.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
			infoPane.setStyle("-fx-background-color: " + WeatherApp.
	        		colorMap[idx%7]);
			
			// Trip start and dest names
			HBox tripNames = new HBox();
			tripNames.setAlignment(Pos.TOP_CENTER);
			
			Label startName = new Label(trip.getStart());
			startName.getStyleClass().add("tripplannername");
			startName.setPrefWidth(240);
			startName.setAlignment(Pos.CENTER_LEFT);
			tripNames.getChildren().add(startName);
			
			// Arrow
			ImageView arrow = new ImageView("arrow_forward.png");
			arrow.setFitWidth(32);
			arrow.setPreserveRatio(true);
			arrow.setCache(true);
			tripNames.getChildren().add(arrow);
			
			Label destName = new Label(trip.getDest());
			destName.getStyleClass().add("tripplannername");
			destName.setPrefWidth(240);
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
			delete.setFitWidth(32);
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
				isEditing = true;
	        	WeatherApp.changePage("tripplanner");
	        });
		}
		
		public VBox getPane() {
			return infoPane;
		}
	}
	
	private class EditTripPane {
		public VBox inputPane;
		// boolean isClicked = false;

		public EditTripPane(Trip trip) {
			inputPane = new VBox();
			inputPane.setAlignment(Pos.TOP_CENTER);
			inputPane.setSpacing(4);
			inputPane.setPrefSize(480, 432);
			
			Label title = new Label("Edit Trip");
			title.setId("edittriptitle");
			title.setTextAlignment(TextAlignment.CENTER);
			
			// Start
			HBox inputBox1 = new HBox();
			inputBox1.setPrefSize(480, 48);
			inputBox1.setAlignment(Pos.CENTER);
			
			ImageView start = new ImageView("start.png");
			start.setFitWidth(48);
			start.setPreserveRatio(true);
			start.setCache(true);

			startingPoint = new TextField(trip != null ? trip.getStart() : "");
			startingPoint.setPrefWidth(432);
			startingPoint.setId("textField");
			startingPoint.setPromptText("Starting Point");

			inputBox1.getChildren().addAll(start, startingPoint);

			// Destination
			HBox inputBox2 = new HBox();
			inputBox2.setPrefSize(480, 48);
			inputBox2.setAlignment(Pos.CENTER);
			
			ImageView destination = new ImageView("dest.png");
			destination.setFitWidth(48);
			destination.setPreserveRatio(true);
			destination.setCache(true);

			dest = new TextField(trip != null ? trip.getDest() : "");
			dest.setPrefWidth(432);
			dest.setId("textField");
			dest.setPromptText("Destination");

			inputBox2.getChildren().addAll(destination, dest);

			// Arrival time
			HBox inputBox3 = new HBox();
			inputBox3.setPrefSize(480, 48);
			inputBox3.setAlignment(Pos.CENTER);
			
			ImageView arrive = new ImageView("arrive.png");
			arrive.setFitWidth(48);
			arrive.setPreserveRatio(true);
			arrive.setCache(true);

			arrivalTime = new TextField(trip != null ? trip.getArriveTime() : "");
			arrivalTime.setPrefWidth(432);
			arrivalTime.setId("textField");
			arrivalTime.setPromptText("Arrival Time");

			inputBox3.getChildren().addAll(arrive, arrivalTime);
				
			// Commute time
			HBox inputBox4 = new HBox();
			inputBox4.setPrefSize(480, 48);
			inputBox4.setAlignment(Pos.CENTER);
			
			ImageView commute = new ImageView("commute.png");
			commute.setFitWidth(48);
			commute.setPreserveRatio(true);
			commute.setCache(true);

			commuteTime = new TextField(trip != null ? trip.getLeaveTime() : "");
			commuteTime.setPrefWidth(432);
			// Need to calculate difference for commute time display
			commuteTime.setId("textField");
			commuteTime.setPromptText("Commute Time");

			inputBox4.getChildren().addAll(commute, commuteTime);

			// Repeat buttons
			HBox daysButtons = new HBox();
			daysButtons.setPrefWidth(Page.BTN_WIDTH);
			daysButtons.setAlignment(Pos.CENTER);
			daysButtons.setSpacing(4);

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
	
	public IpadPgTripplanner() {
		super("tripplanner", "Trip Planner", "Overview", "Trip Planner");
	}

	@Override
	void createContent() {
		// Set up the scroll pane
		scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT * 4);
		
		// Set up and create the content in the scroll pane
		scrollContent = new VBox();
		scrollContent.setPrefWidth(IpadPage.BTN_WIDTH);
		
		int idx = 0;
		for(Trip trip : WeatherApp.trips) {
			scrollContent.getChildren().add(
	        		(new TripplannerPane(trip, idx)).getPane());
			idx++;
		}
		
		Button add = new Button();
		add.setId("addbtn");
		add.setPrefSize(IpadPage.BTN_WIDTH, IpadPage.BTN_HEIGHT);
		add.setText("+");
        add.setOnAction(e -> {
        	WeatherApp.currentlyViewingTrip = null;
        	isEditing = true;
        	WeatherApp.changePage("tripplanner");
        });
        scrollContent.getChildren().add(add);
		
		scrollPane.setContent(scrollContent);
        leftContentGrid.getChildren().add(scrollPane);
        
        rightContentGrid.setId("edittrippane");
        if(isEditing) {
        	EditTripPane start = new EditTripPane(WeatherApp.currentlyViewingTrip);
            rightContentGrid.getChildren().add(start.getPane());
            lButton.setText("Back");
        	rButton.setText("Save");
        } else {
        	rightContentGrid.getChildren().clear();
        	lButton.setText("Overview");
        	rButton.setText("");
        }
	}

	@Override
	void leftButtonAction() {
		if(isEditing) {
			isEditing = false;
			WeatherApp.changePage("tripplanner");
		} else {
			WeatherApp.changePage("overview");
		}
	}

	@Override
	void rightButtonAction() {
		if(isEditing) {
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
	}

}
