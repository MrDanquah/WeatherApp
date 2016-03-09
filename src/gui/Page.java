package gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public abstract class Page {
	protected Scene pageScene;
	protected GridPane pageGrid;
	protected String name;
	protected Button lButton;
	protected Button rButton;
	protected String displayName;
	protected HBox navBar;

	public Page() {
		pageGrid = new GridPane();

		DropShadow shadow = new DropShadow();

		createContent();
		addNavBar();
		rButton.setPrefSize(320, 48);
		lButton.setPrefSize(320, 48);
		
		
		rButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> rButton.setEffect(shadow));
		rButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> rButton.setEffect(null));

		lButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> lButton.setEffect(shadow));
		lButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> lButton.setEffect(null));
		// rButton.setMaxSize(4);

		GridPane.setRowIndex(lButton, 4);
		GridPane.setColumnIndex(lButton, 0);

		GridPane.setRowIndex(rButton, 2);
		GridPane.setColumnIndex(rButton, 1);
		
		//navBar.getChildren().addAll(rButton, lButton);
		//GridPane.setValignment(navBar, VPos.BOTTOM);
		
		pageScene = new Scene(pageGrid, 320, 480);
		pageScene.getStylesheets().add(getClass().getResource("/home.css").toExternalForm());

		pageGrid.getChildren().addAll(rButton, lButton);
		// pageGrid.set
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
