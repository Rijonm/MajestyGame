package Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import CommonClasses.ChatMessage;
import CommonClasses.GameStartMessage;
import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.PlayerMoveMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserRegisterMessage;
import CommonClasses.LoginSuccessMessage.State;
/**
 *Wenn ein neuer Client gestartet wird und der sich mit dem Server Connected wird ein neuer Client-Objekt
 *instanziiert.
 *
 *Diese Klasse handelt alle eingehenden Messages und sendet die Antwort an dem entsprechenden Client.
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

    private int id;
    private String username;
    private String password;
    private int highscore; //noch nicht definiert
    private boolean isOnline; //wird auf true gesetzt, falls der Login erfolgreich war
    private boolean isInGame; //wird auf true gesetzt, bei allen spielern in der Lobby(max 4), nachdem der einte Spieler auf SpielStarten klickt
    private Hand hand; //Jeder Player hat eine Hand, die beim Spielstart erzeugt wird. Startwerte 0,0,0,0,0,0,0,0

	private int coins = 0;
    private int meeples = 5;
    
    
    private int points;
    
    public Client(ServerModel model, Socket socket){
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
				while(true) {
					try {
						
						Message msg = receive();
						// REGISTRIEREN
						if(msg.getMessageType() == MessageType.UserRegisterMessage) {
							System.out.println("registertmessagereceived");
							registerMessage = (UserRegisterMessage) msg;
							registerResponse(registerRequest(registerMessage));
						}
						// EINLOGGEN
						if (msg.getMessageType() == MessageType.UserLoginMessage) {
							System.out.println("einloggenmessagereceived");
							loginMessage = (UserLoginMessage) msg;
							loginResponse(loginRequest(loginMessage));
							//Test ob alle Player die sich einloggen ausgegen werden.
							for(PlayerOnline p : model.playeronline) {
							System.out.println(p.getUsername());
							}
						}
						
						// GAME STARTEN
						if(msg.getMessageType() == MessageType.GameStartMessage) {
							System.out.println("gamestartmessagereceived");
							gameStartMessage = (GameStartMessage) msg;
							isInGame = true; //defines, that this player is in game
							hand = new Hand();
//							for(Client c : model.clients) {
//								if(c.username.equals("rijon")) {
//									c.isInGame = true;
//								};
//							}	
							
							//model.playeringame.add(new PlayerInGame(Client.this.model, Client.this.socket, Client.this.id, Client.this.username)); //Der Spieler der GameStart angeklickt hat wird eingefügt.
						//model.broadcast(gameStartMessage);
//							for(PlayerOnline p : model.playeronline)
//								if(p.getUsername().equals(gameStartMessage.getPlayerName())) {
//									model.playeringame.add(new PlayerInGame(p.getModel(), p.getSocket(), p.getId(), p.getUsername())); //Der Gegner wird in PlayerInGame eingefügt
//									break;
//								}
							model.startGame();
						}
						// IM SPIEL
						if(msg.getMessageType() == MessageType.PlayerMoveMessage) {
							playerMoveMessage = (PlayerMoveMessage) msg;
							model.getGame().receivedPosFromClient(playerMoveMessage.getPositionPlayed(), Client.this.id);
						}
						// CHAT
						if(msg.getMessageType() == MessageType.ChatMessage) {
							chatMessage = (ChatMessage) msg;
							model.broatcastToPlayerInGame(chatMessage);
						}
						
						// AUSLOGGEN
						if(msg.getMessageType() == MessageType.UserLogout) {
							loggedInPlayersMessage = (LoggedInPlayers) msg;
							logoutRequest();
							logoutResponse();
						}
						
						
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
    	
    }
    
    
	/**
     * User wird in der DB registriert und gibt einen Status mithilfe der RegisterSuccessMessage zurück.
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
	 * @param message
	 * @return
	 */
	protected LoginSuccessMessage loginRequest(UserLoginMessage message) {
		return model.db.login(message);
	}
    
	/**
	 * Sendet Login-Antwort an Client.
	 * Speichert ID, falls Anmeldung erfolgreich war und fügt Spieler in die ObservableList PlayerOnline.
	 * 
	 * @author Rijon
	 * @param message
	 */
    public void loginResponse(LoginSuccessMessage message) {
    		send(message);	
    		if(message.getState() == LoginSuccessMessage.State.SUCCESS) {
    			id = message.getId();
    			username = loginMessage.getUsername();
    			isOnline = true;
    			//model.playeronline.add(new PlayerOnline(model, socket, id, username));
    			sendLoggedInPlayers();
    		}
    		
    		
    }
    /*
     * Sendet beim erfolgreichen einloggen die LobbyInofrmationMessage an alle eingeloggten Spieler.
     * Wird von loginResponse aufgerufen.
     * 
     * @author Rijon
     */
    public void sendLoggedInPlayers() {
    		LoggedInPlayers message = model.db.getLoggedInPlayers();
    		model.broadcast(message);
    }
    
    protected void logoutRequest() {
		// TODO Auto-generated method stub
		
	}
    
    protected void logoutResponse() {
		// TODO Auto-generated method stub
		
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
    
    public int getId() {
    		return id;
    }
    
    public Hand getHand() {
		return hand;
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
	
	public Message receive() throws IOException, ClassNotFoundException {
		Message message = (Message) oips.readObject();
		System.out.println("message");
		return message;
		
	}

	
}
