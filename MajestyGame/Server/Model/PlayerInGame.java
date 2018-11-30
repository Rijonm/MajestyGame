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
	public void playerChoose(int CardID) {
		 
//		switch (CardID) {
//        case 1:
//        	Hand.Brewer.add(1, "Brauer", 3, 4);
//        	break;
//        case 2:
//        	Hand.Defense.add(1);
//        	break;
//        case 3:
//        	Hand.Farmer.add(1);
//        	break;
//        case 4:
//        	Hand.Queen.add(1);
//        	break;
//        case 5:
//        	Hand.Soldier.add(1);
//        	Model.CardSoldier.attack();
//        	break;
//        case 6:
//        	Hand.Witch.add(1);
//        	break;
//        case 7:
//        	Hand.Tavern.add(1);
//        	break;
//        default:
//        	break;
    }
	
    


	}
	
	

