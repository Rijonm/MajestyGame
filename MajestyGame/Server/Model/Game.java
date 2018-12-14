package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import CommonClasses.FirstSixCardsMessage;
import CommonClasses.InformationFromServerMessage;
import CommonClasses.OpponentPlayerMessage;
import javafx.collections.ObservableList;

//@ Mert Emek

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
		
		for(Client c : model.clients) { // Sendet jedem Spieler die informartionen jedes Spielers/Gegeners
			if(c.isInGame()== true) {
				for(Client o : model.clients) {
					System.out.println(o.getId() + o.getUsername() + o.getHand().hand.toString() + o.getMeeples());
				OpponentPlayerMessage opm = new OpponentPlayerMessage(o.getId(), o.getUsername(), o.getHand().hand, o.getMeeples());
				c.send(opm);
				}
			}
		}
		
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
	public void receivedPosFromClient(int pos, int cardID, int id) {
		
		//RUNDE
		playedCards++;
		if(playedCards!=0 && players!= 0) { //check die Rundenanzahl ab und speicher sie in round.
			int a = playedCards/players;
			round = a;
		}
		
		//UPDATE HAND AND COINS OF PLAYER PLAYED
		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				p.getHand().playerChoose(cardID); //übergibt die id der gewählten Karte beim jeweiligen spieler
			}
		}
		//SEND UPDATED INFORMATIONS
		for(int playerId : playersId) {
			//Integer[] playerHandArray = model.clients.get(playerId).getHand().getHandArray();
			//int coins = model.clients.get(playerId).getHand.getCoins();
			//PlayerStatesMesage psm = new PlayerSatesMessage(id, playerHandArray, coins);
			//model.broatcatToPlayerInGame(psm)
		}
		
		//DECK AND MEEPLES HANDLING  
		deckA.playerMove(pos);
		Integer[] openCardsArray = deckA.openCards.toArray(new Integer[deckA.openCards.size()]);
		InformationFromServerMessage ifsm = new InformationFromServerMessage(openCardsArray);
		System.out.println(ifsm.getOpenCards());
		model.broatcastToPlayerInGame(ifsm);
		
		//EVALUATION NACH 12 RUNDEN
		if(round == 12) {
			//evaluate();
		}
		
	}

	
	//public void evaluate() {
		//for(Client c : model.client)
		//  for(c.)
		   
		 
		  // for(Client c : model.client)
		 // Hands der Clients vergleichen und winner definieren
		//  Client winner uf 1 setzte 
	//}
}