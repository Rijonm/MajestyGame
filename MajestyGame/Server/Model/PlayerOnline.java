package Model;

import java.net.Socket;
/*
 * 
 * 
 * @author Rijon
 */

public class PlayerOnline extends Client{
	
	private int id;
	private String username;

	public PlayerOnline(ServerModel model, Socket socket, int id, String username) {
		super(model, socket);
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}	
	
}
