package com.lee.builder.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by lee on 2017/6/14.
 */
public class MainWin {
	@FXML private Text actiontarget;
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
		actiontarget.setText("Sign in button pressed");
	}
}
