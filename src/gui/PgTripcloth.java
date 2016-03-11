package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PgTripcloth extends Page {

	private class ClothingPane {
		GridPane clothGrid;
		VBox infoPane;

		public ClothingPane() {
			infoPane = new VBox();
			infoPane.setPrefSize(320, 432);
			infoPane.setAlignment(Pos.CENTER);
			clothGrid = new GridPane();
			clothGrid.setAlignment(Pos.CENTER);

			clothGrid.setGridLinesVisible(false);
			
			mainContentGrid.setStyle("-fx-background-color: #fbaf5d");
			
			Label clothSuggs = new Label("Clothing Suggestions");
			Label weatherDescriptions = new Label("There will be rain, sun and wind");
			
			clothSuggs.setId("clothing");
			
			clothSuggs.setAlignment(Pos.CENTER);
			
			ImageView suggOne = new ImageView("umbrella.png");
			suggOne.setFitWidth(156);
			suggOne.setFitHeight(150);
			clothGrid.add(suggOne, 0, 1);
			ImageView suggTwo = new ImageView("Clothes_gloves-36-512.png");
			suggTwo.setFitWidth(156);
			suggTwo.setFitHeight(150);
			clothGrid.add(suggTwo, 1, 1);
			ImageView suggThree = new ImageView("jacket-512.png");
			suggThree.setFitWidth(156);
			suggThree.setFitHeight(150);
			clothGrid.add(suggThree, 0, 2);
			ImageView suggFour = new ImageView("Sunglasses-512.png");
			suggFour.setFitWidth(156);
			suggFour.setFitHeight(150);
			clothGrid.add(suggFour, 1, 2);
		
			infoPane.getChildren().addAll(clothSuggs,weatherDescriptions, clothGrid);
		}

		public VBox getPane() {
			return infoPane;
		}

	}

	public PgTripcloth() {
		super("clothsuggest", "clothing", "Back", "");
		// TODO Auto-generated constructor stub
	}

	@Override
	void leftButtonAction() {
		WeatherApp.changePage("triplist");

	}

	@Override
	void rightButtonAction() {
		// TODO Auto-generated method stub

	}

	@Override
	void createContent() {
		ClothingPane myCloths = new ClothingPane();
		mainContentGrid.getChildren().add(myCloths.getPane());
	}

}
