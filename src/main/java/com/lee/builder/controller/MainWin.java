package com.lee.builder.controller;

import com.lee.builder.model.Column;
import com.lee.builder.model.Database;
import com.lee.builder.model.Table;
import com.lee.builder.service.IDatabaseService;
import com.lee.builder.service.IGengerateService;
import com.lee.builder.service.impl.DatabaseServiceImpl;
import com.lee.builder.service.impl.GenerateServiceImpl;
import com.lee.builder.utils.DBUtils;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

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
	public void addDatabase(ActionEvent event) throws IOException {
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
	private List<Database> listDatabase(Database db) {
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
		List<Database> list = listDatabase(new Database());
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
		selectedDB = new Database();
		selectedDB.setUrl(String.valueOf(newValue));
		List<Database> dbList = listDatabase(selectedDB);

		List<String> tableNames = null;
		if (CollectionUtils.isNotEmpty(dbList)) {
			selectedDB = dbList.get(0);
			tableNames = listTableNames(selectedDB);
		}
		ObservableList<String> options = FXCollections.observableArrayList();
		options.addAll(tableNames);
		tableComboBox.setItems(options);
	}

	/**
	 * 查询用户选择的数据源地址下的所有表名
	 *
	 * @return
	 */
	private List<String> listTableNames(Database db) {
		IDatabaseService databaseService = new DatabaseServiceImpl();
		List<String> tableNames = databaseService.getTableNameList(db);
		return tableNames;
	}

	@FXML
	private TableView tableView;
	@FXML
	private TableColumn cb;
	@FXML
	private TableColumn columnComment;

	@FXML
	private CheckBox mapper;
	@FXML
	private CheckBox model;
	@FXML
	private CheckBox dao;
	@FXML
	private CheckBox service;
	@FXML
	private CheckBox controller;

	/**
	 * 选中的数据源
	 **/
	private Database selectedDB;
	/**
	 * 选中的表
	 **/
	private Table selectedTable;
	/**
	 * 选中的列
	 **/
	private List<Column> selectedColumns;

	/**
	 * 生成文件
	 *
	 * @param event
	 */
	@FXML
	public void create(ActionEvent event) {
		System.out.println("生成文件");
		ObservableList<Column> list = tableView.getItems();
		selectedColumns = new ArrayList<Column>();
		for (Column o : list) {
			if (o.getCb().isSelected()) {
				System.out.println(o.getColumnName() + "--" + o.getColumnComment());
				selectedColumns.add(o);
			}
		}
		System.out.println(selectedColumns.size());
		selectedTable.setColumns(selectedColumns);
		if (mapper.isSelected()){
			IDatabaseService databaseService = new DatabaseServiceImpl();
			IGengerateService gengerateService = new GenerateServiceImpl();
			String className = capFirstColumnName(selectedTable.getTableName());
			System.out.println(className);
			boolean flag = gengerateService.generateModelClass("ModelTemplete.java", "com.lee.coderepo.model;", "C:\\Users\\lzw\\Desktop\\MyBuilder\\"+className+".java", selectedTable);
			System.out.println(flag);
		}
		if (model.isSelected()){
			System.out.println("model is selected");
		}
		if (dao.isSelected()){
			System.out.println("dao is selected");
		}
		if (service.isSelected()){
			System.out.println("service is selected");
		}
		if (controller.isSelected()){
			System.out.println("controller is selected");
		}
	}

	private String capFirstColumnName(String columnName){

		String[] arr = StringUtils.split(columnName, "_");
		if(arr != null && arr.length > 0) {
			StringBuilder strBuilder = new StringBuilder(StringUtils.capitalize(arr[0]));
			for (int i = 1; i < arr.length; i++) {
				strBuilder.append(StringUtils.capitalize(arr[i]));
			}
			return strBuilder.toString();
		}
		return columnName;
	}

	/**
	 * 生成列名
	 *
	 * @param newValue
	 */
	private void showColumnByTableName(String tableName) {
		if (vBox.getChildren().size() != 0) vBox.getChildren().clear();
		Table table = listTable(tableName, selectedDB);
		List<Column> columns = table.getColumns();

		ObservableList<Column> options = FXCollections.observableArrayList();
		options.addAll(columns);

		cb.setCellValueFactory(new PropertyValueFactory<>("cb"));
		columnComment.setCellValueFactory(new PropertyValueFactory<>("columnComment"));
		tableView.setEditable(true);
		tableView.setItems(options);
		vBox.getChildren().addAll(tableView);
	}


	private Table listTable(String tableName, Database db) {
		IDatabaseService databaseService = new DatabaseServiceImpl();
		Table table = databaseService.getTableByName(tableName, db);
		return table;
	}


	/**
	 * 选中数据源后显示其中的表明
	 */
	@FXML
	public void databaseSelectedAction(ActionEvent event) {
		//以后要删除掉
		//showDatabase();

		//databaseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		//	@Override
		//	public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		//		System.out.println("成功选中");
		//		System.out.println("selected--->" + selected);
		//		System.out.println("oldValue--->" + oldValue);
		//		System.out.println("newValue--->" + newValue);
		//		if (oldValue != null) {
		//
		//		}
		//	}
		//});

		databaseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				System.out.println("选中数据源");
				System.out.println("newValue--->" + newValue);
				if (newValue != null) showTableByUrl(newValue);
			}
		});

	}

	/**
	 * 选中表后显示其中的列名
	 */
	@FXML
	public void tableSelectedAction() {
		tableComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				System.out.println("选中table");
				System.out.println("newValue--->" + newValue);
				if (newValue != null) {
					showColumnByTableName(newValue);
					selectedTable = new Table(newValue, newValue, new ArrayList<>());
				}

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
