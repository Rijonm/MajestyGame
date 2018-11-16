package Model;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
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
public class Client implements Serializable {
	
	private ServerModel model;
	private Socket socket;
	private UserRegisterMessage registerMessage;
	private UserLoginMessage loginMessage;
	private LoggedInPlayers loggedInPlayersMessage;

    private int id;
    
    private String userName;
    private String password;
    private Hand hand;
    
    private int points;
    
    public Client(ServerModel model, Socket socket){
    	this.model = model;
    	this.socket = socket;
    	Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						
						Message msg = Message.receive(socket);
						
						if(msg.getMessageType() == MessageType.UserRegisterMessage) {
							registerMessage = (UserRegisterMessage) msg;
							registerResponse(registerRequest(registerMessage));
						}
						if (msg.getMessageType() == MessageType.UserLoginMessage) {
							loginMessage = (UserLoginMessage) msg;
							loginResponse(loginRequest(loginMessage));
							//Player muss hier instanziiert werden.
						}
						
						
						if(msg.getMessageType() == MessageType.UserLogout) {
							loggedInPlayersMessage = (LoggedInPlayers) msg;
							logoutRequest();
							logoutResponse();
						}
						if(msg.getMessageType() == MessageType.GameStartMessage) {
							
						}
						if(msg.getMessageType() == MessageType.PlayerMoveMessage) {
							
						}
						if(msg.getMessageType() == MessageType.ChatMessage) {
							
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
    		Message.send(this.socket, message);
    }
	
	protected LoginSuccessMessage loginRequest(UserLoginMessage message) {
		return model.db.login(message);
	}
    
	/**
	 * Sendet Login-Antwort an Client.
	 * 
	 * @author Rijon
	 * @param message
	 */
    public void loginResponse(LoginSuccessMessage message) {
    		Message.send(this.socket, message);
    }
    
    protected void logoutRequest() {
		// TODO Auto-generated method stub
		
	}
    
    protected void logoutResponse() {
		// TODO Auto-generated method stub
		
	}

	
}
