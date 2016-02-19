package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WeatherApp extends Application{
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        primaryStage.setResizable(false); // Not resizable
        
        GridPane grid = new GridPane(); // Create the grid pane
        
        Button btn1 = new Button();
        btn1.setText("1");
        btn1.setPrefSize(320, 108);
        btn1.setId("btn1");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN1 pressed!");
            }
        });
        GridPane.setRowIndex(btn1, 1);
        GridPane.setColumnIndex(btn1, 1);
        grid.getChildren().add(btn1);
        
        Button btn2 = new Button();
        btn2.setText("A");
        btn2.setPrefSize(320, 108);
        btn2.setId("btn2");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN2 pressed!");
            }
        });
        GridPane.setRowIndex(btn2, 2);
        GridPane.setColumnIndex(btn2, 1);
        grid.getChildren().add(btn2);
        
        Button btn3 = new Button();
        btn3.setText("F");
        btn3.setPrefSize(320, 108);
        btn3.setId("btn3");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN3 pressed!");
            }
        });
        GridPane.setRowIndex(btn3, 3);
        GridPane.setColumnIndex(btn3, 1);
        grid.getChildren().add(btn3);
        
        Button btn4 = new Button();
        btn4.setText("W");
        btn4.setPrefSize(320, 108);
        btn4.setId("btn4");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BTN4 pressed!");
            }
        });
        GridPane.setRowIndex(btn4, 4);
        GridPane.setColumnIndex(btn4, 1);
        grid.getChildren().add(btn4);

        Scene scene = new Scene(grid, 320, 480);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
        		getClass().getResource("/home.css").toExternalForm());
        primaryStage.show();
    }
}
