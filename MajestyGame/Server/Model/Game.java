package Model;

import java.io.Serializable;
import java.util.ArrayList;

import CommonClasses.CardFromServerMessage;
import CommonClasses.FirstSixCardsMessage;
import javafx.collections.ObservableList;

public class Game implements Serializable {
	
	private ObservableList<Client> players; //Holds PlayerInGame
	private DeckA deckA;
	private int[] meeples = new int[6];
	int runde = 0;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
		for(Client c : model.clients) {//Adds online players to ObservableList players
			if(c.isInGame()==true)
			players.add(c);
		}
		deckA = new DeckA();
		
		sendFirstSixCards(deckA.getCard(0), deckA.getCard(1), deckA.getCard(2), deckA.getCard(3), deckA.getCard(4), deckA.getCard(5), 1);
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
		runde++;
		for (Client p : players){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				
				
			}
			CardFromServerMessage cfsm = new CardFromServerMessage(deckA.getOpenCards(), meeples);
			model.broatcastToPlayerInGame(cfsm);
			
		}
		
	}


	public void evaluate() {
		
	
	}
}