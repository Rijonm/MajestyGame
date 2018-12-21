package Control;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import Model.ClientModel;
import Model.ClientModel.Opponent;
import View.ClientView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.control.Label;

public class ClientController {

	public ClientModel model;
	public ClientView view;

	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
		

		view.connectB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// model.connect(view.ip.getText(), Integer.parseInt(view.port.getText()));
				try {
					model.connect("172.20.10.3", 1111);
					view.primaryStage.setScene(view.setFirstScene());
					view.primaryStage.setFullScreen(false); /// muss true gesetzt werden
					// primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); //User
					// Information Text after going fullscreen
					view.primaryStage.setResizable(true); // muss false gesetz werden
					view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
					view.primaryStage.setTitle("Majesty Group");
					// model.connect(view.ip.getText(), Integer.parseInt(view.port.getText()));
					view.start();
				} catch (ConnectException e) {
					System.out.println("server not started");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// loginScene buttons
		view.loginB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// ACTIVATE
				model.sendUserLoginMessage(view.userNameLogin.getText(), view.userpasswordLogin.getText());

				// Message loginMessage = new UserLoginMessage(view.userNameLogin.getText(),
				// view.passwordTf.getText());

				// Message.send(model.socket, loginMessage);

				view.primaryStage.setScene(view.setThirdScene());
				view.primaryStage.setFullScreen(false);
				// primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); //User
				// Information Text after going fullscreen
				view.primaryStage.setResizable(true);
				view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
				view.primaryStage.setTitle("Majesty Group");
				view.start();

			}
		});

		view.registrierenB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.setSecondScene());
				view.primaryStage.setFullScreen(false);
				// primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); //User
				// Information Text after going fullscreen
				view.primaryStage.setResizable(false);
				view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
				view.primaryStage.setTitle("Majesty Group");
				view.start();

			}
		});

		// --------------------------

		// registrierenScene buttons
		view.backFirstSceneB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.firstScene);
			}
		});

		view.registrierenBB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				model.sendUserRegisterMessage(view.userNameRegister.getText(), view.userPasswordRegister.getText());
				view.primaryStage.setScene(view.setThirdScene());
				view.start();
			}
		});
		// --------------------------

		// spielStartenScene buttons
		view.spielstartenB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				model.sendGameStartMessage();
				System.out.println("start");
				// view.primaryStage.setScene(view.setFifthScene());
				// view.primaryStage.setFullScreen(false);

			}
		});

		// chatButton
		view.sendButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				model.sendChatMessage(model.myName.getValue(), model.myName.getValue() + ": " + view.chatInput.getText());
				System.out.println(model.myName.getValue() + " said : " + view.chatInput.getText());
			}
		});

		view.einstellungenB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.setFourthScene());

			}
		});

		view.setupSaveB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.setThirdScene());

			}
		});

		view.highscoreB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				model.sendGetHighscoresMessage();
				view.primaryStage.setScene(view.setSixthScene());

			}
		});

		view.logoutB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.firstScene);
			}
		});

		view.spielanleitungB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.setSeventhScene());

			}
		});

		view.backToGameScene.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				view.primaryStage.setScene(view.setThirdScene());

			}
		});

		// -------------------------

		// gameScene buttons
		// @author Yusuf Or & Ozan Firat

		view.buttons[0].setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				System.out.println("b0");
				int getIdOfButton = model.deck.get(0);

				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(0, getIdOfButton);
				} else {
					System.out.println("Split Card");
					view.popUpStage();
					view.getCardIndexOnButtonOfSplitCard(0, getIdOfButton);

				}

			}
		});

		view.buttons[1].setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				if(model.myMeeples.getValue()>0) {
				int getIdOfButton = model.deck.get(1);
				System.out.println("b1");

				if (model.deck.get(1) <= 6) {
					model.sendPlayerMoveMessage(1, getIdOfButton);

				} else {

					System.out.println("Split Card");
					view.popUpStage();
					view.getCardIndexOnButtonOfSplitCard(1, getIdOfButton);

				}

			}else {
				// @TODO Yusuf Popup zu wenig meeples
			}
			}
		});

		view.buttons[2].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if(model.myMeeples.getValue()>1) {
				int getIdOfButton = model.deck.get(2);

				System.out.println("b2");
				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(2, getIdOfButton);
				} else {
					System.out.println("Split Card");
					view.popUpStage();
					view.getCardIndexOnButtonOfSplitCard(2, getIdOfButton);

				}
			}else {
				// @TODO Popup
			}

			}
		});

		view.buttons[3].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if(model.myMeeples.getValue()>2) {
				int getIdOfButton = model.deck.get(3);

				System.out.println("b3");				
				
				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(3, getIdOfButton);
				} else {
					System.out.println("Split Card");
					view.popUpStage();

					view.getCardIndexOnButtonOfSplitCard(3, getIdOfButton);

				}
				} else {
					//TODO Yusuf Meldung
				}

			}
		});

		view.buttons[4].setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				if(model.myMeeples.getValue()>3) {
				int getIdOfButton = model.deck.get(4);



				System.out.println("b4");

				
				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(4, getIdOfButton);
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					view.getCardIndexOnButtonOfSplitCard(4, getIdOfButton);


				}
				}else {
					//TODO Yusuf
				}
			}
		});

		view.buttons[5].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if(model.myMeeples.getValue()>4) {
				int getIdOfButton = model.deck.get(5);

				System.out.println("b5");

				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(5, getIdOfButton); // pos, cardID
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					view.getCardIndexOnButtonOfSplitCard(5, getIdOfButton);
					

				}
				}else {
					//TODO Yusuf
				}

			}
		});

		// -------------------------
		/**
		 * Wird aktiviert, sobald eine registerSuccessMessage eintrifft. Die GUI wird
		 * entsprechend veraendert.
		 * 
		 * @author Rijon
		 */
		// TODO
		model.getRegisterSuccess().addListener(c -> {
			System.out.println(c);
			view.registrierenBB.setText(c.toString());
			// COULD NOT CONNECT
			if (c.toString().contains("COULD_NOT_CONNECT") || c.toString().equals("StringProperty [value: ]")) {

			}
			// REGISTER SUCCESS
			if (c.toString().contains("SUCCESS")) {
				
			}
			// PLAYER ALLREADY EXIST
			if (c.toString().contains("EXIST")) {

			}
		});

		/**
		 * Wird aktiviert, sobald eine loginSuccessMessage eintrifft. Die GUI wird
		 * entsprechend verändert.
		 * 
		 * @author Rijon
		 */
		model.getLoginSuccess().addListener(c -> {
			System.out.println(c);

			// COULD NOT CONNECT
			if (c.toString().contains("COULD_NOT_CONNECT") || c.toString().equals("StringProperty [value: ]")) {

			}
			// WRONG LOGIN
			if (c.toString().contains("WRONG")) {

			}
			// WRONG LOGIN
			if (c.toString().contains("FAILURE")) {

			}
			// LOGIN SUCCESS
			if (c.toString().contains("SUCCESS")) {
				model.myName.set(view.userNameLogin.getText());
				view.primaryStage.setScene(view.setThirdScene());
				view.start();
			}
		});

		model.getHighscoreList().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				view.highscoreListLabel.setText(newValue);
				// TODO rijon: resize window or set fixed size in ClientView because list is always the same height (always 10 players)
			}
		});

		/**
		 * Aktualisiert Buttons.
		 * 
		 * @author Rijon
		 */
		model.deck.addListener((ListChangeListener<Integer>) c -> {
			
			if (!view.primaryStage.getScene().equals(view.fifthScene)) { // setzt nur das erste mal die Fünfte Scene
				view.ANZAHL_GEGNER = model.opponentPlayers.size();
				view.primaryStage.setScene(view.setFifthScene());
				view.primaryStage.setFullScreen(false);
			}
			Platform.runLater(() -> {
				int i = 0;
				for (Button b : view.buttons) {
					b.getStyleClass().clear();
					b.getStyleClass().addAll("gameButton" + c.getList().get(i), "gameButtons");
					// b.getStyleClass().removeAll("gameButton"+model.deck.get(i) ,"gameButtons");
					// b.getStyleClass().addAll("gameButton"+model.deck.get(i) ,"gameButtons");

					i++;
				}
			
			});

			for(int a = 0 ; a < model.opponentPlayers.size(); a++) {
				int b = model.opponentPlayers.get(a).meeples.intValue();
				view.lblMainMeeplesCardArrayList.get(a).getStyleClass().clear();
				view.lblMainMeeplesCardArrayList.get(a).getStyleClass().addAll("meeplesCard", "meeplesCard-"+ b);
			};
			view.lblMainMeeplesCard.getStyleClass().clear();
			view.lblMainMeeplesCard.getStyleClass().addAll("meeplesCard", "meeplesCard-"+ model.myMeeples.getValue());

		});

		model.newestMessage.addListener((o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.chatContent.appendText(newValue + "\n");

		});

		view.primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			model.sendLogoutMessage();

			Platform.setImplicitExit(false);
            Platform.exit();
            System.exit(0);
		});
		
		model.myTurn.addListener((observable, oldValue, newValue) ->{
			if(newValue.booleanValue()==true) {
				for(int i = 0; i< view.buttons.length; i++)
				view.buttons[i].setDisable(false);
			}
			if(newValue.booleanValue()==false) {
				for(int i = 0; i< view.buttons.length; i++)
					view.buttons[i].setDisable(true);
			}
		});
		
		model.meeples.addListener((ListChangeListener<Integer>) c -> {
			Platform.runLater(() -> {
				int i = 0;
				for (Label l : view.meeplesDeck) {
					l.setText(Integer.toString(c.getList().get(i)));
					//l.setTextFill(Color.web("#FFFFFF"));
					if(c.getList().get(i)!=0) {
						
						l.getStyleClass().add("meeplesCounter");
					} 
					if(c.getList().get(i)==0) {
						
						l.getStyleClass().clear();
					} 
					i++;
				}
			});
			
		});
		
		model.winners.addListener((ListChangeListener<String>) c -> {
			view.winnerStage(model.winnerList);

		});
		
	}
		

}
