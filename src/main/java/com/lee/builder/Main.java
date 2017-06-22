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
		//Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_win.fxml"));
		//primaryStage.setTitle("MyBuilder");

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add_database.fxml"));
		primaryStage.setTitle("新增数据源");

		primaryStage.setScene(new Scene(root, 630, 300));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	//public void start(Stage stage) {
	//	Scene scene = new Scene(new Group());
	//	stage.setTitle("Radio Button Sample");
	//	stage.setWidth(630);
	//	stage.setHeight(400);
	//
	//	final ToggleGroup group = new ToggleGroup();
	//
	//	RadioButton rb1 = new RadioButton("Home");
	//	rb1.setToggleGroup(group);
	//	rb1.setUserData("Home");
	//
	//	RadioButton rb2 = new RadioButton("Calendar");
	//	rb2.setToggleGroup(group);
	//	rb2.setUserData("Calendar");
	//
	//	RadioButton rb3 = new RadioButton("Contacts");
	//	rb3.setToggleGroup(group);
	//	rb3.setUserData("Contacts");
	//
	//	group.selectedToggleProperty().addListener(
	//			(ObservableValue<? extends Toggle> ov, Toggle old_toggle,
	//			 Toggle new_toggle) -> {
	//				if (group.getSelectedToggle() != null) {
	//					final Image image = new Image(
	//							getClass().getResourceAsStream(
	//									group.getSelectedToggle().getUserData().toString()
	//											+ ".jpg"
	//							)
	//					);
	//					//icon.setImage(image);
	//				}
	//			});
	//
	//	HBox hbox = new HBox();
	//	VBox vbox = new VBox();
	//
	//	vbox.getChildren().add(rb1);
	//	vbox.getChildren().add(rb2);
	//	vbox.getChildren().add(rb3);
	//	vbox.setSpacing(10);
	//
	//	hbox.getChildren().add(vbox);
	//	//hbox.getChildren().add(icon);
	//	hbox.setSpacing(50);
	//	hbox.setPadding(new Insets(20, 10, 10, 20));
	//
	//	((Group) scene.getRoot()).getChildren().add(hbox);
	//	stage.setScene(scene);
	//	stage.show();
	//}

	//public void start(Stage primaryStage) {
	//	primaryStage.setTitle("MyBuilder");
	//	Group root = new Group();
	//	Scene scene = new Scene(root, 300, 250, Color.WHITE);
	//
	//	Text t = new Text();
	//	t.setX(10.0);
	//	t.setY(50.0);
	//	t.setCache(true);//？
	//	t.setText("数据源");
	//	t.setFill(Color.RED);
	//	t.setFont(Font.font(null, FontWeight.BOLD, 30));
	//	ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
	//				"First", "Second", "Third")
	//		);
	//	cb.setTooltip(new Tooltip("Select the language"));
	//	cb.setLayoutX(100.0);
	//	cb.setLayoutY(50.0);
	//
	//
	//	root.getChildren().add(t);
	//	root.getChildren().add(cb);
	//	primaryStage.setScene(scene);
	//	primaryStage.show();
	//}

	//public void start(Stage stage) {
	//	VBox vbox = new VBox(20);
	//	Scene scene = new Scene(vbox, 400, 400);
	//	stage.setScene(scene);
	//
	//	DatePicker checkInDatePicker = new DatePicker();
	//
	//	vbox.getChildren().add(checkInDatePicker);
	//
	//	stage.show();
	//}

	//public void start(final Stage primaryStage) {
	//	Group root = new Group();
	//
	//	Button buttonLoad = new Button("Load");
	//	buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
	//		@Override
	//		public void handle(ActionEvent arg0) {
	//			FileChooser fileChooser = new FileChooser();
	//			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	//			fileChooser.getExtensionFilters().add(extFilter);
	//			File file = fileChooser.showOpenDialog(primaryStage);
	//			System.out.println(file);
	//		}
	//	});
	//	VBox vBox = VBoxBuilder.create()
	//			.children(buttonLoad)
	//			.build();
	//	root.getChildren().add(vBox);
	//	primaryStage.setScene(new Scene(root, 500, 400));
	//	primaryStage.show();
	//}

	//public void start(Stage primaryStage) {
	//	BorderPane root = new BorderPane();
	//	Scene scene = new Scene(root, 300, 250, Color.WHITE);
	//
	//	MenuBar menuBar = new MenuBar();
	//	menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	//	root.setTop(menuBar);
	//
	//	// File menu - new, save, exit
	//	Menu fileMenu = new Menu("File");
	//	MenuItem newMenuItem = new MenuItem("New");
	//	MenuItem saveMenuItem = new MenuItem("Save");
	//	MenuItem exitMenuItem = new MenuItem("Exit");
	//	exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	//
	//	fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
	//			new SeparatorMenuItem(), exitMenuItem);
	//
	//	Menu webMenu = new Menu("Web");
	//	CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
	//	htmlMenuItem.setSelected(true);
	//	webMenu.getItems().add(htmlMenuItem);
	//
	//	CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
	//	cssMenuItem.setSelected(true);
	//	webMenu.getItems().add(cssMenuItem);
	//
	//	Menu sqlMenu = new Menu("SQL");
	//	ToggleGroup tGroup = new ToggleGroup();
	//	RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
	//	mysqlItem.setToggleGroup(tGroup);
	//
	//	RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
	//	oracleItem.setToggleGroup(tGroup);
	//	oracleItem.setSelected(true);
	//
	//	sqlMenu.getItems().addAll(mysqlItem, oracleItem,
	//			new SeparatorMenuItem());
	//
	//	Menu tutorialManeu = new Menu("Tutorial");
	//	tutorialManeu.getItems().addAll(
	//			new CheckMenuItem("Java"),
	//			new CheckMenuItem("JavaFX"),
	//			new CheckMenuItem("Swing"));
	//
	//	sqlMenu.getItems().add(tutorialManeu);
	//
	//	menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
	//
	//	primaryStage.setScene(scene);
	//	primaryStage.show();
	//}

}
