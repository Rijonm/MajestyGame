package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import CommonClasses.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerModel {
	
	
	ServerSocket serverSocket;
	Socket socket;
	protected ObservableList<Client> clients = FXCollections.observableArrayList();
	
//	private Game game;
//	private Message actionToPerform;
	
	public ServerModel() {

	}
	
	public void serverStart(int port) {
		System.out.println(port);
		
		try {
			serverSocket = new ServerSocket(port);
			Runnable r = new Runnable() {

				@Override
				public void run() {
					while(true) {
					try {
						
						socket = serverSocket.accept();
						System.out.println("ClientAccepted");
						Client c = new Client(ServerModel.this, socket);
						clients.add(c);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}	
					}
				}
			};
			Thread t = new Thread(r, "ServerSocket");
			t.start();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void broatcast() {
		
	}
	
	public void registerUserToDB() {
		
	}
	
	public void sendCardsToPlayers() {
		
	}
	
	public void evalWinner() {
		
	}

}
