package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import CommonClasses.ChatMessage;
import CommonClasses.GameStartMessage;
import CommonClasses.HighscoreListMessage;
import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.OtherPlayerLoggedOutMessage;
import CommonClasses.PlayerMessage;
import CommonClasses.PlayerMoveMessage;
import CommonClasses.PlayersMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;
import javafx.application.Platform;

/**
 * Wenn ein neuer Client gestartet wird und der sich mit dem Server Connected
 * wird ein neuer Client-Objekt instanziiert.
 *
 * Diese Klasse handelt alle eingehenden Messages und sendet die Antwort an dem
 * entsprechenden Client.
 *
 * @author Rijon
 *
 */
public class Client {

	private ServerModel model;
	public Socket socket;
	private ObjectInputStream oips;
	private ObjectOutputStream oops;
	private UserRegisterMessage registerMessage;
	private UserLoginMessage loginMessage;
	private LoggedInPlayers loggedInPlayersMessage;
	private GameStartMessage gameStartMessage;
	private PlayerMoveMessage playerMoveMessage;
	private ChatMessage chatMessage;
	private UserLogout logoutMessage;
	private OtherPlayerLoggedOutMessage otherPlayerLoggedOutMessage;

	private int id;
	private String username;
	private String password;
	private int highscore; // noch nicht definiert
	private boolean isOnline; // wird auf true gesetzt, falls der Login erfolgreich war
	private boolean isInGame; // wird auf true gesetzt, bei allen spielern in der Lobby(max 4), nachdem der
								// einte Spieler auf SpielStarten klickt
	private Hand hand = new Hand();; // Jeder Player hat eine Hand, die beim Spielstart erzeugt wird. Startwerte
										// 0,0,0,0,0,0,0,0

	private int coins = 0;
	private int meeples = 5;

	private int points;

	public Client(ServerModel model, Socket socket) {
		this.model = model;
		this.socket = socket;
		try {
			oops = new ObjectOutputStream(socket.getOutputStream());
			oips = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {

						Message msg = receive();
						// REGISTRIEREN
						if (msg.getMessageType() == MessageType.UserRegisterMessage) {
							System.out.println("registertmessagereceived");
							registerMessage = (UserRegisterMessage) msg;
							registerResponse(registerRequest(registerMessage));
						}
						// EINLOGGEN
						if (msg.getMessageType() == MessageType.UserLoginMessage) {
							System.out.println("einloggenmessagereceived");
							loginMessage = (UserLoginMessage) msg;
							loginResponse(loginRequest(loginMessage));

						}

						// GAME STARTEN
						if (msg.getMessageType() == MessageType.GameStartMessage) {
							System.out.println("gamestartmessagereceived");
							gameStartMessage = (GameStartMessage) msg;
							model.startGame();
						}
						// IM SPIEL
						if (msg.getMessageType() == MessageType.PlayerMoveMessage) {
							playerMoveMessage = (PlayerMoveMessage) msg;
							model.getGame().receivedPosFromClient(playerMoveMessage.getPositionPlayed(),
									playerMoveMessage.getCardID(), Client.this.id);
						}
						// CHAT
						if (msg.getMessageType() == MessageType.ChatMessage) {
							chatMessage = (ChatMessage) msg;
							model.broatcastToPlayerInGame(chatMessage);
						}

						// AUSLOGGEN
						if (msg.getMessageType() == MessageType.UserLogout) {
							logoutMessage = (UserLogout) msg;
							model.db.logout(logoutMessage);
							sendPlayerLoggedOut(logoutMessage.getPlayerId());
							sendLoggedInPlayers();
						}
						// logout of other player
						if (msg.getMessageType() == MessageType.OtherPlayerLoggedOutMessage) {
							otherPlayerLoggedOutMessage = (OtherPlayerLoggedOutMessage) msg;
								sendOtherPlayerLoggedOut(otherPlayerLoggedOutMessage);
						}
						// highscores requested
						if (msg.getMessageType() == MessageType.GetHighScoresMessage) {
							// get top 10 playes sorted by highscore
							PlayersMessage playersMessage = model.db.getPlayers("highscore", false, 10);
							HighscoreListMessage highscoreListMessage = new HighscoreListMessage(
									playersMessage.getPlayers());
							sendHighscoreListMessage(highscoreListMessage);
						}

					}
				} catch (SocketException e) {
					System.out.println("connection reset");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(r);
		t.start();

	}

	/**
	 * User wird in der DB registriert und gibt einen Status mithilfe der
	 * RegisterSuccessMessage zurück.
	 */
	protected RegisterSuccessMessage registerRequest(UserRegisterMessage message) {
		return model.db.register(message);
	}

	/**
	 * Dem Client wird ein RegisterSuccessMessage zugesendet.
	 */
	public void registerResponse(RegisterSuccessMessage message) {
		send(message);
	}

	/**
	 * Handelt den eingehenden Loginrequest, indem in der DB nachschaut wird.
	 * 
	 * @param message
	 * @return
	 */
	protected LoginSuccessMessage loginRequest(UserLoginMessage message) {
		return model.db.login(message);
	}

	/**
	 * Sendet Login-Antwort an Client. Speichert ID, falls Anmeldung erfolgreich war
	 * und fügt Spieler in die ObservableList PlayerOnline.
	 * 
	 * @author Rijon
	 * @param message
	 */
	public void loginResponse(LoginSuccessMessage message) {
		send(message);
		if (message.getState() == LoginSuccessMessage.State.SUCCESS) {
			id = message.getId();
			username = loginMessage.getUsername();
			isOnline = true;
			// model.playeronline.add(new PlayerOnline(model, socket, id, username));
			sendLoggedInPlayers();
		}

	}

	/*
	 * Sendet beim erfolgreichen einloggen die LobbyInofrmationMessage an alle
	 * eingeloggten Spieler. Wird von loginResponse aufgerufen.
	 * 
	 * @author Rijon
	 */
	public void sendLoggedInPlayers() {
		LoggedInPlayers message = model.db.getLoggedInPlayers();
		model.broadcast(message);
	}

	public void sendPlayerLoggedOut(int playerId) {
		PlayerMessage message = model.db.getPlayer(playerId);
		model.broadcast(message);
	}

	public void sendOtherPlayerLoggedOut(OtherPlayerLoggedOutMessage msg) {
		model.broadcast(msg);
	}

	public void sendHighscoreListMessage(HighscoreListMessage msg) {
		System.out.println("server/client sendHighscoreListMessage");
		send(msg);
	}

	public ServerModel getModel() {
		return this.model;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public boolean isInGame() {
		return isInGame;
	}

	public void setIsOnline(boolean b) {
		isOnline = b;
	}

	public void setIsInGame(boolean b) {
		isInGame = b;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Hand getHand() {
		return hand;
	}

	public int getMeeples() {
		return meeples;
	}

	public void send(Message message) {
		try {
			if (!socket.isClosed()) {
				oops.writeObject(message);
				oops.flush();
			} else {
				System.out.println("socket: " + socket.toString() + " is closed");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Message receive() throws IOException, ClassNotFoundException, SocketException {
		Message message = (Message) oips.readObject();
		return message;

	}

	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
