package com.lee.builder.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by lzw on 2017/6/21.
 */
public class AddDatabase {
	@FXML
	private ComboBox chooseDatabase;
	@FXML
	private TextField firstNameField;

	@FXML
	private void showDatabase(){

		ObservableList<String> options = FXCollections.observableArrayList();
		chooseDatabase.setItems(options);
		options.addAll("mysql","oracle","sqlite");
		//chooseDatabase.getSelectionModel().select(0);
	}
}
