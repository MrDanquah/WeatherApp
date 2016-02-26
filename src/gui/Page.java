package gui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public abstract class Page {
	protected Scene pageScene;
	protected GridPane pageGrid;
	protected String name;
	
	public Page() {
		pageGrid = new GridPane();
        
		createButtons();
		
        pageScene = new Scene(pageGrid, 320, 480);
        pageScene.getStylesheets().add(
        		getClass().getResource("/home.css").toExternalForm());
	}
	
	public String getName() {
		return name;
	}
	
	public Scene getScene() {
		return pageScene;
	}
	
	abstract void createButtons();
	abstract void refreshPage();
}
