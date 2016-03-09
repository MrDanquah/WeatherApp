package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Page {
	private Scene pageScene;
	private VBox pageContentandNav;
	protected GridPane mainContentGrid;
	protected String name;
	protected Button lButton;
	protected Button rButton;
	protected String displayName;
	private HBox navBar;

	public Page() {
		pageContentandNav = new VBox();
		mainContentGrid = new GridPane();
		mainContentGrid.setPrefSize(320, 432);
		navBar = new HBox();
		navBar.setPrefSize(320, 48);

		DropShadow shadow = new DropShadow();

		createContent();
		
		addNavBar();
		rButton.setPrefSize(160, 48);
		lButton.setPrefSize(160, 48);
		
		rButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> rButton.setEffect(shadow));
		rButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> rButton.setEffect(null));

		lButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> lButton.setEffect(shadow));
		lButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> lButton.setEffect(null));
		
		navBar.getChildren().addAll(lButton, rButton);
		pageContentandNav.getChildren().add(mainContentGrid);
		pageContentandNav.getChildren().add(navBar);
		
		pageScene = new Scene(pageContentandNav, 320, 480);
		pageScene.getStylesheets().add(getClass().getResource("/home.css").toExternalForm());
	}

	public String getName() {
		return name;
	}

	public Scene getScene() {
		return pageScene;
	}

	abstract void addNavBar();

	abstract void createContent();

	abstract void refreshPage();
}
