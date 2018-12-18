package Model;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CommonClasses.ChatMessage;
import CommonClasses.FirstSixCardsMessage;
import CommonClasses.GameStartMessage;
import CommonClasses.InformationFromServerMessage;
import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.OpponentPlayerMessage;
import CommonClasses.Player;
import CommonClasses.PlayerMoveMessage;
import CommonClasses.PlayerStatesMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
/**
 * Die Klasse ClientModel handelt alle eingehenden sowie ausgehenden Messages, die durch den User durch interaktion ausgelöst wurden.
 * 
 * @author rijon
 *
 */
public class ClientModel {
	
	private int id; // Wird gesetzt, nachdem sich der Client erfolgreich eingeloggt hat. Entsprich der id in der DB.
	public SimpleStringProperty myName = new SimpleStringProperty();
	public SimpleIntegerProperty myCoins = new SimpleIntegerProperty();
	public SimpleIntegerProperty myMeeples = new SimpleIntegerProperty();
	public ObservableIntegerArray myHand = FXCollections.observableIntegerArray();
	public Socket socket;
	public ObjectInputStream oips;
	public ObjectOutputStream oops;
	private SimpleStringProperty registerSuccessString = new SimpleStringProperty();
	private SimpleStringProperty loginSuccessString = new SimpleStringProperty();
	public SimpleStringProperty newestMessage = new SimpleStringProperty();
	private ObservableList<String> lobbyPlayers = FXCollections.observableArrayList();
	public ObservableList<Opponent> opponentPlayers = FXCollections.observableArrayList(); // Opponents: id, name, coins,  meeples, hand, wonOrLose
	public ObservableList<Integer> deck = FXCollections.observableArrayList();
	private ObservableMap<String, Integer> map = FXCollections.observableHashMap();
	
	public ClientModel() {
		
	}
	
	public void connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			oops = new ObjectOutputStream(socket.getOutputStream());
			oips = new ObjectInputStream(socket.getInputStream());
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (true) {
						
						try {
							Message msg = receive();
							
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
							if(msg.getMessageType() == MessageType.OpponentPlayerMessage) {
								Platform.runLater(() ->{
								receivedOpponentPlayers(msg);
								});
							}
							//Erst wenn die 6 karten eintreffen muss ein trigger die spielScene starten
							//FERTIG
							if(msg.getMessageType() == MessageType.FirstSixCardsMessage) {
								Platform.runLater(() ->{
								receivedFirstSixCardsMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.InformationFromServerMessage) {
								Platform.runLater(() ->{
								receivedInformationFromServerMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.PlayerStatesMessage) {
								Platform.runLater(() ->{
									receivedPlayerStatesMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.EvaluateGameMessage) {
								Platform.runLater(() ->{
								receivedEvaluateGameMessage(msg);
								});
							}
							//FERTIG
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//RECEIVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE Start-------------
	protected void receivedChatMessage(Message msg) {
		ChatMessage cm = (ChatMessage) msg;
		newestMessage.set(cm.getMessage());
		
	}

	protected void receivedEvaluateGameMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}
	
	protected void receivedPlayerStatesMessage(Message msg) {
		PlayerStatesMessage psm = (PlayerStatesMessage) msg;
		int[] intHand = Arrays.stream(psm.getHand()).mapToInt(Integer::intValue).toArray();
		if(psm.getId() == this.id) {
			System.out.println(psm.getId() + "ICH");
			myHand.setAll(intHand);
			myCoins.set(psm.getCoins());
			myMeeples.set(psm.getMeeples());
			System.out.println(Arrays.toString(psm.getHand()));
			System.out.println(psm.getCoins());
			System.out.println(psm.getMeeples());
		}else {
			System.out.println(psm.getId() + "ER");
			System.out.println(Arrays.toString(psm.getHand()));
		}
		
	}

	protected void receivedInformationFromServerMessage(Message msg) {
		InformationFromServerMessage ifsm = (InformationFromServerMessage) msg;
		System.out.println(Arrays.toString(ifsm.getOpenCards())+ "OPEN CARDS");
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(ifsm.getOpenCards()));
		deck.clear();
		deck.addAll(list);
	}
	/*
	 * Aktualisiert deck, trigger in contoller
	 */
	protected void receivedFirstSixCardsMessage(Message msg) {
		FirstSixCardsMessage fscm = (FirstSixCardsMessage) msg;
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(fscm.getFirstSixCards()));
		deck.clear();
		deck.addAll(list);
		
	}
	/*
	 * Gets every opponent from the server
	 */
	protected void receivedOpponentPlayers(Message msg) {
		OpponentPlayerMessage opm = (OpponentPlayerMessage) msg;
		if(opm.getId()!=this.id) { //Da server immer die ganze Spieler Array durchgeht und allen sendet, muss analysiert werden ob es sich dabei um die eigene ID handelt, falls nicht dann als Oppenent einfügen.
			Opponent opponent = new Opponent(opm.getId(), opm.getUsername(), opm.getHand(), opm.getMeeples(), opm.getCoins());
			opponentPlayers.add(opponent);
		}
		
	}
	
	/*
	 * Alle Spieler die online sind werden in der ObservableList eingefügt.
	 * 
	 * @author Rijon
	 */
	protected void receivedLoggedInPlayersMessage(Message msg) {
		//Leert lobbyPlayers um die Liste zu aktualisieren
//		if(!lobbyPlayers.isEmpty()) 
//			for(Player c : lobbyPlayers) {
//				lobbyPlayers.remove(c);
//		}
		LoggedInPlayers lip = (LoggedInPlayers) msg;
		lobbyPlayers.clear();
		//lobbyPlayers.addAll(lip.getPlayers());
//		if(!lobbyPlayers.isEmpty()) 
//			for(Player c : lobbyPlayers) {
//				lobbyPlayers.remove(c);
//			}
		for(Player p: lip.getPlayers()) {
			lobbyPlayers.add(p.getUsername());
		}
//		lobbyPlayers.addAll(lip.getPlayers());
//			for(Player c : lip.getPlayers()) {
//				lobbyPlayers.add(c);
//				System.out.println(c.getUsername());
//		}
		
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
	//RECEIVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE END-------------
	
	/**
	*Schickt eine UserRegisterMessage an den Server, nachdem "Registireren" angeklickt wurde.
	*
	*@author Rijon
	*@param username
	*@param passwort
	*/
	public void sendUserRegisterMessage(String username, String passwort) {
		Message registerMessage = new UserRegisterMessage(username, passwort);
		send(registerMessage);
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
		send(loginMessage);
	}
	/**
	 * Schickt eine GameStartMessage an den Server, nachdem der Gegenspieler ausgewählt wurde.
	 * 
	 * @author Rijon
	 * @TODO Mehrere Spieler
	 * @param name
	 */
	public void sendGameStartMessage() {
		Message gameStartMessage = new GameStartMessage();
		send(gameStartMessage);
	}
	
	/**
	 * Schickt eine PlayerMoveMessage an den Server, nachdem ein Spielzug getätigt wurde.
	 * 
	 * @author Rijon
	 * @param i
	 */
	public void sendPlayerMoveMessage(int pos, int cardid) {
		Message playerMoveMessage = new PlayerMoveMessage(pos, cardid, id);
		send(playerMoveMessage);
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
		send(chatMessage);
	}
	
	public void sendLogoutMessage() {
		Message logoutMessage = new UserLogout(id);
		send(logoutMessage);
	}
	//Addlistener in controll for lobby
	public ObservableList<String> getLobbyPlayers() {
		return lobbyPlayers;
	}
	
	//Addlistner in controll for registerinfo
	public SimpleStringProperty getRegisterSuccess() {
		return registerSuccessString;
	}
	//Addlistener in contoll for logininfo
	public SimpleStringProperty getLoginSuccess() {
		return loginSuccessString;
	}
	
	public void send(Message message) {
		try {
			
			oops.writeObject(message);
			oops.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Message receive() throws IOException, ClassNotFoundException, EOFException {
		
		Message message = (Message) oips.readObject();
		return message;
		
	}
	
	public class Opponent{
		public SimpleIntegerProperty id = new SimpleIntegerProperty();
		public SimpleStringProperty name = new SimpleStringProperty();
		public SimpleIntegerProperty coins = new SimpleIntegerProperty();
		public SimpleIntegerProperty meeples = new SimpleIntegerProperty();
		public ObservableIntegerArray hand = FXCollections.observableIntegerArray();
		public SimpleStringProperty wonOrLose = new SimpleStringProperty();
		
		public Opponent(int id, String name, Integer hand[], int meeples, int coins) {
			this.id.set(id);
			this.name.set(name);
			this.meeples.set(meeples);
			int[] intHand = Arrays.stream(hand).mapToInt(Integer::intValue).toArray();
			this.hand.setAll(intHand);
			this.coins.setValue(coins); //Nicht sicher ob set oder setValue
			
		}
		
	}

	
	
}

