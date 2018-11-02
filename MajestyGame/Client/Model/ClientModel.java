package Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Commons.Message;

public class ClientModel {
	
	public Socket socket;
	public ClientModel() {
		
	}
	
	public void connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println("Client connect request");
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void sendRegistrieren(String username, String passwort) {
		
		
	}
	
	public void sendLogin(String username, String passwort) {
		
	}
	
	public void sendGameStart() {
		
	}
	
	public void sendGetHighscore() {
		
	}
	
	public void sendPlayMove() {
		
	}
	
	public void disconnect() {
		
	}
	
	public void sendMessage() {
		
		
	}
	
	public void receiveMessage() {
		
		//if(msg == msg.messageType.ChatMessage){
		//
		//}
		//if(msg == msg.messageType.LoginMessage){
		//
		//}
	}
}