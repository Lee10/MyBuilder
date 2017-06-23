package com.lee.builder.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by lee on 2017/6/14.
 */
public class MainWin implements Initializable{
	@FXML
	private ComboBox databaseComboBox;
	@FXML
	private ComboBox tableComboBox;
	@FXML
	private VBox vBox;
	@FXML
	private Button add;

	/**
	 * 打开新增数据源界面
	 * @param event
	 * @throws IOException
	 */
	@FXML
	//这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
	private void handleButtonAction(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add_database.fxml"));
		primaryStage.setTitle("新增数据源");

		primaryStage.setScene(new Scene(root, 630, 300));
		primaryStage.show();
	}

	/**
	 * 生成数据源下拉选
	 */
	@FXML
	private void showDatabase() {

		ObservableList<String> options = FXCollections.observableArrayList();
		options.addAll("mysql://172.16.7.4:3306/fswk",
				"oracle://172.16.7.4:3306/fswk",
				"sqlite://172.16.7.4:3306/fswk");
		databaseComboBox.setItems(options);
		//databaseComboBox.getSelectionModel().select(0);
	}

	/**
	 * 生成表下拉选
	 */
	@FXML
	private void showTable(Object newValue){
		List<String> tableNames = null;
		if (newValue.equals("mysql://172.16.7.4:3306/fswk")){
			tableNames = new ArrayList<String>();
			tableNames.add("table1");
			tableNames.add("table2");
		}else {
			tableNames = new ArrayList<String>();
			tableNames.add("table1");
			tableNames.add("table2");
			tableNames.add("table3");
			tableNames.add("table4");
		}

		ObservableList<String> options = FXCollections.observableArrayList();
		options.addAll(tableNames);
		tableComboBox.setItems(options);
		//tableComboBox.getSelectionModel().select(0);
	}

	/**
	 * 生成列名
	 * @param newValue
	 */
	@FXML
	private void showColumn(Object newValue){
		if (vBox.getChildren().size() != 0) vBox.getChildren().clear();
		int count=0;
		if (newValue.equals("table1"))count=4;
		else count=5;
		for (int i = 1; i < count; i++) {
			CheckBox column = new CheckBox("column"+i);
			vBox.getChildren().add(column);
		}

	}

	/**
	 * 选中数据源后显示其中的表明
	 */
	@FXML
	private void databaseSelectedAction(){
		showDatabase();
		databaseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				showTable(newValue);
			}
		});

	}

	/**
	 * 选中表后显示其中的列名
	 */
	@FXML
	private void tableSelectedAction(){
		tableComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				showColumn(newValue);
			}
		});

	}



	/**
	 * 初始化
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//String databaseurl = "mysql://172.16.7.4:3306/fswk";
		//String table ="table1";
		//showDatabase();
		//showTable(databaseurl);
		//showColumn(table);
	}
}
