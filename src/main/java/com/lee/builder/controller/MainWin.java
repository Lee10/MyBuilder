package com.lee.builder.controller;

import com.lee.builder.model.Database;
import com.lee.builder.service.IDatabaseService;
import com.lee.builder.service.impl.DatabaseServiceImpl;
import com.lee.builder.utils.DBUtils;
import com.lee.builder.utils.JsonUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by lee on 2017/6/14.
 */
public class MainWin implements Initializable {
	@FXML
	private ComboBox<String> databaseComboBox;
	@FXML
	private ComboBox tableComboBox;
	@FXML
	private VBox vBox;
	@FXML
	private Button add;

	/**
	 * 打开新增数据源界面
	 * 按钮事件：启动子窗口
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	//这里的addDatabase方法为我们在FXML文件中声明的onAction的处理函数
	private void addDatabase(ActionEvent event) throws IOException {
		Stage stage = (Stage) getWindow(event);
		startSub(stage);
	}

	/**
	 * 启动子窗口
	 *
	 * @throws IOException
	 */
	private void startSub(Window owner) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add_database.fxml"));
		Scene scene = new Scene(root, 400, 350);
		stage.setScene(scene);
		stage.setTitle("新增数据源");

		stage.initModality(Modality.WINDOW_MODAL);
		//设置父窗口
		stage.initOwner(owner);
		stage.show();
	}

	/**
	 * 获取控件元素的窗口对象
	 *
	 * @param event
	 * @return
	 */
	public static Window getWindow(ActionEvent event) {
		return ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * 获取menuItem控件的窗口对象
	 *
	 * @param event
	 * @return
	 */
	public static Window getWindowFromMenu(ActionEvent event) {
		MenuItem menuItem = ((MenuItem) event.getSource());
		//转换成ContextMenu
		ContextMenu cm = menuItem.getParentPopup();
		return cm.getOwnerWindow();
	}


	/**
	 * 生成数据源下拉选
	 */
	private void showDatabase() {
		List<String> strList = getDBUrl();
		ObservableList<String> options = FXCollections.observableArrayList();
		options.addAll(strList);
		databaseComboBox.setItems(options);
	}

	/**
	 * 查询本地sqlite库database
	 *
	 * @return
	 */
	private List<Database> selectDatabase(Database db) {
		Database sqlite = new Database();
		sqlite.setType(DBUtils.DB_TYPE_SQLITE);
		sqlite.setSid("E:\\work\\code\\MyBuilder\\src\\main\\resources\\conf.db");
		StringBuilder sql = new StringBuilder("select * from database where 1=1");

		List<Object> objList = new ArrayList<Object>();
		if (StringUtils.isNoneEmpty(db.getUrl())) {
			sql.append(" and url = ?");
			objList.add(db.getUrl());
		}

		Object[] params = objList.toArray();

		List<Database> list = new ArrayList<Database>();
		try {
			list = DBUtils.select(sqlite, sql.toString(), params, Database.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过List<Database>生成List<String>
	 *
	 * @param list
	 * @return
	 */
	private List<String> getDBUrl() {
		List<Database> list = selectDatabase(new Database());
		List<String> strList = new ArrayList<String>();
		if (CollectionUtils.isEmpty(list)) return strList;
		for (Database database : list) {
			strList.add(DBUtils.buildUrl(database));
		}
		return strList;
	}


	/**
	 * 生成表名下拉选
	 */
	private void showTableByUrl(Object newValue) {

		//通过选择的url查询sqlite数据库database的数据库信息
		Database db = new Database();
		db.setUrl(String.valueOf(newValue));
		List<Database> dbList = selectDatabase(db);
		System.out.println("dbList-->" + dbList.size());
		System.out.println(JsonUtils.toString(dbList));

		List<String> tableNames = null;
		if (CollectionUtils.isNotEmpty(dbList)) {
			tableNames = selectTable(dbList.get(0));
		}

		ObservableList<String> options = FXCollections.observableArrayList();
		options.addAll(tableNames);

		tableComboBox.setItems(options);
		//tableComboBox.getSelectionModel().select(0);
	}

	/**
	 * 查询用户选择的数据源地址下的所有表名
	 *
	 * @return
	 */
	private List<String> selectTable(Database db) {
		IDatabaseService databaseService = new DatabaseServiceImpl();
		List<String> tableNames = databaseService.getTableNameList(db);
		return tableNames;
	}

	/**
	 * 生成列名
	 *
	 * @param newValue
	 */
	@FXML
	private void showColumn(Object newValue) {
		if (vBox.getChildren().size() != 0) vBox.getChildren().clear();
		int count = 0;
		if (newValue.equals("table1")) count = 4;
		else count = 5;
		for (int i = 1; i < count; i++) {
			CheckBox column = new CheckBox("column" + i);
			vBox.getChildren().add(column);
		}
	}


	/**
	 * 选中数据源后显示其中的表明
	 */
	@FXML
	private void databaseSelectedAction(ActionEvent event) {
		//以后要删除掉
		//showDatabase();

		//databaseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		//	@Override
		//	public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
		//		System.out.println("成功选中");
		//		System.out.println("selected--->" + selected);
		//		System.out.println("oldFruit--->" + oldFruit);
		//		System.out.println("newFruit--->" + newFruit);
		//		if (oldFruit != null) {
		//
		//		}
		//	}
		//});

		databaseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				System.out.println("成功选中");
				System.out.println("newValue--->" + newValue);
				//if (newValue != null) showTableByUrl(newValue);
			}
		});

	}

	/**
	 * 选中表后显示其中的列名
	 */
	@FXML
	private void tableSelectedAction() {
		tableComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				showColumn(newValue);
			}
		});

	}


	/**
	 * 初始化
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showDatabase();
	}
}
