package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import CommonClasses.FirstSixCardsMessage;
import CommonClasses.FooterMessage;
import CommonClasses.InformationFromServerMessage;
import CommonClasses.OpponentPlayerMessage;
import CommonClasses.PlayerStatesMessage;
import CommonClasses.WinnerMessage;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.objects.annotations.Where;


public class Game  {
	
	private int players = 0;
	private ArrayList<Integer> playersId = new ArrayList<Integer>(); // Hier sind die ID's der Spieler  die im Spiel sind zu finden
	private DeckA deckA;
	private Integer[] meeples = new Integer[6];
	private int round = 0;
	private int playedCards = 0;
	private int turn;
	private String turnUsername;
	private int turnIndex = 0;
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
		
		turn = playersId.get(0); // Spieler der spiel iniitiert beginnt mit Zug.
		for(int i = 0; i<model.clients.size();i++) {
			if(model.clients.get(i).getId() == turn) {
				turnUsername = model.clients.get(i).getUsername();
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
		sendFirstSixCards(deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards(), deckA.getFirstSixCards()); // Setzt die ersten 6 Karten und gibt dem ersten Spieler die Möglichkeit das Spiel zu beginnen.
		FooterMessage footerMessage = new FooterMessage(turnUsername, turn, playedCards, round);
		model.broatcastToPlayerInGame(footerMessage);
		
		
	}
	
	public void sendFirstSixCards(int a, int b, int c, int d, int e, int f) {
		FirstSixCardsMessage fscm= new FirstSixCardsMessage(a, b, c, d, e, f);
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
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 
					c.getHand().meeples = c.getHand().meeples - 1;
					
				}
			}
			meeples[1] = 0;
			
			break;
		case 2:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 
					c.getHand().meeples = c.getHand().meeples - 2;
				}
			}
			meeples[2] = 0;
			break;
		case 3:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 
					c.getHand().meeples = c.getHand().meeples - 3;
				}
			}
			meeples[3] = 0;
			break;
		case 4:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			meeples[3] = meeples[3] + 1;
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 
					c.getHand().meeples = c.getHand().meeples - 4;
				}
			}
			meeples[4] = 0;
			break;
		case 5:
			meeples[0] = meeples[0] + 1;
			meeples[1] = meeples[1] + 1;
			meeples[2] = meeples[2] + 1;
			meeples[3] = meeples[3] + 1;
			meeples[4] = meeples[4] + 1;
			for(Client c : model.clients) { 
				if(c.getId()== id) {	 
					c.getHand().meeples = c.getHand().meeples - 5;
				}
			}
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
		
		
		if(playersId.size() == turnIndex+1) {
			turnIndex = -1;
		};
		turnIndex++;
		turn = playersId.get(turnIndex);
		for(int i = 0; i<model.clients.size();i++) {
		if(model.clients.get(i).getId() == turn) {
			turnUsername = model.clients.get(i).getUsername();
			}
		}
		FooterMessage footerMessage = new FooterMessage(turnUsername, turn, playedCards, round);
		model.broatcastToPlayerInGame(footerMessage);
		
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
		if(cardID == 1)
			coinsBrewerDistributeAll();
		if(cardID == 5)
			coinsTavernDistributeAll();
			
			
		
		
		//SEND UPDATED INFORMATIONS from the Client which shows the move to all Clients
		for(Client c : model.clients) {
			int idc = c.getId();
			Integer[] playerHandArray = c.getHand().hand;
			int coins = c.getHand().coins;
			int meeples = c.getHand().meeples;
			PlayerStatesMessage psm = new PlayerStatesMessage(idc, playerHandArray, meeples, coins);
			System.out.println(Arrays.toString(playerHandArray));
			model.broatcastToPlayerInGame(psm);
		}
		
		//DECK AND MEEPLES HANDLING  
		deckA.playerMove(pos);
		Integer[] openCardsArray = deckA.openCards.toArray(new Integer[deckA.openCards.size()]);
		Integer[] openMeeples = meeples.clone();
		System.out.println(Arrays.toString(meeples) + "Meeples bevor gsendet werden");
		InformationFromServerMessage ifsm = new InformationFromServerMessage(openCardsArray, openMeeples);
		model.broatcastToPlayerInGame(ifsm);
		
		//EVALUATION NACH 12 RUNDEN
		if(round == 12) {
			varietyCard();
			evaluateLazarett();
			evaluate();
			
		}
		
	}
	
	
	
	//@ Mert Emek
	public void coinsBrewerDistributeAll() {
		for(Client c : model.clients) {
			if(c.getHand().hand[0] > 0) {
				c.getHand().coins = c.getHand().coins +2;
				System.out.println("ID= "+c.getId()+" c.getHand().hand[0]= "+c.getHand().hand[1]+" Coins= "+c.getHand().coins);
			}
		}
	}
	//@ Mert Emek
	public void coinsTavernDistributeAll() {
		for(Client c : model.clients) {
			if(c.getHand().hand[1] > 0) {
				c.getHand().coins = c.getHand().coins +3;
				System.out.println("ID= "+c.getId()+" c.getHand().hand[1]= "+c.getHand().hand[1]+" Coins= "+c.getHand().coins);
			}	
		}
	}	
	
	//@ Mert Emek
	// Bei Spielende die Punkte für unterschiedliche Personen
	public void varietyCard() {
		int farmer=0, brewer=0, witch=0, defense=0, soldier=0, queen=0, host = 0, variety;
		for(Client c : model.clients) {
			if(c.getHand().hand[0]>0)
				farmer = 1;
			if(c.getHand().hand[1]>0)
				brewer = 1;
			if(c.getHand().hand[2]>0)
				witch = 1;
			if(c.getHand().hand[3]>0)
				defense = 1;
			if(c.getHand().hand[4]>0)
				soldier = 1;
			if(c.getHand().hand[5]>0)
				host = 1;
			if(c.getHand().hand[6]>0)
				queen = 1;
			variety = farmer+brewer+witch+defense+soldier+queen+host;
			System.out.println("ID= "+c.getId()+" Anzahl verschiedene Karten= "+(variety));
			c.getHand().coins = c.getHand().coins+(variety*variety);
			
		}
		
	
	}
	//@ Mert Emek
	// Pro Karte im Lazarett -1
	public void evaluateLazarett() {
		for(Client c : model.clients) {
			int minusPoints = 0;
			minusPoints = c.getHand().getHandSize(7);
			c.getHand().coins = c.getHand().coins - minusPoints;
		}
	}

	//@ Rijon & Mert
//	Hier geht ihr jedes Gebäude
//	von links nach rechts durch.
//	Auf jedem Gebäude findest du
//	rechts unten eine Münze, die
//	angibt, wie viele Münzen du
//	jeweils erhalten kannst. Dabei
//	wird geprüft, welcher Spieler die
//	meisten Personen unter diesem
//	Gebäude liegen hat. Dieser erhält
//	die angegebenen Münzen.
//	Sollte es passieren, dass mehrere
//	Spieler gleich viele Personen an
//	einem Gebäude liegen haben, so
//	bekommen alle die volle Anzahl an Münzen.
	public void evaluate() {
	
	//Farmer evaluation -------------------
	ArrayList<Integer> farmerPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		farmerPoints.add(c.getHand().hand[0]);
	}
	int farmerMax = Collections.max(farmerPoints);
	
	for(Client c : model.clients) {
		if(c.getHand().hand[0] == farmerMax) {
			c.getHand().coins = c.getHand().coins + 10;
		}
	}
	
	//Brewer evaluation -------------------
	ArrayList<Integer> brewerPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		brewerPoints.add(c.getHand().hand[1]);
	}
	int brewerMax = Collections.max(brewerPoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[1] == brewerMax) {
			c.getHand().coins = c.getHand().coins + 11;
		}
	}
		
	//Witch evaluation -------------------
	ArrayList<Integer> witchPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		witchPoints.add(c.getHand().hand[2]);
		}
	int witchMax = Collections.max(witchPoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[2] == witchMax) {
			c.getHand().coins = c.getHand().coins + 12;
		}
	}
		
	//Defense evaluation -------------------
	ArrayList<Integer> defensePoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		defensePoints.add(c.getHand().hand[3]);
		}
	int defenseMax = Collections.max(defensePoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[3] == defenseMax) {
			c.getHand().coins = c.getHand().coins + 13;
		}
	}
		
	//Soldier evaluation -------------------
	ArrayList<Integer> soldierPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		soldierPoints.add(c.getHand().hand[4]);
	}
	int soldierMax = Collections.max(soldierPoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[4] == soldierMax) {
			c.getHand().coins = c.getHand().coins + 14;
		}
	}
		
	//Host evaluation -------------------
	ArrayList<Integer> hostPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		hostPoints.add(c.getHand().hand[5]);
	}
	int hostMax = Collections.max(hostPoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[5] == hostMax) {
			c.getHand().coins = c.getHand().coins + 15;
		}
	}
		
	//Queen evaluation -------------------
	ArrayList<Integer> queenPoints = new ArrayList<Integer>();
	for(Client c : model.clients) {
		queenPoints.add(c.getHand().hand[6]);
	}
	int queenMax = Collections.max(queenPoints);
		
	for(Client c : model.clients) {
		if(c.getHand().hand[6] == queenMax) {
			c.getHand().coins = c.getHand().coins + 16;
		}
	}
	
	//END evaluation -------------------
		ArrayList<String> winners = new ArrayList<>();
		WinnerMessage wm = new WinnerMessage(winners);
		ArrayList<Integer> winnerPoints = new ArrayList<Integer>();
		for(Client c : model.clients) {
			winnerPoints.add(c.getHand().coins);
		}
		int coinMax = Collections.max(winnerPoints);
			
		for(Client c : model.clients) {
			if(c.getHand().coins == coinMax) {
				winners.add(c.getUsername());
			}
		}
		model.broatcastToPlayerInGame(wm);
	
	
	
	
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