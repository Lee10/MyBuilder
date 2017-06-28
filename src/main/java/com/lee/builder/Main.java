package com.lee.builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by lee on 2017/6/14.
 */
public class Main extends Application{
	
	public void start(Stage primaryStage) throws Exception {
		// 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_win.fxml"));
		primaryStage.setTitle("MyBuilder");
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		//固定窗口大小，不可改变
		//primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
