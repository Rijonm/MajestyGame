package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import CommonClasses.Message;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/*
 * Server wird gestartet.
 * @author Rijon
 */
public class ServerModel {

	public ServerSocket serverSocket;
	public Socket socket;
	protected ObservableList<Client> clients = FXCollections.observableArrayList();
	public Database db;
	private final Logger logger = Logger.getLogger("");
	private Game game;

	public ServerModel() {
		db = new Database();
	}

	public void serverStart(int port) {
		System.out.println("Server started on port: " + port);

		try {
			serverSocket = new ServerSocket(port);
			Runnable r = new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							socket = serverSocket.accept();
							System.out.println("ClientAccepted");
							Client c = new Client(ServerModel.this, socket);
							clients.add(c);
						}
					} catch (SocketException e) {
						System.out.println("server socket is closed");
					} catch (IOException e) {
						e.printStackTrace();
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
		try {
			System.out.println("closing server socket");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Schickt eine Message an alle Spieler die Online sind.
	 * 
	 * @author Rijon
	 */
	public void broadcast(Message outMsg) {
		logger.info("Broadcasting message to players online");
		for (Client c : clients) {
			c.send(outMsg);
		}
	}

	/*
	 * Schickt eine Message an alle Spieler die Online sind.
	 * 
	 * @author Rijon
	 */
	public void broadcastToOnlinePlayers(Message outMsg) {
		logger.info("Broadcasting message to players online");
		for (Client c : clients) {
			if (c.isOnline() == true) {
				c.send(outMsg);
			}
		}
	}

	/*
	 * Schickt eine Message ab alle Spieler die im Spiel sind.
	 * 
	 * @author Rijon
	 */
	public void broatcastToPlayerInGame(Message outMsg) {
		logger.info("Broadcasting message to players in game");
		for (Client c : clients) {
			if (c.isInGame() == true) {
				c.send(outMsg);
			}
		}
	}

	/*
	 * Sobald ein Spieler auf SpielStarten geklickt hat wird ein neues Spiel
	 * initalisiert und das Spiel wird gestartet.
	 * 
	 * @author Rijon
	 */
	public void startGame() {
		game = new Game(ServerModel.this);

	}

	public Game getGame() {
		return game;
	}
}
