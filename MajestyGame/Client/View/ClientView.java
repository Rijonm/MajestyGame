package View;


import java.util.ArrayList;

import Model.ClientModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView {
	
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
	public VBox centerStart;
	public BorderPane spielStartenArea;
	public ListView<String> lobby;
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
	public OpponentView opponentView;
	public DeckArea deckArea;
	public BuildingsArea buildingsArea;
	public Label[] buildingAry = new Label[8];
	public Label[] enemyBCarray = new Label[8];
	
	public ArrayList<OpponentView> opponentArrayList = new ArrayList<>();
	
	public ChatArea chatArea;
	public PlayerArea playerArea;
	public BottomArea bottomArea;
	public Button[] buttons = new Button[6];
	public Label[] meeplesDeck = new Label[6];
	public Label buildingCounter;
	//public Label ebCounter;
//	public Button b1 = new Button("b1");
//	public Button b2 = new Button("b2");
//	public Button b3 = new Button("b3");
//	public Button b4 = new Button("b4");
//	public Button b5 = new Button("b5");
//	public Button b6 = new Button("b6");
	public VBox centerVbox, leftPlace, leftVbox, leftMainBox, rightVbox;
	
	
	//enemyDeck
	public int ANZAHL_GEGNER = 0;
	public HBox enemyArea = new HBox();
	
	//ChatArea
	public TextField chatContent;
	public 	TextField chatInput = new TextField();
	public Button sendButton = new Button("senden");
	public Label chatinhalt;
	
	
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

	
	//PopUp for SplitCards 
	/**
	 * @author ozanf
	 * 
	 */
	
	public Stage popUpStage;
	public Scene popUpScene;
	public VBox popUpBox;
	public Button pb1, pb2;
	
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
		
		for (int i = 0; i < 6; i++) {
//			Random r1 = new Random();
//			int rn = r1.nextInt(6);
			Label m = new Label("Meeple");
			meeplesDeck[i] = m;
			
			Button b = new Button("b"+i);
			buttons[i] = b;
		}
		for (int j = 0; j < 8; j++) {
			buildingCounter = new Label("0");
			buildingAry[j] = buildingCounter;

		}
		
		//for (int k = 0; k < ANZAHL_GEGNER; k++) {
			
			for (int l = 0; l < 8; l++) {		
				Label ebCounter = new Label("0");
				enemyBCarray[l] = ebCounter;
			}
		//}
		
		majestyLogoView.setImage(majestyLogo);
		majestyLogoView.setFitWidth(615);
		majestyLogoView.setFitHeight(320);
		
				
		connectPane = new VBox();
		connectPane.setAlignment(Pos.CENTER);
		connectPane.getChildren().addAll(majestyLogoView, ip, port, connectB);
		connectPane.getStyleClass().add("panes");
		
		connectScene = new Scene(connectPane, 700, 800);
		connectScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		this.primaryStage.setFullScreen(false);
		//primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); 	//User Information Text after going fullscreen
		this.primaryStage.setResizable(false);
		this.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
		this.primaryStage.setTitle("Majesty Group");
		this.primaryStage.setScene(connectScene);
		
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
	 * @Author Yusuf
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
	 * @Author Yusuf
	 */
	public Scene setThirdScene() {
		
		spielStartenArea = new BorderPane();
		centerStart = new VBox();
		centerStart.setAlignment(Pos.CENTER);
		optionenBox = new HBox();
		spielStartenArea.setAlignment(centerStart, Pos.CENTER);
		
		
		lobby = new ListView<>(model.getLobbyPlayers());
		optionenBox.getChildren().addAll(spielstartenB, einstellungenB, highscoreB, spielanleitungB, logoutB);
		optionenBox.setAlignment(Pos.CENTER);
		lobby.getStyleClass().add("lobby");
		//spielStartenArea.setTop(lobby);
		centerStart.getChildren().addAll(lobby, optionenBox);
		
		spielStartenArea.setCenter(centerStart);
		spielStartenArea.getStyleClass().add("spielStartenArea");
		
		
		
		thirdScene = new Scene(spielStartenArea);
		thirdScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		return thirdScene;
	}
	
	// einstellungenScene
		/**
		 * setFourthScene represents the Preferences Scene
		 * 
		 * @Author Yusuf
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
		gameArea.getStyleClass().add("gameArea");
		
		centerVbox = new VBox();
		leftVbox = new VBox();
		leftPlace = new VBox();
		leftMainBox = new VBox();
		leftMainBox.getStyleClass().add("leftMainBox");
		leftMainBox.setAlignment(Pos.BOTTOM_LEFT);
		leftVbox.setId("leftVbox");
		rightVbox= new VBox();
		
		
		//EnemyDeck		
		for(int i = 0; i < ANZAHL_GEGNER; i++) {
			VBox enemyPlace = new VBox();
			
			Label lp = new Label();
			lp.textProperty().bind(model.opponentPlayers.get(i).name);
			
			OpponentView ov = new OpponentView();
			for(int j = 0; j < 8; j++) {
					//ov.lAr[j].setText(""+model.opponentPlayers.get(i).hand.get(j));
					ov.lAr[j].textProperty().bind(model.opponentPlayers.get(i).hand.get(j).asString());
				}
			opponentArrayList.add(ov);
			enemyPlace.getChildren().addAll(lp, ov);
			enemyArea.getChildren().add(enemyPlace);
			enemyArea.setAlignment(Pos.CENTER);
		}

				
		// LeftArea
			//EnemyDetails

		for (int x = 0; x < ANZAHL_GEGNER; x++) { 
			Label lblEnemyTitle = new Label("Your enemy "+ (x+1) +": ");
			Label lp = new Label();
			lp.textProperty().bind(model.opponentPlayers.get(x).name);
			Label lc = new Label();
			lc.textProperty().bind(model.opponentPlayers.get(x).coins.asString());
			Label lm = new Label();
			lm.textProperty().bind(model.opponentPlayers.get(x).meeples.asString());
			
			Label lblMainMeeplesCard = new Label("J");
			lblMainMeeplesCard.getStyleClass().addAll("meeplesCard", "meeplesCard-4");
			lblMainMeeplesCard.setMinHeight(128);
			
			Region spacerP = new Region();
			
			VBox.setVgrow(spacerP, Priority.ALWAYS);
			
			
			lp.getStyleClass().add("playerAreaLbl");
			lc.getStyleClass().add("playerAreaLbl");
			lm.getStyleClass().add("playerAreaLbl");
			
			leftVbox.getChildren().addAll(lblEnemyTitle, lp, lc, lm, lblMainMeeplesCard, spacerP);
			leftVbox.getStyleClass().add("playerNameArea");
		}
			//MainDetails
		Region spacerMain = new Region();
		Label lblMainTitle = new Label("My empire:");
		Label lblMainName = new Label();
		lblMainName.textProperty().bind(model.opponentPlayers.get(0).name);
		lblMainName.getStyleClass().add("playerAreaLbl");
		
		Label lblMainCoin = new Label();
		lblMainCoin.textProperty().bind(model.opponentPlayers.get(0).coins.asString());
		lblMainCoin.getStyleClass().add("playerAreaLbl");
		
		Label lblMainMeeples = new Label();
		Label lblMainMeeplesCard = new Label("m");
		lblMainMeeples.textProperty().bind(model.opponentPlayers.get(0).meeples.asString());
		lblMainMeeples.getStyleClass().add("playerAreaLbl");
		System.out.println("meeplesCard-"+lblMainMeeples.getText());
		lblMainMeeplesCard.getStyleClass().addAll("meeplesCard", "meeplesCard-"+lblMainMeeples.getText());
		lblMainMeeplesCard.setMinHeight(128);
		
		VBox.setVgrow(spacerMain, Priority.ALWAYS);
		leftMainBox.getStyleClass().add("playerNameArea");
		leftMainBox.getChildren().addAll(spacerMain, lblMainTitle, lblMainName, lblMainCoin, lblMainMeeples, lblMainMeeplesCard);
		leftPlace.getChildren().addAll(leftVbox, leftMainBox);
		
		
			
		//DeckArea
		HBox personDeck = new HBox();
		for (int i = 0; i < 6; i++) {
			VBox personBox = new VBox();
			Label m = meeplesDeck[i];
			m.setMinHeight(25);
			Button b = buttons[i];
			b.setMinHeight(324);
			personBox.getChildren().addAll(b, m);
			personDeck.getChildren().add(personBox);
		}
		personDeck.setAlignment(Pos.CENTER);
		
		
		//personDeck.setAlignment(Pos.CENTER);
		//personDeck.getStyleClass().add("personDeck");
		
		
		//BuildingArea
		Label buildingsImage = new Label();
		buildingsImage.getStyleClass().add("buildingCard");
		buildingsImage.setMinHeight(238);
		HBox buildingsCounters = new HBox();
		buildingsCounters.setAlignment(Pos.CENTER);
		buildingsCounters.getStyleClass().add("buildingsCounters");
		for (int i = 0; i < 8; i++) {
			
			buildingCounter = buildingAry[i];			
			buildingCounter.setAlignment(Pos.CENTER);
			buildingCounter.getStyleClass().add("counterLabel");
			//Region spacerC = new Region();
			//spacerC.getStyleClass().add("spacerBuildings");
			buildingCounter.getStyleClass().add("coinCounter");
			buildingsCounters.getChildren().add(buildingCounter);
		}
		
		
		//BottomArea
		HBox bottomArea = new HBox();
		Label roundCounter = new Label("Gespielte Runden: ");
		Label cardCounter = new Label("Gespielte Karten: ");
		Label timeCounter = new Label("Gespielte Zeit: ");
		bottomArea.getChildren().addAll(roundCounter, cardCounter, timeCounter);
		bottomArea.setId("bottomArea");
		
		
		//ChatArea
		VBox chatArea = new VBox();
		Label chatTitle = new Label("Chat");
		chatTitle.getStyleClass().add("chatTitle");
		chatContent = new TextField("");
		chatContent.setEditable(false);
		Region spacerC = new Region();
		spacerC.getStyleClass().add("chatSpacer");
		chatContent.getStyleClass().add("chatContent");		
		chatArea.getStyleClass().add("chatArea");
		chatArea.getChildren().addAll(chatTitle, chatContent, spacerC, chatInput, sendButton);
		chatInput.getStyleClass().add("chatInputTextField");
		sendButton.getStyleClass().add("sendButton");
		

		centerVbox.setAlignment(Pos.CENTER);

		centerVbox.getChildren().addAll(enemyArea, personDeck, buildingsImage, buildingsCounters);
		
		BorderPane.setAlignment(leftPlace, Pos.CENTER);
		gameArea.setLeft(leftPlace);
		//gameArea.setLeft(leftVbox);
		gameArea.setCenter(centerVbox);
		gameArea.setRight(chatArea);
		gameArea.setBottom(bottomArea);
		fifthScene = new Scene(gameArea);
		fifthScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		return fifthScene;
	}

	
	//PopUpStage and PopupScene
	/**
	 * 
	 * @author ozanf
	 * 
	 */
	public Stage popUpStage() {
		popUpStage = new Stage();
		popUpBox = new VBox();
		pb1 = new Button("1");
		pb2 = new Button("2");
		popUpBox.getChildren().addAll(pb1, pb2);
		popUpScene = new Scene(popUpBox,100,100);
		popUpScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		popUpStage.setScene(popUpScene);
		popUpStage.show();
		return popUpStage;
		
		
		
	}
	
	
	
	//einstellungenScene
	/**
	 * setSixthScene represents the settings scene
	 * 
	 * @Author Yusuf
	 */
	public Scene setSixthScene() {
		
		highscoreArea = new BorderPane();
		highscoreListLabel = new Label("placeholder");
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
