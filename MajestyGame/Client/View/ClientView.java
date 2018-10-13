package View;

import Model.ClientModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView{
	
	public Stage primaryStage;
	public ClientModel model;
	
	public Scene firstScene; //loginScene ---
	public TextField usernameTf = new TextField("username");
	public TextField passwordTf = new TextField("password");
	public Button loginB = new Button ("Login");
	public Button registrierenB = new Button ("Regsitrieren");
	
	public Scene secondScene; // registrierenScene ---
	public VBox registrierenArea;
	public Button registrierenBB = new Button("Registrieren");
	public Button backFirstSceneB = new Button ("Back");
	
	public Scene thirdScene; //gamestartScene
	public HBox optionenBox;
	public BorderPane spielStartenArea;
	public Button spielstartenB = new Button("Spiel starten");
	public Button einstellungenB = new Button ("Einstellungen");
	public Button highscoreB = new Button ("Highscore");
	public Button logoutB = new Button ("Logout");
	public Button spielanleitungB = new Button ("Spielanleitung");
	
	public Scene fourthScene; //EinstellungenScene
	
	public Scene fifthScene; //GameScene
	private BorderPane gameArea;
	private Button b1, b2, b3, b4, b5, b6;
	HBox cardButtonsBox;
	

	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
	
	// LOGIN-SCENE
		BorderPane loginPage = new BorderPane();

		VBox loginArea = new VBox();
		
		loginArea.getChildren().addAll(usernameTf, passwordTf, loginB, registrierenB);
		loginPage.setCenter(loginArea);
		
		firstScene = new Scene(loginPage);
		
		primaryStage.setScene(firstScene); // Login Area
		

		Button spielStartB = new Button("Spiel starten");
		Button loginB = new Button ("Login");
		Button accountOptionsB = new Button("Registrieren/Loeschen");
		Button einstellungenB = new Button("Einstellungen");
		Button beendenB = new Button("Beenden");
		VBox startOptionBox = new VBox();		
	}
	
	
	// REGISTRIEREN-Scene
	/**
	 * setSecondScene represents the Register Scene
	 * 
	 * @Author Rijon Mushkolaj
	 */
	public Scene setSecondScene() {
		
		registrierenArea = new VBox();
		
		registrierenArea.getChildren().addAll(registrierenBB, backFirstSceneB);
		
		secondScene = new Scene(registrierenArea);
		return secondScene;
	
	}
		
	
	// SPIEL START-Scene
	/**
	 * setThirdScene represents the StartGameScene
	 * 
	 * @Author Rijon Mushkolaj
	 */
	public Scene setThirdScene() {
		
		spielStartenArea = new BorderPane();
		optionenBox = new HBox();
		optionenBox.getChildren().addAll(spielstartenB, einstellungenB, highscoreB, logoutB, spielanleitungB);
		spielStartenArea.setCenter(optionenBox);
		
		
		
		thirdScene = new Scene(spielStartenArea);
		return thirdScene;
	}
	
	// EINSTELLUNGEN-Scene
		/**
		 * setFourthScene represents the Preferences Scene
		 * 
		 * @Author Rijon Mushkolaj
		 */
	public Scene setFourthScene() {
		
		
		return fourthScene;
	}
	
	// GAME-Scene
		/**
		 * setFifthScene represents the Game Scene
		 * 
		 * @Author Rijon Mushkolaj
		 */
	public Scene setFifthScene() {
		
		gameArea = new BorderPane();
		b1 = new Button("b1");
		b2 = new Button("b2");
		b3 = new Button("b3");
		b4 = new Button("b4");
		b5 = new Button("b5");
		b6 = new Button("b6");
		
		cardButtonsBox = new HBox();
		cardButtonsBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
		gameArea.setCenter(cardButtonsBox);
		
		fifthScene = new Scene(gameArea);
		
		return fifthScene;
	}
	

	
	public void start() {
		
		primaryStage.show();
		
	}

}
