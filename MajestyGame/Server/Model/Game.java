package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import CommonClasses.FirstSixCardsMessage;
import CommonClasses.InformationFromServerMessage;
import javafx.collections.ObservableList;

public class Game  {
	
	int players = 0;
	int playedCards = 0;
	private DeckA deckA;
	private int[] meeples = new int[6];
	int round = 0;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
		
		for(Client c : model.clients) {
			if(c.isInGame()==true) {
				players++;
			}
		}
		deckA = new DeckA();
		sendFirstSixCards(deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), 1);
	}
	
	public void sendFirstSixCards(int a, int b, int c, int d, int e, int f, int turn) {
		FirstSixCardsMessage fscm= new FirstSixCardsMessage(a, b, c, d, e, f, turn);
		model.broadcastToOnlinePlayers(fscm);
	}
	/*
	 * Wenn die Position eintrifft, dann solle in der players Liste geschaut werden, welcher Spieler den entsprechenden Zug gemacht hat.
	 * 
	 */
	public void receivedPosFromClient(int pos, int id) {
		playedCards++;
		if(playedCards!=0 && players!= 0) { //check die Rundenanzahl ab
			int a = playedCards/players;
			round = a;
		}
		
		deckA.playerMove(pos);
		
		
//		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
//			if(p.getId()==id) {
//				
//			}
//		}
		System.out.println(deckA.openCards);
		InformationFromServerMessage ifsm = new InformationFromServerMessage(deckA.openCards.toArray(new Integer[deckA.openCards.size()]));
		System.out.println(ifsm.getOpenCards());
		model.broadcastToOnlinePlayers(ifsm);
		
		
	}
	
	


	public void evaluate() {
		
	
	}
}