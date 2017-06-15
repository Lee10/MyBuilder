package com.lee.builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by lee on 2017/6/14.
 */
public class Main extends Application{
	
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_win.fxml"));
		primaryStage.setTitle("MyBuilder");
		primaryStage.setScene(new Scene(root, 350, 275));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
