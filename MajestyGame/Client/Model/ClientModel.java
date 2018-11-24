package Model;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;


import CommonClasses.CardFromServerMessage;
import CommonClasses.ChatMessage;
import CommonClasses.FirstSixCardsMessage;
import CommonClasses.GameStartMessage;
import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.Player;
import CommonClasses.PlayerMoveMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserRegisterMessage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
/**
 * Die Klasse ClientModel handelt alle eingehenden sowie ausgehenden Messages, die durch den User durch interaktion ausgelöst wurden.
 * 
 * @author rijon
 *
 */
public class ClientModel {
	
	private int id;
	public Socket socket;
	private SimpleStringProperty registerSuccessString = new SimpleStringProperty();
	private SimpleStringProperty loginSuccessString = new SimpleStringProperty();
	private ObservableList<Player> lobbyPlayers = FXCollections.observableArrayList();
	private ObservableList<Integer> deck = FXCollections.observableArrayList();
	private ObservableMap<String, Integer> map = FXCollections.observableHashMap();
	
	public ClientModel() {
		
	}
	
	public void connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (true) {
						
						try {
							Message msg = Message.receive(socket);
							
							//FERTIG
							if(msg.getMessageType() == MessageType.RegisterSuccessMessage) {
								Platform.runLater(() ->{
								receivedRegisterSuccessMessage(msg);
								System.out.println("RegisterSuccessMessage");
								});
							}
							//FERTIG
							if(msg.getMessageType() == MessageType.LoginSuccessMessage) {
								Platform.runLater(() ->{
								receivedLoginSuccessMessage(msg);
								System.out.println("LoginSuccessMessage");
								});
							}
							//FERTIG
							if(msg.getMessageType() == MessageType.LoggedInPlayers) {
								Platform.runLater(() ->{
								receivedLoggedInPlayersMessage(msg);
								});
							}
							//Erst wenn die 6 karten eintreffen muss ein trigger die spielScene starten
							if(msg.getMessageType() == MessageType.FirstSixCardsMessage) {
								Platform.runLater(() ->{
								receivedFirstSixCardsMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.CardFromServerMessage) {
								Platform.runLater(() ->{
								receivedCardFromServerMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.EvaluateGameMessage) {
								Platform.runLater(() ->{
								receivedEvaluateGameMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.ChatMessage) {
								Platform.runLater(() ->{
								receivedChatMessage(msg);
								});
							}
							
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			};
			Thread t = new Thread(r);
			t.start();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	protected void receivedChatMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	protected void receivedEvaluateGameMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	protected void receivedCardFromServerMessage(Message msg) {
		CardFromServerMessage cfsm = (CardFromServerMessage) msg;
		
	}
	/*
	 * Aktualisiert deck, trigger in contoller
	 */
	protected void receivedFirstSixCardsMessage(Message msg) {
		FirstSixCardsMessage fscm = (FirstSixCardsMessage) msg;
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(fscm.getFirstSixCards()));
		
		deck = FXCollections.observableArrayList(list);
		
		
	}
	
	/*
	 * Alle Spieler die online sind werden in der ObservableList eingefügt.
	 * 
	 * @author Rijon
	 */
	protected void receivedLoggedInPlayersMessage(Message msg) {
		//Leert lobbyPlayers um die Liste zu aktualisieren
		if(!lobbyPlayers.isEmpty()) 
			for(Player c : lobbyPlayers) {
				lobbyPlayers.remove(c);
		}
		LoggedInPlayers lip = (LoggedInPlayers) msg;
			for(Player c : lip.getPlayers()) {
				lobbyPlayers.add(c);
		}
		
	}
	/**
	 * Wenn eine RegisterSuccessMessage eintrifft, wird der Status in die ObervableList eingefügt und durch die addlistenermethode im Controller im View dem Client angezeigt.
	 * @param msg
	 */
	protected void receivedLoginSuccessMessage(Message msg) {
		LoginSuccessMessage lsm =(LoginSuccessMessage) msg;
		if(lsm.getState().equals(LoginSuccessMessage.State.SUCCESS)) {
		id =lsm.getId();
		}
		loginSuccessString.set("");
		loginSuccessString.set(lsm.getState().toString());
		
		
	}
	/**
	 * Wenn eine RegisterSuccessMessage eintrifft, wird der Status in die ObervableList eingefügt und durch die addlistenermethode im Controller im View dem Client angezeigt.
	 * @param msg
	 */
	protected void receivedRegisterSuccessMessage(Message msg) {
		RegisterSuccessMessage rsm = (RegisterSuccessMessage) msg;
		registerSuccessString.set("");
		registerSuccessString.set(rsm.getState().toString());
	}
	
	/**
	*Schickt eine UserRegisterMessage an den Server, nachdem "Registireren" angeklickt wurde.
	*
	*@author Rijon
	*@param username
	*@param passwort
	*/
	public void sendUserRegisterMessage(String username, String passwort) {
		Message registerMessage = new UserRegisterMessage(username, passwort);
		Message.send(this.socket, registerMessage);
	}
	/**
	 * Schickt eine UserLoginMessage an den Server, nachdem "Login" angelickt wurde.
	 * 
	 * @author Rijon
	 * @param username
	 * @param passwort
	 */
	public void sendUserLoginMessage(String username, String passwort) {
		Message loginMessage = new UserLoginMessage(username, passwort);
		Message.send(this.socket, loginMessage);
	}
	/**
	 * Schickt eine GameStartMessage an den Server, nachdem der Gegenspieler ausgewählt wurde.
	 * 
	 * @author Rijon
	 * @TODO Mehrere Spieler
	 * @param name
	 */
	public void sendGameStartMessage(String name) {
		Message gameStartMessage = new GameStartMessage(name);
		Message.send(this.socket, gameStartMessage);
	}
	
	/**
	 * Schickt eine PlayerMoveMessage an den Server, nachdem ein Spielzug getätigt wurde.
	 * 
	 * @author Rijon
	 * @param i
	 */
	public void sendPlayerMoveMessage(int i) {
		Message playerMoveMessage = new PlayerMoveMessage(i);
		Message.send(this.socket, playerMoveMessage);
	}
	
	/**
	 * Schickt eine ChatMessage an den Server, nachdem jemand im Chat auf "Senden" klickt.
	 * 
	 * @author Rijon
	 * @param name
	 * @param message
	 */
	public void sendChatMessage(String name, String message) {
		Message chatMessage = new ChatMessage(name, message);
		Message.send(this.socket, chatMessage);
	}
	
	public void disconnect() {
		
	}
	//Addlistener in controll for lobby
	public ObservableList<Player> getLobbyPlayers() {
		return lobbyPlayers;
	}
	//Addlistener in controll for game
	public ObservableList<Integer> getFirstSixCards() {
		return deck;
	}
	//Addlistner in controll for registerinfo
	public SimpleStringProperty getRegisterSuccess() {
		return registerSuccessString;
	}
	//Addlistener in contoll for logininfo
	public SimpleStringProperty getLoginSuccess() {
		return loginSuccessString;
	}
	
}