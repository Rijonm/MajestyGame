package View;

import Model.ClientModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView{
	
	public Stage primaryStage;
	public ClientModel model;
	public TextField usernameTf = new TextField("username");
	public TextField passwordTf = new TextField("password");
	public Scene firstScene; //loginScene ---
	public Button loginB = new Button ("Login");
	public Button registrierenB = new Button ("Regsitrieren");
	public Scene secondScene; // StartGameScene ---
	public Button startGame = new Button("Start Game");
	public Button backFirstSceneB = new Button ("Back");
	public Scene thirdScene; //GameScene
	

	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
	
	// HIER LOGIN-SCENE
		BorderPane loginPage = new BorderPane();
<<<<<<< HEAD
		VBox loginArea = new VBox();
		
		loginArea.getChildren().addAll(usernameTf, passwordTf, loginB, registrierenB);
		loginPage.setCenter(loginArea);
		
		firstScene = new Scene(loginPage);
		
		primaryStage.setScene(firstScene); // Login Area
		
=======
		Button spielStartB = new Button("Spiel starten");
		Button loginB = new Button ("Login");
		Button accountOptionsB = new Button("Registrieren/Löschen");
		Button einstellungenB = new Button("Einstellungen");
		Button beendenB = new Button("Beenden");
		VBox startOptionBox = new VBox();
>>>>>>> f9f239b8d9d200a7d8513f7329afcdfbd9cc4f49
		
	}
	
	// HIER SPIELSTARTEN-SCENE
	public Scene setSecondScene() {
		
		VBox startArea = new VBox();
		
		startArea.getChildren().addAll(startGame, backFirstSceneB);
		
		secondScene = new Scene(startArea);
		return secondScene;
		
	}
	
	// HIER SPIEL-DESIGN
	public Scene setThirdScene() {
		
		
		
		return thirdScene;
	}

	public void start() {
		
		primaryStage.show();
		
	}

}
