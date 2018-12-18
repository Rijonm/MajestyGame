package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import CommonClasses.FirstSixCardsMessage;
import CommonClasses.InformationFromServerMessage;
import CommonClasses.OpponentPlayerMessage;
import CommonClasses.PlayerStatesMessage;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.objects.annotations.Where;


public class Game  {
	
	int players = 0;
	ArrayList<Integer> playersId = new ArrayList<Integer>(); // Hier sind die ID's der Spieler  die im Spiel sind zu finden
	int playedCards = 0;
	private DeckA deckA;
	private Integer[] meeples = new Integer[6];
	int round = 0;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
		
		for(Client c : model.clients) { // Setzt ale Spieler in der Lobby als isInGame und zählt die SpielerAnzahl hoch, ausserdem wird die ID der Spieler in der ArrayList playersId gespeichert, welche sich in der Lobby befinden.
			if(c.isOnline()==true) {
				c.setIsInGame(true);
				playersId.add(c.getId());
				players++;
			}
		}
		
		for(Client c : model.clients) { // Sendet jedem Spieler die informartionen jedes Spielers/Gegeners
			if(c.isInGame()== true) {
				for(Client o : model.clients) {
				OpponentPlayerMessage opm = new OpponentPlayerMessage(o.getId(), o.getUsername(), o.getHand().hand, o.getHand().meeples, o.getHand().coins);
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
		
		if(meeples[pos] > 0) { //Schaut zuerst wieviele meeples sich unter der angelickten Karte befinden, falls über 0...
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 //...dann sucht er denn spieler der den Zug ausgeführt hat
					c.getHand().meeples = c.getHand().meeples + meeples[pos];// ...und fügt die Meeples in seiner "Hand" ein, die sich unter der Karte befinden.
					c.getHand().actuallMeeples();
				}
			}
		}
		
		switch(pos) {
		case 0:
			meeples[0] = 0;
			break;
		case 1:
			meeples[0] = meeples[0] + 1;
			meeples[1] = 0;
			break;
		case 2:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = 0;
			break;
		case 3:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			meeples[3] = 0;
			break;
		case 4:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			meeples[3] = meeples[3] + 1;
			meeples[4] = 0;
			break;
		case 5:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			meeples[3] = meeples[3] + 1;
			meeples[4] = meeples[4] + 1;
			meeples[2] = 0;
			break;
			
		}
		System.out.println(Arrays.toString(meeples) + "MEEPLES SERVER");
		
		//RUNDE
		playedCards++;
		if(playedCards!=0 && players!= 0) { //check die Rundenanzahl ab und speicher sie in round.
			int a = playedCards/players;
			round = a;
		}
		System.out.println(cardID + " IDDDD");
		//UPDATE HAND AND COINS OF PLAYERS		
		for (Client p : model.clients){ //handelt die Hand des Clients, der gespielt hat.
			if(p.getId()==id) {
				p.getHand().playerChoose(cardID); //übergibt die id der gewählten Karte beim jeweiligen spieler
				System.out.println(Arrays.toString(p.getHand().hand) + " HAAAND");
			}
		}
		
		
		if(cardID == 4) { //Falls ein Angriff dann
			for(Client o : model.clients) {
				if(o.isInGame() && o.getId()!= id) { //Chek nur Gegenspieler ab
					for(Client c : model.clients)
						if(c.getId() == id) {
							if(o.getHand().hand[3] < c.getHand().hand[4]) { //Falls Gegenspieler weniger Verteidigung als Spieler der gezogen hat...
								for(int i = 0; i<7; i++) {
									if(o.getHand().hand[i] > 0) {
										o.getHand().hand[i] = o.getHand().hand[i]-1;
										o.getHand().setToLazarett(i);
										break;
									}
								}
						}
					}
				}
			}
		}
		
		//SEND UPDATED INFORMATIONS from the Client which made the move to all Clients
		for(Client c : model.clients) {
			int idc = c.getId();
			Integer[] playerHandArray = c.getHand().hand;
			int coins = c.getHand().coins;
			int meeples = c.getHand().meeples;
			PlayerStatesMessage psm = new PlayerStatesMessage(idc, playerHandArray, coins, meeples);
			System.out.println(Arrays.toString(playerHandArray));
			model.broatcastToPlayerInGame(psm);
		}
		
		//DECK AND MEEPLES HANDLING  
		deckA.playerMove(pos);
		Integer[] openCardsArray = deckA.openCards.toArray(new Integer[deckA.openCards.size()]);
		InformationFromServerMessage ifsm = new InformationFromServerMessage(openCardsArray, meeples);
		model.broatcastToPlayerInGame(ifsm);
		
		//EVALUATION NACH 12 RUNDEN
		if(round == 12) {
			//evaluate();
		}
		
	}

	
	public void evaluate() {
	for(Client c : model.clients) {
		
		for(int i = 0; i<=7; i++) {
		c.getHand().getHandSize(i);
		}	
	// 0 Farmer 10 Coins
	// 1 Brewer 11 Coins
	// 2 Witch 12 Coins
	// 3 Defense 13 Coins
	// 4 Soldier 14 Coins
	// 5 Tavern 15 Coins
	// 6 Queen 16 Coins
	// 7 Lazarett pro Karte -1 Coins
	
	
		// for(Client c : model.client)
		// for(c.)
		// for(Client c : model.client)
		// Hands der Clients vergleichen und winner definieren
		// Client winner uf 1 setzte 
	}
}
}