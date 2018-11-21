package Model;

import java.net.Socket;
import java.util.ArrayList;

/**
 *Wenn das Spiel beginnt, wird ein neuer Player instanziiert.
 *
 *@author Mert
 */

public class PlayerInGame extends PlayerOnline{
	
	/*
	 *  oder in int int[] hand = new int[8];
	 */
	
	/* 
	 * Die ArrayList representiert den Spielerhand mit 
	 * den gezogenen Karten in den 6 Gebäudekarten
	 */
	
	ArrayList<Hand> playerHand = new ArrayList<Hand>();

	
	public PlayerInGame(ServerModel model, Socket socket, int id, String username) {
		super(model, socket, id, username);
		// TODO Auto-generated constructor stub
		
	}
	
	/*
	 * Diese Methode soll bei jedem Zug eines Spielers, die gezogene Hand
	 * und ensprechend in die Gebäudekarte ablegen. Die Methoden der Karten sollen 
	 * auch direkt ausgeführt werden.
	 */
	public void PlayerChoose(EnumCard a) {
		switch (a) {
        case Brewer:
        	Hand.Brewer.add(a);
        	
        	break;
        case Defense:
        	Hand.Defense.add(a);
        	break;
        case Farmer:
        	Hand.Farmer.add(a);
        	break;
        case Queen:
        	Hand.Queen.add(a);
        	break;
        case Soldier:
        	Hand.Soldier.add(a);
        	break;
        case Witch:
        	Hand.Witch.add(a);
        	break;
        case Tavern:
        	Hand.Tavern.add(a);
        	break;
    }
	
    


	}
	
	
	
	

}
