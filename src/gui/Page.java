package gui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public abstract class Page {
	protected Scene pageScene;
	protected GridPane pageGrid;
	protected String name;
	protected String displayName;
	
	public Page() {
		pageGrid = new GridPane();
        
		createContent();
		
        pageScene = new Scene(pageGrid, 320, 480);
        pageScene.getStylesheets().add(
        		getClass().getResource("/home.css").toExternalForm());
	}
	
	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public Scene getScene() {
		return pageScene;
	}
	
	abstract void createContent();
	abstract void refreshPage();
}
