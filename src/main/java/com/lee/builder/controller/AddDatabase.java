package com.lee.builder.controller;

import com.lee.builder.model.Database;
import com.lee.builder.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
		Connection conn = null;
		try {
			conn = DBUtils.getConnecttion(getDatabase());
			System.out.println("连接成功");
		} catch (Exception e) {
			System.out.println("连接失败");
		}

	}

	/**
	 * 保存新增数据库信息
	 */
	@FXML
	private void saveButtontAction() {
		// 0 连接SQLite的JDBC
		String url="jdbc:sqlite://E:\\work\\code\\MyBuilder\\src\\main\\resources\\conf.db";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 1 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接失败");
		}


		Database db = getDatabase();
		String sql ="insert or replace into database(ip,port,sid,type,username,password) values(?,?,?,?,?,?)";
		Object[] params={db.getIp(),db.getPort(),db.getSid(),db.getType(),db.getUsername(),db.getPassword()};
		try {
			int count= new QueryRunner().update(conn, sql, params);
			System.out.println(count>0?"入库成功":"入库失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Database getDatabase(){
		Database database = new Database();
		database.setType((String) databaseTypeComboBox.getSelectionModel().getSelectedItem());
		database.setIp(host.getText());
		database.setPort(StringUtils.isEmpty(post.getText()) ? 0 : Integer.valueOf(post.getText()));
		database.setSid(sid.getText());
		database.setUsername(username.getText());
		database.setPassword(password.getText());
		return database;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
