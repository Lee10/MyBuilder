package com.lee.builder.controller;

import com.lee.builder.model.Database;
import com.lee.builder.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Created by lzw on 2017/6/21.
 */
public class AddDatabase implements Initializable {
	@FXML
	private ComboBox databaseTypeComboBox;
	@FXML
	private Button connTest;
	@FXML
	private Button save;
	@FXML
	private TextField host;
	@FXML
	private TextField post;
	@FXML
	private TextField sid;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextArea log;
	/**
	 * 测试连接是否成功
	 */
	private boolean flag;

	/**
	 * 显示下拉选数据库类型
	 */
	@FXML
	private void showDatabase() {

		ObservableList<String> options = FXCollections.observableArrayList();
		databaseTypeComboBox.setItems(options);
		options.addAll("mysql", "oracle", "sqlite");
	}

	/**
	 * 测试连接数据库
	 */
	@FXML
	private void connTestButtonAction() {
		log.clear();
		flag = false;
		Connection conn = null;
		try {
			conn = DBUtils.getConnecttion(getDatabase());
			flag = true;
			log.appendText("测试连接成功\n");
		} catch (Exception e) {
			log.appendText("测试连接失败\n");
			log.appendText(e.toString());
		}

	}

	/**
	 * 保存新增数据库信息
	 */
	@FXML
	private void saveButtontAction(ActionEvent event) {
		log.clear();
		if (!flag) {
			log.appendText("测试连接失败,无法保存");
			return;
		}
		Database sqlite = new Database();
		sqlite.setType(DBUtils.DB_TYPE_SQLITE);
		sqlite.setSid(AddDatabase.class.getClassLoader().getResource("conf.db").getPath());
		Database db = getDatabase();
		String sql = "insert or replace into database(ip,port,sid,type,username,password) values(?,?,?,?,?,?)";
		Object[] params = {db.getIp(), db.getPort(), db.getSid(), db.getType(), db.getUsername(), db.getPassword()};

		try {
			int count = DBUtils.update(sqlite, sql, params);
			System.out.println(count > 0 ? "入库成功" : "入库失败");
			if (count>0){
				Stage stage = (Stage) MainWin.getWindow(event);
				stage.close();

			}else {
				log.appendText("入库失败");
			}
		} catch (Exception e) {
			log.appendText(e.toString());
		}
	}

	/**
	 * 获取新增数据源页面输入值
	 *
	 * @return
	 */
	private Database getDatabase() {
		Database database = new Database();
		database.setType((String) databaseTypeComboBox.getSelectionModel().getSelectedItem());
		database.setIp(host.getText());
		database.setPort(StringUtils.isEmpty(post.getText()) ? 0 : Integer.valueOf(post.getText()));
		database.setSid(sid.getText());
		database.setUsername(username.getText());
		database.setPassword(password.getText());
		return database;
	}

	/**
	 * 初始化
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
