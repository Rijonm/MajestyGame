package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import CommonClasses.CardFromServerMessage;
import CommonClasses.FirstSixCardsMessage;
import javafx.collections.ObservableList;

public class Game implements Serializable {
	
	int players = 0;
	int playedCards = 0;
	private DeckA deckA;
	private int[] meeples = new int[6];
	int round = 0;
	private ServerModel model;
	Random r = new Random();
	int zerro = r.nextInt(7);
	int two = r.nextInt(7);
	int three = r.nextInt(7);
	int four =r.nextInt(7);
	int five =r.nextInt(7);
	int six =r.nextInt(7);
	
	public Game(ServerModel model) {
		this.model = model;
//		for(Client c : model.clients) {//Adds online players to ObservableList players
//			if(c.isInGame()==true)
//			players.add(c);
//		}
		for(Client c : model.clients) {
			if(c.isInGame()==true) {
				players++;
			}
		}
		deckA = new DeckA();
		sendFirstSixCards(deckA.cards[zerro], deckA.cards[two], deckA.cards[three], deckA.cards[four], deckA.cards[five], deckA.cards[six], 1);
		//sendFirstSixCards(deckA.getCard(0), deckA.getCard(1), deckA.getCard(2), deckA.getCard(3), deckA.getCard(4), deckA.getCard(5), 1);
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
		
		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				
				p.getHand().playerChoose(pos);
				
			}
			CardFromServerMessage cfsm = new CardFromServerMessage(deckA.getOpenCards(), meeples);
			model.broatcastToPlayerInGame(cfsm);
			
		}
		
	}


	public void evaluate() {
		
	
	}
}