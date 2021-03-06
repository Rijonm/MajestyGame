package View;


import java.util.ArrayList;
import java.util.List;

import Model.ClientModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
	public Label statusMessageLabel = new Label("");

	
	
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
	public Button spielstartenB = new Button("Go");
	public Button einstellungenB = new Button("Settings");
	public Button highscoreB = new Button("Highscore");
	public Button spielanleitungB = new Button("Rules");
	public Button logoutB = new Button("Logout");
	
	//einstellungenScene
	public Scene fourthScene; 
	public Button setupSaveB = new Button("save");
	
	public Button enButton = new Button("EN");
	public Button deButton = new Button("DE");
	
	public Button bgButton1 = new Button("1");
	public Button bgButton2 = new Button("2");
	public Button bgButton3 = new Button("3");
	
	public int langInteger = 0;  //default EN
	
	//gameScene
	public Scene fifthScene; 
	public String backGround = "gameArea";
	public BorderPane gameArea;
	public OpponentView opponentView;

	public Label[] buildingAry = new Label[8];
	public Label[] enemyBCarray = new Label[8];
	
	public ArrayList<OpponentView> opponentArrayList = new ArrayList<>();
	
	public Button[] buttons = new Button[6];
	public Label[] meeplesDeck = new Label[6];
	public Label buildingCounter;

	public VBox centerVbox, leftPlace, leftVbox, leftMainBox, rightVbox;
	
	public Label roundCounter;
	public Label cardCounter;
	public Label usernameTurn;
	public ArrayList<Label> lblMainMeeplesCardArrayList = new ArrayList<>();
	public Label lblMainMeeplesCard;
	
	//enemyDeck
	public int ANZAHL_GEGNER = 0;
	public HBox enemyArea = new HBox();
	
	//ChatArea
	public TextArea chatContent;
	public 	TextField chatInput = new TextField();
	public Button sendButton = new Button("Send");
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
		
	public Button backToGameScene = new Button("Back");

	
	//PopUp for SplitCards 
	/**
	 * @author ozanf
	 * 
	 */
	public Stage popUpStage;
	public Scene popUpScene;
	public HBox popUpBox;
	public Button pb1, pb2;
	
	//Not Enough Meeples Stage
	public Stage notEnoughMeeplesStage;
	public Button closeNotEnoughM = new Button("OK");
	
	//Winner Stage
	public Stage winnerStage;
	public Scene winnerScene;
	public VBox winnerBox;
	public Label winnerLabel;
	public Button buttonBackToStart = new Button("back");
	
	//opponentLogoutStage
	public Stage opponentLogoutStage;
	public Scene opponentLogoutScene;
	public Label opponentLogoutName = new Label("");
	
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
			Label m = new Label();
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
		
		statusMessageLabel.getStyleClass().add("statusMessageLabel");
		
		loginArea.getChildren().addAll(majestyLogoView, userNameLogin, userpasswordLogin, statusMessageLabel, loginB, registrierenB);
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
	public Scene setThirdScene(int langInteger) {
		
		spielStartenArea = new BorderPane();
		centerStart = new VBox();
		centerStart.setAlignment(Pos.CENTER);
		optionenBox = new HBox();
		spielStartenArea.setAlignment(centerStart, Pos.CENTER);
		
		if(langInteger==1) {
			spielstartenB.setText("Start");
			einstellungenB.setText("Einstellungen");
			spielanleitungB.setText("Spielanleitung");
		}else {
			spielstartenB.setText("Go");
			einstellungenB.setText("Settings");
			spielanleitungB.setText("Rules");
		}
		
		
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
	public Scene setFourthScene(int langInteger) {
		
		VBox einstellungenArea = new VBox();
		einstellungenArea.setId("einstellungenArea");
		HBox backGroundBox = new HBox();
		
		HBox langBox = new HBox();
		HBox musicBox = new HBox();
		
		Label languageLabel = new Label("Language: ");
		if(langInteger==1) {
			languageLabel.setText("Sprache: ");
		}else {
			languageLabel.setText("Language: ");
		}

		Label backgroundImageLabel = new Label("Background-Image: ");
		if(langInteger==1) {
			backgroundImageLabel.setText("Hintergrundbild: ");
		}else {
			backgroundImageLabel.setText("Background-Image: ");
		}
		
		bgButton1.getStyleClass().addAll("selectedBUTTON", "bgButton1");
		bgButton1.setMinHeight(62);
		bgButton2.getStyleClass().add("bgButton2");
		bgButton2.setMinHeight(62);
		bgButton3.getStyleClass().add("bgButton3");
		bgButton3.setMinHeight(62);
		setupSaveB.getStyleClass().add("setUpButtons");
		if(langInteger==1) {
			setupSaveB.setText("Speichern");
		}else {
			setupSaveB.setText("Save");
		}
		
		langBox.getChildren().addAll(languageLabel,enButton, deButton);
		backGroundBox.getChildren().addAll( backgroundImageLabel,bgButton1,bgButton2,bgButton3);
		
		
		einstellungenArea.getChildren().addAll(langBox,backGroundBox, musicBox,setupSaveB);
		
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
	public Scene setFifthScene(String backGround, int langInteger) {
		
		gameArea = new BorderPane();
		gameArea.getStyleClass().add(backGround);
		
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
			Region spacerB = new Region();
			
			VBox.setVgrow(spacerB, Priority.ALWAYS);
			spacerB.setMinHeight(20);
			opponentArrayList.add(ov);
			enemyPlace.getChildren().addAll(lp, ov, spacerB);
			enemyArea.getChildren().add(enemyPlace);
			enemyArea.setAlignment(Pos.CENTER);
		}

				
		// LeftArea
			//EnemyDetails

		for (int x = 0; x < ANZAHL_GEGNER; x++) { 
			Label lblEnemyTitle = new Label("Your enemy "+ (x+1) +": ");
			if(langInteger==1) {
				lblEnemyTitle.setText("Dein Feind " + (x+1) +": ");
			} else {
				lblEnemyTitle.setText("Your Enemy " + (x+1) + ": ");
			}
			Label lp = new Label();
			lp.textProperty().bind(model.opponentPlayers.get(x).name);
			Label lc = new Label();
			Label lcTitle = new Label("Coins: ");
			HBox lcBox = new HBox();
			lcBox.getChildren().addAll(lcTitle, lc);
			lc.textProperty().bind(model.opponentPlayers.get(x).coins.asString());
			Label lm = new Label();
			lm.textProperty().bind(model.opponentPlayers.get(x).meeples.asString());
			
			Label lblMainMeeplesCard = new Label();
			lblMainMeeplesCard.getStyleClass().addAll("meeplesCard", "meeplesCard-"+ model.opponentPlayers.get(x).meeples.asString());
			lblMainMeeplesCard.setMinHeight(128);
			lblMainMeeplesCardArrayList.add(lblMainMeeplesCard);
			Region spacerP = new Region();
			
			VBox.setVgrow(spacerP, Priority.ALWAYS);
			
			
			lp.getStyleClass().add("playerAreaLbl");
			lc.getStyleClass().add("playerAreaLbl");
			lm.getStyleClass().add("playerAreaLbl");
			
			leftVbox.getChildren().addAll(lblEnemyTitle, lp, lcBox, lm, lblMainMeeplesCard, spacerP);
			leftVbox.getStyleClass().add("playerNameArea");
		}
			//MainDetails
		Region spacerMain = new Region();
		Label lblMainTitle = new Label("My empire: ");
		if(langInteger==1) {
			lblMainTitle.setText("Mein Königreich: ");
		}else {
			lblMainTitle.setText("My Empire: ");
		}
		Label lblMainName = new Label();
		//lblMainName.setText("");
		lblMainName.textProperty().bind(model.myName);
		lblMainName.getStyleClass().add("playerAreaLbl");
		
		HBox lblMainCoinsTextBox = new HBox();
		Label lblMainCoin = new Label();
		Label lblMainCoinTitle = new Label("Coins: ");
		
		lblMainCoin.textProperty().bind(model.myCoins.asString());
		lblMainCoin.getStyleClass().add("playerAreaLbl");
		lblMainCoinsTextBox.getChildren().addAll(lblMainCoinTitle, lblMainCoin);
		
		Label lblMainMeeples = new Label();
		lblMainMeeples.textProperty().bind(model.myMeeples.asString());
		lblMainMeeplesCard = new Label();
		//lblMainMeeples.textProperty().bind(model.opponentPlayers.get(0).meeples.asString());
		lblMainMeeples.getStyleClass().add("playerAreaLbl");
		System.out.println("meeplesCard-"+lblMainMeeples.getText());
		lblMainMeeplesCard.getStyleClass().addAll("meeplesCard", "meeplesCard-"+lblMainMeeples.getText());
		lblMainMeeplesCard.setMinHeight(128);
		
		VBox.setVgrow(spacerMain, Priority.ALWAYS);
		leftMainBox.getStyleClass().add("playerNameArea");
		leftMainBox.getChildren().addAll(spacerMain, lblMainTitle, lblMainName, lblMainCoinsTextBox, lblMainMeeples, lblMainMeeplesCard);
		leftPlace.getChildren().addAll(leftVbox, leftMainBox);
		
		
			
		//DeckArea
		HBox personDeck = new HBox();
		for (int i = 0; i < 6; i++) {
			VBox personBox = new VBox();
			Label m = meeplesDeck[i];
			m.setMinHeight(20);
			Button b = buttons[i];
			b.setDisable(true);
			b.setMinHeight(243);
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
			buildingCounter.textProperty().bind(model.myHand.get(i).asString());
			//Region spacerC = new Region();
			//spacerC.getStyleClass().add("spacerBuildings");
			buildingCounter.getStyleClass().add("coinCounter");
			buildingsCounters.getChildren().add(buildingCounter);
		}
		
		
		//BottomArea
		HBox bottomArea = new HBox();
		Label roundCounterTitle = new Label("Played rounds: ");
		if(langInteger==1) {
			roundCounterTitle.setText("Gespielte Runden: ");
		}else {
			roundCounterTitle.setText("Played rounds: ");
		}
		roundCounter = new Label();
		roundCounter.textProperty().bind(model.round.asString());
		Label cardCounterTitle = new Label(" Played cards: ");
		if(langInteger==1) {
			cardCounterTitle.setText(" Gespielte Karten: ");
		}else {
			cardCounterTitle.setText(" Played cards: ");
		}
		cardCounter = new Label();
		cardCounter.textProperty().bind(model.playedCards.asString());
		Label usernameTurnTitle = new Label(" Whos turn: ");
		if(langInteger==1) {
			usernameTurnTitle.setText(" An der Reihe: ");
		}else {
			usernameTurnTitle.setText(" Whos turn: ");
		}
		usernameTurn = new Label();
		usernameTurn.textProperty().bind(model.turnUsername);
		bottomArea.getChildren().addAll(roundCounterTitle, roundCounter, cardCounterTitle, cardCounter, usernameTurnTitle, usernameTurn);
		bottomArea.setId("bottomArea");
		
		
		//ChatArea
		VBox chatArea = new VBox();
		Label chatTitle = new Label("Chat");
		chatTitle.getStyleClass().add("chatTitle");
		chatContent = new TextArea();
		ScrollPane chatScroll = new ScrollPane();
		chatScroll.setFitToWidth(true);
		chatScroll.setFitToHeight(true);
		chatScroll.setContent(chatContent);
		chatContent.setEditable(false);
		chatContent.setMinHeight(400.00);
		chatContent.setMaxWidth(290.00);
		
		Region spacerC = new Region();
		spacerC.getStyleClass().add("chatSpacer");
		chatContent.getStyleClass().add("chatContent");		
		chatArea.getStyleClass().add("chatArea");
		chatArea.getChildren().addAll(chatTitle, chatContent, spacerC, chatInput, sendButton);
		chatInput.getStyleClass().add("chatInputTextField");
		sendButton.getStyleClass().add("sendButton");
		if(langInteger==1) {
			sendButton.setText("Senden");
		}else {
			sendButton.setText("Send");
		}

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
	public Stage popUpStage(int langInteger) {
		popUpStage = new Stage();
		
		popUpBox = new HBox();
		
		pb1 = new Button();
		pb2 = new Button();
		
		pb1.setMinHeight(243);
		pb2.setMinHeight(243);
		pb1.getStyleClass().addAll("splitCard", "splitCard1");
		pb2.getStyleClass().addAll("splitCard", "splitCard2");
		
		popUpBox.getStyleClass().add("popUpBox");
		popUpBox.getChildren().addAll(pb1, pb2);
		popUpScene = new Scene(popUpBox,310,253);
		popUpScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		popUpStage.setScene(popUpScene);
		popUpStage.setResizable(false);
		popUpStage.getIcons().add(new Image("images/majestyIcon.png"));
		popUpStage.setTitle("Split Card - Choose one!");
		if(langInteger==1) {
			popUpStage.setTitle("Split Card - Wähle eine!");
		}else {
			popUpStage.setTitle("Split Card - Choose one!");
		}
		popUpStage.show();
		return popUpStage;
	}
	
	//Zu wenig Meeples Scene
	public Stage notEnoughMeeples(int langInteger) {
		notEnoughMeeplesStage = new Stage();
		
		VBox notEnoughMBox = new VBox();
		
		Label notEnoughMLbl = new Label("You don't have enough Meeples!");
		if(langInteger==1) {
			notEnoughMLbl.setText("Du hast nicht genügend Meepless!");
		}else {
			notEnoughMLbl.setText("You don't have enough Meeples!");
		}
		notEnoughMBox.getStyleClass().add("notEnoughMBox");
		notEnoughMBox.setAlignment(Pos.CENTER);


		notEnoughMBox.getChildren().addAll(notEnoughMLbl, closeNotEnoughM);
		Scene notEnoughMeeplesScene = new Scene(notEnoughMBox,300,200);
		
		notEnoughMeeplesScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		notEnoughMeeplesStage.setScene(notEnoughMeeplesScene);
		notEnoughMeeplesStage.setResizable(false);
		notEnoughMeeplesStage.getIcons().add(new Image("images/majestyIcon.png"));
		notEnoughMeeplesStage.setTitle("Error!");
		if(langInteger==1) {
			notEnoughMeeplesStage.setTitle("Fehler!");
		}else {
			notEnoughMeeplesStage.setTitle("Error!");
		}
		notEnoughMeeplesStage.show();
		return notEnoughMeeplesStage;
				
	}
	
	//Winner Scene
	public Stage winnerStage(List<String> winners, int langInteger) {
		winnerStage = new Stage();
		winnerBox = new VBox();

		Label staticW = new Label("Winner is: ");
		if(langInteger==1) {
			staticW.setText("Der Gewinner ist: ");
		}else {
			staticW.setText("Winner is: ");
		}
		ArrayList<String> w = (ArrayList<String>)winners;
		for(int i = 0; i < winners.size(); i++) {
			winnerLabel = new Label(w.get(i));
			winnerLabel.getStyleClass().add("winnerLabel");
		}
		if(langInteger==1) {
			buttonBackToStart.setText("Zurueck");
		}else {
			buttonBackToStart.setText("Back");
		}
		winnerBox.getChildren().addAll(staticW, winnerLabel, buttonBackToStart);
		winnerBox.getStyleClass().add("winnerBox");
		winnerBox.setAlignment(Pos.CENTER);
		winnerScene = new Scene(winnerBox,300,200);
		winnerScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		winnerStage.getIcons().add(new Image("images/majestyIcon.png"));
		winnerStage.setTitle("Congratulation!");
		if(langInteger==1) {
			winnerStage.setTitle("Gratulation!");
		}else {
			winnerStage.setTitle("Congratulation!");
		}
		winnerStage.setScene(winnerScene);
		winnerStage.show();
		return winnerStage;
	}
	//Winner Scene
	public Stage opponentLogoutStage(int langInteger) {
		opponentLogoutStage = new Stage();
		VBox opponentLogoutBox = new VBox();

		Label opponentLogoutLabelTitle = new Label("Opponent has logged out: " );
		if(langInteger==1) {
			opponentLogoutLabelTitle.setText("Gegner hat sich ausgeloggt: ");
		}else {
			opponentLogoutLabelTitle.setText("Opponent has logged out: ");
		}


		opponentLogoutBox.getChildren().addAll(opponentLogoutLabelTitle, opponentLogoutName, buttonBackToStart);
		opponentLogoutBox.getStyleClass().add("winnerBox");
		opponentLogoutBox.setAlignment(Pos.CENTER);
		opponentLogoutScene = new Scene(opponentLogoutBox,300,200);
		opponentLogoutScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		
		opponentLogoutStage.getIcons().add(new Image("images/majestyIcon.png"));
		opponentLogoutStage.setTitle("Logout Alert!");
		if(langInteger==1) {
			opponentLogoutStage.setTitle("Logout Meldung!");
		}else {
			opponentLogoutStage.setTitle("Logout Alert!");
		}
		opponentLogoutStage.setScene(opponentLogoutScene);
		opponentLogoutStage.show();
		return opponentLogoutStage;
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
		
		if(langInteger==1) {
			backToGameScene.setText("Zurück");
		} else {
			backToGameScene.setText("Back");
		}
			
		sixthScene = new Scene(highscoreArea);
		sixthScene.getStylesheets().add(getClass().getResource("majesty.css").toExternalForm());
		return sixthScene;
	}
	
	public int getCardIndexOnButtonOfSplitCard(int buttonIndex, int cardIndex) {
		int cardIndexOfSplitCard = 0;
		
		switch (cardIndex) {
			case 7:
				pb1.getStyleClass().add("gameButton0");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 0);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton1");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 1);
					
					popUpStage.close();
				});
				break;
			case 8:
				pb1.getStyleClass().add("gameButton0");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 0);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton4");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 4);
					popUpStage.close();
				});
				break;
			case 9:
				pb1.getStyleClass().add("gameButton2");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 2);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton1");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 1);
					popUpStage.close();
				});
				break;
			case 10:
				pb1.getStyleClass().add("gameButton4");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 4);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton1");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 1);
					popUpStage.close();
				});
				break;
			case 11:
				pb1.getStyleClass().add("gameButton2");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 2);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton3");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 3);
					popUpStage.close();
				});
				break;
			case 12:
				pb1.getStyleClass().add("gameButton2");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 2);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton5");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 5);
					popUpStage.close();
				});
				break;
			case 13:
				pb1.getStyleClass().add("gameButton6");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 6);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton2");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 2);
					popUpStage.close();
				});
				break;
			case 14:
				pb1.getStyleClass().add("gameButton4");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 4);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton3");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 3);
					popUpStage.close();
				});
				break;
			case 15:
				pb1.getStyleClass().add("gameButton5");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 5);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton3");
				pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 3);
					popUpStage.close();
				});
				break;
			case 16:
				pb1.getStyleClass().add("gameButton6");
				pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 6);
					popUpStage.close();
				});
	
				pb2.getStyleClass().add("gameButton3");
				 pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 3);
					popUpStage.close();
				});
				break;
			case 17:
				 pb1.getStyleClass().add("gameButton4");
				 pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 4);
					popUpStage.close();
				});
	
				 pb2.getStyleClass().add("gameButton5");
				 pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 5);
					popUpStage.close();
				});
				break;
			case 18:
				 pb1.getStyleClass().add("gameButton6");
				 pb1.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 6);
					popUpStage.close();
				});
	
				 pb2.getStyleClass().add("gameButton5");
				 pb2.setOnAction(e -> {
					model.sendPlayerMoveMessage(buttonIndex, 5);
					popUpStage.close();
				});
				break;
			default:
				System.out.println("FAILURE CARD INDEX NOD FOUND!");
	
				break;
			}
		return cardIndexOfSplitCard;
	}
	
	

	
	
	//spielanleitungScene
	/**
	 * setSeventhScene represents the rules scene
	 * 
	 * @Author Yusuf
	 */
	public Scene setSeventhScene(int langInteger) {
		
		rulesArea = new BorderPane();
		rulesBox = new VBox();
		rulesBox.setId("rulesBox");
		rulesLabel  = new Label("Rules");
		TextArea rulesTextArea = new TextArea();
		rulesTextArea.setText("Let's just briefly summarize what you need to do at Majesty's:\r\n" + 
				"1. you must always take one of the people on display and connect it to the corresponding building of your\r\n" + 
				"of the kingdom.\r\n" + 
				"2nd This person may trigger a special action, and always brings you a few coins.\r\n" + 
				"3. do the whole thing in turn until each player has 12 people in his kingdom.\r\n" + 
				"4. you go through the final score piece by piece.\r\n" + 
				"5. the winner may put on a crown.\r\n" + 
				"\r\n" + 
				"Translated with www.DeepL.com/Translator");
		if(langInteger==1) {
			rulesLabel.setText("Regeln: ");
			rulesTextArea.setText(
				"Fassen wir noch einmal kurz zusammen, was du bei Majesty machen musst:\r\n" + 
				"1. Du musst immer eine der ausliegenden Personen nehmen und an das zugehörige Gebäude deines\r\n" + 
				"Königreichs legen.\r\n" + 
				"2. Diese Person löst eventuell eine Sonderaktion aus, und bringt dir immer ein paar Münzen.\r\n" + 
				"3. Das Ganze macht ihr reihum so lange bis jeder Spieler 12 Personen in seinem Königreich liegen hat.\r\n" + 
				"4. Ihr geht die Schlusswertung Stück für Stück durch.\r\n" + 
				"5. Der Gewinner darf sich eine Krone aufsetzen.");
		}
		
		rulesBox.getChildren().addAll(rulesLabel, rulesTextArea);

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
