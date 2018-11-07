package View;

import Model.ClientModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	public TextField userNameLogin, userpasswordLogin;
	public Button loginB = new Button ("Login");
	public Button registrierenB = new Button ("Registrieren");
	
	// registrierenScene
	public Scene secondScene; 
	public VBox registrierenArea;
	public TextField userNameRegister, userPasswordRegister;
	public Button registrierenBB = new Button("Registrieren");
	public Button backFirstSceneB = new Button("Zurück");
	
	//gamestartScene
	public Scene thirdScene; 
	public HBox optionenBox;
	public BorderPane spielStartenArea;
	public Button spielstartenB = new Button("Starten");
	public Button einstellungenB = new Button("Einstellungen");
	public Button highscoreB = new Button("Highscore");
	public Button spielanleitungB = new Button("Spielanleitung");
	public Button logoutB = new Button("Logout");
	
	//einstellungenScene
	public Scene fourthScene; 
	public BorderPane einstellungenArea;
	private VBox setupBox;
	public Label languageLabel = new Label("Sprache: ");
	public Label backgroundMusicLabel = new Label("Hintergrundmusik: ");
	public Label backgroundImageLabel = new Label("Hintergrundbild: ");
	public Button setupSaveB = new Button("save");
	
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
	
	//highscoreScene
	public Scene sixthScene;
	public BorderPane highscoreArea;
	public Label highscoreListLabel;

	
	//spielanleitungScene
	public Scene seventhScene;
	public BorderPane rulesArea;
	private VBox rulesBox;
	public Label rulesLabel;
		
	public Button backToGameScene = new Button("Zurück");

	//connectScene
	/**
	 * Represents the connection to the server scene
	 * 
	 * @param primaryStage
	 * @param model
	 */
	
	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		connectB = new Button("Connect");
		ip = new TextField("IP");
		port = new TextField("Port");
		
		connectPane = new VBox();
		connectPane.getChildren().addAll(ip, port, connectB);
		connectScene = new Scene(connectPane);
		
		primaryStage.setScene(connectScene);	
	}
	
	// loginScene
	/**
	 * setFirstScene represents the Login Scene
	 * 
	 * @return
	 */
	
	public Scene setFirstScene() {
		
		loginPane = new BorderPane();
		loginArea = new VBox();
		
		userNameLogin  = new TextField("username");
		userpasswordLogin = new TextField("password");
		
		loginArea.getChildren().addAll(userNameLogin, userpasswordLogin, loginB, registrierenB);
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
		
		userNameRegister = new TextField("Username");
		userPasswordRegister = new TextField("Password");
		

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
		
		optionenBox.getChildren().addAll(spielstartenB, einstellungenB, highscoreB, spielanleitungB, logoutB);
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
		
		einstellungenArea = new BorderPane();
		setupBox = new VBox();
		
		
		setupBox.getChildren().addAll(languageLabel, backgroundMusicLabel, backgroundImageLabel, setupSaveB);
		einstellungenArea.setCenter(setupBox);
		
		fourthScene = new Scene(einstellungenArea);
		
		
		return fourthScene;
	}
	
	// gameScene
		/**
		 * setFifthScene represents the game scene
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
	
	
	//einstellungenScene
	/**
	 * setSixthScene represents the settings scene
	 * 
	 * @return
	 */
	public Scene setSixthScene() {
		
		highscoreArea = new BorderPane();
		highscoreListLabel = new Label("Highscore");
		highscoreArea.setCenter(highscoreListLabel);
		highscoreArea.setBottom(backToGameScene);
		
		sixthScene = new Scene(highscoreArea);
		
		return sixthScene;
	}
	
	//spielanleitungScene
	/**
	 * setSeventhScene represents the rules scene
	 * 
	 * @return
	 */
	public Scene setSeventhScene() {
		
		rulesArea = new BorderPane();
		rulesBox = new VBox();
		rulesLabel  = new Label("Rules");
		
		rulesBox.getChildren().add(rulesLabel);

		rulesArea.setCenter(rulesBox);
		rulesArea.setBottom(backToGameScene);
		
		seventhScene = new Scene(rulesArea);
		
		return seventhScene;
	}

	
	public void start() {
		
		primaryStage.show();
		
	}

}
