package Model;

import java.net.Socket;

/**
 *Wenn das Spiel beginnt, wird ein neuer Player instanziiert.
 *
 *@author Rijon
 */

public class PlayerInGame extends PlayerOnline{
	
	int[] hand = new int[8];
	
	public PlayerInGame(ServerModel model, Socket socket, int id, String username) {
		super(model, socket, id, username);
		// TODO Auto-generated constructor stub
		
	}
	
	

}
