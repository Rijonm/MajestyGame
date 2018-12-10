package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import CommonClasses.FirstSixCardsMessage;
import CommonClasses.InformationFromServerMessage;
import javafx.collections.ObservableList;

public class Game  {
	
	int players = 0;
	ArrayList<Integer> playersId = new ArrayList<Integer>(); // Hier sind die ID's der Spieler  die im Spiel sind zu finden
	int playedCards = 0;
	private DeckA deckA;
	private int[] meeples = new int[6];
	int round = 0;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
		
		for(Client c : model.clients) { // Setzt ale Spieler in der Lobby als isInGame und zählt die SpielerAnzahl hoch, ausserdem wird die ID der Spieler in der ArrayList playersId gespeichert
			if(c.isOnline()==true) {
				c.setIsInGame(true);
				playersId.add(c.getId());
				players++;
			}
		}
		System.out.println();
		
		for(int i = 0; i<meeples.length; i++) { // Meeples 0,0,0,0,0,0
			meeples[i] = 0;
		}
		
		deckA = new DeckA();
		sendFirstSixCards(deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), playersId.get(0)); // Setzt die ersten 6 Karten und gibt dem einten Spieler die Möglichkeit das Spiel zu beginnen.
	}
	
	public void sendFirstSixCards(int a, int b, int c, int d, int e, int f, int turn) {
		FirstSixCardsMessage fscm= new FirstSixCardsMessage(a, b, c, d, e, f, turn);
		model.broatcastToPlayerInGame(fscm);
	}
	/*
	 * Wenn die Position eintrifft, dann solle in der players Liste geschaut werden, welcher Spieler den entsprechenden Zug gemacht hat.
	 * 
	 */
	public void receivedPosFromClient(int pos, int id) {
		for(Client p : model.clients)
		playedCards++;
		if(playedCards!=0 && players!= 0) { //check die Rundenanzahl ab und speicher sie in round.
			int a = playedCards/players;
			round = a;
		}
		
		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				p.getHand().playerChoose(deckA.openCards.get(pos)); //übergibt die id der gewählten Karte beim jeweiligen spieler
			}
		}
		
		deckA.playerMove(pos);
		Integer[] openCardsArray = deckA.openCards.toArray(new Integer[deckA.openCards.size()]);
		//Integer[] playerPointsArray = model.clients.get(0).getHand().getPoints();
		InformationFromServerMessage ifsm = new InformationFromServerMessage(openCardsArray);
		System.out.println(ifsm.getOpenCards());
		model.broatcastToPlayerInGame(ifsm);
		
		
	}

	public void evaluate() {
		
	
	}
}