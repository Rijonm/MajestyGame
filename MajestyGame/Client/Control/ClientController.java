package Control;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import Model.ClientModel;
import View.ClientView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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
					model.connect("localhost", 1111);
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
				model.sendChatMessage("rijon", view.chatInput.getText());
				view.chatContent.setText(view.chatInput.getText());
				System.out.println(view.chatInput.getText());
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
				// view.buildingCounter.setText(Integer.toString(model.deck.get(1)));
				int getIdOfButton = model.deck.get(0);

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				if (getIdOfButton <= 6) {
					model.sendPlayerMoveMessage(0, getIdOfButton);

					view.buildingAry[model.deck.get(0)].setText(Integer.toString(labelContent + 1)); // in setText kommt
																										// Array-Nachricht
																										// vom Server
																										// rein
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 0);
							view.popUpStage.close();

						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(0, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 5);
							view.popUpStage.close();
						});
						break;
					case 16:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(0, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");

						break;
					}

				}

			}
		});

		view.buttons[1].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				int getIdOfButton = model.deck.get(1);
				System.out.println("b1");

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				if (model.deck.get(1) <= 6) {
					model.sendPlayerMoveMessage(1, getIdOfButton);

					view.buildingAry[getIdOfButton].setText(Integer.toString(labelContent + 1)); // in setText kommt
																									// Array-Nachricht
																									// vom Server rein
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(0, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 5);
							view.popUpStage.close();
						});
						break;
					case 16:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(1, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");
						break;
					}

				}

			}
		});

		view.buttons[2].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				int getIdOfButton = model.deck.get(2);

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				System.out.println("b2");
				if (getIdOfButton <= 6) {
					view.buildingAry[getIdOfButton].setText(Integer.toString(labelContent + 1)); // in setText kommt
																									// Array-Nachricht
																									// vom Server rein

					model.sendPlayerMoveMessage(2, getIdOfButton);
				} else {
					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(0, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 5);
							view.popUpStage.close();
						});
						break;
					case 16:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(2, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");
						break;
					}

				}

			}
		});

		view.buttons[3].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				int getIdOfButton = model.deck.get(3);

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				System.out.println("b3");
				if (getIdOfButton <= 6) {
					view.buildingAry[getIdOfButton].setText(Integer.toString(labelContent + 1)); // in setText kommt
																									// Array-Nachricht
																									// vom Server rein

					model.sendPlayerMoveMessage(3, getIdOfButton);
				} else {
					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(0, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 5);
							view.popUpStage.close();
						});
						break;
					case 16:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(3, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");
						break;
					}

				}

			}
		});

		view.buttons[4].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				int getIdOfButton = model.deck.get(4);

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				System.out.println("b4");
				model.sendPlayerMoveMessage(4, 4);

				if (getIdOfButton <= 6) {
					view.buildingAry[getIdOfButton].setText(Integer.toString(labelContent + 1)); // in setText kommt
																									// Array-Nachricht
																									// vom Server rein
																									// TEST

					model.sendPlayerMoveMessage(3, getIdOfButton);
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(0, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 5);
							view.popUpStage.close();
						});

						break;
					case 16:
						// view.buildingAry[2].setText(Integer.toString(labelContent+1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 3);
							view.popUpStage.close();

						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(4, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");
						break;
					}

				}

			}
		});

		view.buttons[5].setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				int getIdOfButton = model.deck.get(5);

				String contentLabel = view.buildingCounter.getText();
				int labelContent = Integer.parseInt(contentLabel);

				System.out.println("b5");
				model.sendPlayerMoveMessage(5, getIdOfButton); // pos, cardID

				if (getIdOfButton <= 6) {
					view.buildingAry[model.deck.get(5)].setText(Integer.toString(labelContent + 1)); // in setText kommt
																										// Array-Nachricht
																										// vom Server
																										// rein

					model.sendPlayerMoveMessage(5, getIdOfButton); // pos, cardID
				} else {

					System.out.println("Split Card");
					view.popUpStage();

					switch (getIdOfButton) {
					case 7:
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton1");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 1);
							view.popUpStage.close();
						});

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));

						// Popup mit 2 Buttons
						//
						// ....return getChoose
						// model.sendPlayerMoveMessage(5, 2);
						break;
					case 8:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton0");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 0);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 4);
							view.popUpStage.close();
						});
						break;
					case 9:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton2");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 2);
							view.popUpStage.close();
						});
						break;
					case 10:

						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton1");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 1);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 4);
							view.popUpStage.close();
						});
						break;
					case 11:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton3");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 3);
							view.popUpStage.close();
						});
						break;
					case 12:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 5);
							view.popUpStage.close();
						});
						break;
					case 13:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton2");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 2);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 6);
							view.popUpStage.close();
						});
						break;
					case 14:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton4");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 4);
							view.popUpStage.close();
						});
						break;
					case 15:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 3);
							view.popUpStage.close();

						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 5);
							view.popUpStage.close();
						});
						break;
					case 16:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton3");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 3);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 6);
							view.popUpStage.close();
						});
						break;
					case 17:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton4");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 4);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton5");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 5);
							view.popUpStage.close();
						});
						break;
					case 18:
						view.buildingAry[2].setText(Integer.toString(labelContent + 1));
						view.pb1.getStyleClass().add("gameButton5");
						view.pb1.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 5);
							view.popUpStage.close();
						});

						view.pb2.getStyleClass().add("gameButton6");
						view.pb2.setOnAction(e -> {
							model.sendPlayerMoveMessage(5, 6);
							view.popUpStage.close();
						});
						break;
					default:
						System.out.println("FAILURE CARD INDEX NOD FOUND!");
						break;
					}

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

			// 2)Set new Cards
			// for(int i = 0; i<= buttons.length; i++) {
			// buttons[i].setText("" + model.getFirstSixCards().get(i));
			// }

		});

		model.newestMessage.addListener((o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.chatContent.appendText(newValue + "\n");

		});

		view.primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			model.sendLogoutMessage();
		});
	}

}
