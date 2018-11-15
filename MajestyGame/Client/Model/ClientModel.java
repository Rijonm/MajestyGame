package Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.UserLoginMessage;
import javafx.application.Platform;

public class ClientModel {
	
	public Socket socket;
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
							
							if(msg.getMessageType() == MessageType.RegisterSuccessMessage) {
								Platform.runLater(() ->{
								receivedRegisterSuccessMessage(msg);
								});
							}
							if(msg.getMessageType() == MessageType.LoginSuccessMessage) {
								Platform.runLater(() ->{
								receivedLoginSuccessMessage(msg);
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
		// TODO Auto-generated method stub
		
	}

	protected void receivedRegisterSuccessMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	public void sendUserRegisterMessage(String username, String passwort) {
		
		
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