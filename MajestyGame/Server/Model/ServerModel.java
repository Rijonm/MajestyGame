package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import CommonClasses.Message;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ServerModel {
	
	
	public ServerSocket serverSocket;
	public Socket socket;
	protected ObservableList<Client> clients = FXCollections.observableArrayList();
	protected ObservableList<PlayerOnline> playeronline = FXCollections.observableArrayList();
	protected ObservableList<PlayerInGame> playeringame = FXCollections.observableArrayList();
	public Database db;
	private final Logger logger = Logger.getLogger("");
//	private Game game;
//	private Message actionToPerform;
	
	public ServerModel() {
		db = new Database();
		
		playeronline.addListener((ListChangeListener<PlayerOnline>) e ->{
			
	});
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
	
	public void serverStop() {
		
	}
	
	
	/*
	 * Schickt eine Message an alle Spieler die Online sind.
	 * 
	 * @author Rijon
	 */
	public void broadcastToOnlinePlayers(Message outMsg) {
		logger.info("Broadcasting message to players online");
		for (PlayerOnline c : playeronline) {
			c.send(outMsg);
		}
	}
	/*
	 * Schickt eine Message ab alle Spieler die im Spiel sind.
	 * 
	 * @author Rijon
	 */
	public void broatcastToPlayerInGame(Message outMsg) {
		logger.info("Broatcasting message to players in game.");
		for(PlayerInGame c : playeringame) {
			c.send(outMsg);
		}
	}
	
	public void startGame() {
		Game game = new Game(ServerModel.this);
	}
	
	/*
	 * Der Gewinner wird evaluiert.
	 * 
	 * @author Rijon
	 */
	public void evalWinner() {
		
	}

}
