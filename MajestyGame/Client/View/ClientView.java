package View;


import Model.ClientModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientView{
	public Stage primaryStage;
	public ClientModel model;
	
	//connectScene
	public Scene connectScene;
	public VBox connectPane;
	public TextField ip;
	public TextField port;
	public Button connectB;
	
	
	public Image majestyLogo = new Image(this.getClass().getClassLoader().getResourceAsStream("images/majestyLogo.png"));
	public ImageView majestyLogoView = new ImageView();
	
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
		connectB.getStyleClass().add("connectButton");
		ip = new TextField("IP");
		ip.getStyleClass().addAll("ipTextField", "TextField");
		port = new TextField("Port");
		port.getStyleClass().addAll("portTextField", "TextField");
		
		
		
		majestyLogoView.setImage(majestyLogo);
		majestyLogoView.setFitWidth(615);
		majestyLogoView.setFitHeight(320);
		
				
		connectPane = new VBox();
		connectPane.setAlignment(Pos.CENTER);
		connectPane.getChildren().addAll(majestyLogoView, ip, port, connectB);
		connectPane.getStyleClass().add("panes");
		
		connectScene = new Scene(connectPane, 700, 800);
		connectScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		primaryStage.setFullScreen(true);
		//primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); 	//User Information Text after going fullscreen
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
		primaryStage.setTitle("Majesty Group");
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
		loginPane.getStyleClass().add("panes");
		
		loginArea = new VBox();
		loginArea.setAlignment(Pos.CENTER);
		loginArea.getStyleClass().add("panes");
		
		userNameLogin  = new TextField("username");
		userpasswordLogin = new TextField("password");
		
		loginArea.getChildren().addAll(majestyLogoView, userNameLogin, userpasswordLogin, loginB, registrierenB);
		loginPane.setCenter(loginArea);
		registrierenB.getStyleClass().add("registerButton");
		

		firstScene = new Scene(loginPane, 700, 800);
		firstScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
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
		registrierenArea.getStyleClass().add("panes");
		
		userNameRegister = new TextField("Username");
		userPasswordRegister = new TextField("Password");
		

		registrierenArea.getChildren().addAll(userNameRegister,userPasswordRegister,registrierenBB, backFirstSceneB);
		
		secondScene = new Scene(registrierenArea);
		secondScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
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
		thirdScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
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
		fourthScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		
		return fourthScene;
	}
	
	// gameScene
		/**
		 * setFifthScene represents the game scene
		 * 
		 * @Author Yusuf Or
		 */
	public Scene setFifthScene() {
		
		gameArea = new BorderPane();
		cardButtonsBox = new HBox();
		b1.getStyleClass().addAll("gameButtonFirst", "gameButtons");
		b2.getStyleClass().addAll("gameButtonSecond", "gameButtons");
		b3.getStyleClass().addAll("gameButtonThird", "gameButtons");
		b4.getStyleClass().addAll("gameButtonFourth", "gameButtons");
		b5.getStyleClass().addAll("gameButtonFifth", "gameButtons");
		b6.getStyleClass().addAll("gameButtonSixth", "gameButtons");
		
		HBox buildingsPlayer1 = new HBox(); 
		Label buildingCard1 = new Label();
		buildingCard1.getStyleClass().addAll("bd1", "buildingCard");
		Label buildingCard2 = new Label();
		buildingCard2.getStyleClass().addAll("bd2", "buildingCard");		
		Label buildingCard3 = new Label();
		buildingCard3.getStyleClass().addAll("bd3", "buildingCard");		
		Label buildingCard4 = new Label();
		buildingCard4.getStyleClass().addAll("bd4", "buildingCard");
		Label buildingCard5 = new Label();
		buildingCard5.getStyleClass().addAll("bd5", "buildingCard");
		Label buildingCard6 = new Label();
		buildingCard6.getStyleClass().addAll("bd6", "buildingCard");		
		Label buildingCard7 = new Label();
		buildingCard7.getStyleClass().addAll("bd7", "buildingCard");		
		Label buildingCard8 = new Label();
		buildingCard8.getStyleClass().addAll("bd8", "buildingCard");		
		
	
		buildingsPlayer1.getChildren().addAll(buildingCard1,buildingCard2,buildingCard3,buildingCard4,buildingCard5,buildingCard6,buildingCard7,buildingCard8);
		cardButtonsBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
		
		gameArea.setCenter(cardButtonsBox);
		gameArea.setBottom(buildingsPlayer1);
		
		
		fifthScene = new Scene(gameArea);
		fifthScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		return fifthScene;
	}
	
	
	//einstellungenScene
	/**
	 * setSixthScene represents the settings scene
	 * 
	 * @Author Yusuf
	 */
	public Scene setSixthScene() {
		
		highscoreArea = new BorderPane();
		highscoreListLabel = new Label("Highscore");
		highscoreArea.setCenter(highscoreListLabel);
		highscoreArea.setBottom(backToGameScene);
		
		sixthScene = new Scene(highscoreArea);
		sixthScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		return sixthScene;
	}
	
	//spielanleitungScene
	/**
	 * setSeventhScene represents the rules scene
	 * 
	 * @Author Yusuf
	 */
	public Scene setSeventhScene() {
		
		rulesArea = new BorderPane();
		rulesBox = new VBox();
		rulesLabel  = new Label("Rules");
		
		rulesBox.getChildren().add(rulesLabel);

		rulesArea.setCenter(rulesBox);
		rulesArea.setBottom(backToGameScene);
		
		seventhScene = new Scene(rulesArea);
		seventhScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		return seventhScene;
	}

	
	public void start() {
		
		primaryStage.show();
		
	}

}
