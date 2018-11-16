package Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserRegisterMessage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
/**
 * Die Klasse ClientModel handelt alle eingehenden sowie ausgehenden Messages, die durch den User durch interaktion ausgelöst wurden.
 * 
 * @author rijon
 *
 */
public class ClientModel {
	
	public Socket socket;
	public SimpleStringProperty registerSuccessString = new SimpleStringProperty();
	public SimpleStringProperty loginSuccessString = new SimpleStringProperty();
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
							if(msg.getMessageType() == MessageType.LobbyInformationMessage) {
								Platform.runLater(() ->{
								receivedLobbyInformationMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.FirstSixCardsMessage) {
								Platform.runLater(() ->{
								receivedFirstSixCardsMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.PlayerMoveMessage) {
								Platform.runLater(() ->{
								receivedPlayerMoveMessage(msg);
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

	protected void receivedPlayerMoveMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	protected void receivedFirstSixCardsMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	protected void receivedLobbyInformationMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	protected void receivedLoginSuccessMessage(Message msg) {
		LoginSuccessMessage lsm =(LoginSuccessMessage) msg;
		loginSuccessString.set("");
		loginSuccessString.set(lsm.getState().toString());
		
		
	}

	protected void receivedRegisterSuccessMessage(Message msg) {
		RegisterSuccessMessage rsm = (RegisterSuccessMessage) msg;
		registerSuccessString.set("");
		registerSuccessString.set((String)rsm.getState().toString());
	}
	
	/**
	*Schickt eine UserRegisterMessage an den Server, nachdem "Registireren" angeklickt wurde.
	*
	*@author Rijon
	*/
	public void sendUserRegisterMessage(String username, String passwort) {
		Message registerMessage = new UserRegisterMessage(username, passwort);
		Message.send(this.socket, registerMessage);
	}
	
	public void sendUserLoginMessage(String username, String passwort) {
		Message loginMessage = new UserLoginMessage(username, passwort);
		Message.send(this.socket, loginMessage);
	}
	
	public void sendGameStartMessage() {
		
	}
	
	public void sendPlayerMoveMessage() {
		
	}
	
	public void sendChatMessage() {
		
	}
	
	public void disconnect() {
		
	}
}