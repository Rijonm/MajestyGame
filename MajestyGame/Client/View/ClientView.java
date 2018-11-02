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
	//connectScene
	public Scene connectScene;
	public VBox connectPane;
	public TextField ip;
	public TextField port;
	public Button connectB;
	//loginScene
	public Scene firstScene; 
	public BorderPane loginPane;
	public VBox loginArea;
	public TextField usernameTf = new TextField("username");
	public TextField passwordTf = new TextField("password");
	public Button loginB = new Button ("Login");
	public Button registrierenB = new Button ("Regsitrieren");
	// registrierenScene
	public Scene secondScene; 
	public VBox registrierenArea;
	public Button registrierenBB = new Button("Registrieren");
	public Button backFirstSceneB = new Button ("Back");
	//gamestartScene
	public Scene thirdScene; 
	public HBox optionenBox;
	public BorderPane spielStartenArea;
	public Button spielstartenB = new Button("Spiel starten");
	public Button einstellungenB = new Button ("Einstellungen");
	public Button highscoreB = new Button ("Highscore");
	public Button logoutB = new Button ("Logout");
	public Button spielanleitungB = new Button ("Spielanleitung");
	//einstellungenScene
	public Scene fourthScene; 
	//gameScene
	public Scene fifthScene; 
	public BorderPane gameArea;
	public Button b1 = new Button("b1");
	public Button b2 = new Button("b2");
	public Button b3 = new Button("b3");
	public Button b4 = new Button("b4");
	public Button b5 = new Button("b5");
	public Button b6 = new Button("b6");
	HBox cardButtonsBox;
	

	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		connectB= new Button("Connect");
		ip = new TextField("IP");
		port = new TextField("Port");
		
		connectPane = new VBox();
		
		connectPane.getChildren().addAll(ip, port, connectB);
		
		connectScene = new Scene(connectPane);
		
		primaryStage.setScene(connectScene);	
	}
	
	public Scene setFirstScene() {
		
		loginPane = new BorderPane();

		loginArea = new VBox();
		
		loginArea.getChildren().addAll(usernameTf, passwordTf, loginB, registrierenB);
		loginPane.setCenter(loginArea);
		
		firstScene = new Scene(loginPane);
		
		return firstScene;
	}
	
	
	// registrierenScene
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
		
	
	// gameStartScene
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
	
	// einstellungenScene
		/**
		 * setFourthScene represents the Preferences Scene
		 * 
		 * @Author Rijon Mushkolaj
		 */
	public Scene setFourthScene() {
		
		
		return fourthScene;
	}
	
	// gameScene
		/**
		 * setFifthScene represents the Game Scene
		 * 
		 * @Author Rijon Mushkolaj
		 */
	public Scene setFifthScene() {
		
		gameArea = new BorderPane();
		
		
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
