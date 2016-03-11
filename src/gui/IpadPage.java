package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class IpadPage {
	private Scene pageScene;
	private VBox pageContentandNav;
	private HBox pageContent;
	protected GridPane leftContentGrid;
	protected GridPane rightContentGrid;
	private String name;
	protected Button lButton;
	protected Button rButton;
	protected String displayName;
	private HBox navBar;
	
	protected static final int BTN_WIDTH = 512;
	protected static final int BTN_HEIGHT = 175;

	public IpadPage(String name, String displayName, String leftButtonText, String rightButtonText) {
		this.name = name;
		this.displayName = displayName;
		
		// Set up the grids and layout managers
		pageContentandNav = new VBox();
		pageContent = new HBox();
		pageContent.setAlignment(Pos.CENTER);
		leftContentGrid = new GridPane();
		leftContentGrid.setPrefSize(512, 700);
		rightContentGrid = new GridPane();
		rightContentGrid.setPrefSize(512, 700);
		pageContent.getChildren().addAll(leftContentGrid, rightContentGrid);
		navBar = new HBox();
		navBar.setPrefSize(1024, 68);
		
		// Create the main content
		createContent();
		
		// Create the nav bar
		lButton = new Button(leftButtonText);
		lButton.setPrefSize(512, 68);
		lButton.setId("navButton");
		lButton.setBorder(new Border(new BorderStroke(
				Color.web("#7d7d7d"), 
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(0, 1, 0, 0))));
		lButton.setOnAction(e -> leftButtonAction());
		
		rButton = new Button(rightButtonText);
		rButton.setPrefSize(512, 68);
		rButton.setId("navButton");
		rButton.setBorder(new Border(new BorderStroke(
				Color.web("#7d7d7d"), 
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(0, 0, 0, 1))));
		rButton.setOnAction(e -> rightButtonAction());
		
		navBar.getChildren().addAll(lButton, rButton);
		
		// Add main content and navbar to the scene and generate the scene
		pageContentandNav.getChildren().addAll(pageContent, navBar);
		
		pageScene = new Scene(pageContentandNav, 1024, 768);
		pageScene.getStylesheets().add(getClass().getResource("/ipad.css").toExternalForm());
	}

	public String getName() {
		return name;
	}

	public Scene getScene() {
		return pageScene;
	}
	
	abstract void leftButtonAction();
	
	abstract void rightButtonAction();

	abstract void createContent();

	public void refreshPage() {
		createContent();
	};
}
