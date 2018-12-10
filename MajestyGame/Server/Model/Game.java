package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import CommonClasses.CardFromServerMessage;
import CommonClasses.FirstSixCardsMessage;
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
		sendFirstSixCards(deckA.getCard(), deckA.getCard(), deckA.getCard(), deckA.getCard(), deckA.getCard(), deckA.getCard(), 1);
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
		sendFirstSixCards(deckA.openCards.get(0), deckA.openCards.get(1), deckA.openCards.get(2), deckA.openCards.get(3), deckA.openCards.get(4), deckA.openCards.get(5), 1);
		
		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				
			}
			//CardFromServerMessage cfsm = new CardFromServerMessage(deckA.getOpenCards(), meeples);
			//model.broatcastToPlayerInGame(cfsm);
			
		}
		
	}
	
	


	public void evaluate() {
		
	
	}
}