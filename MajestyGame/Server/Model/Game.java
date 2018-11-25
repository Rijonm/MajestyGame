package Model;

import java.io.Serializable;
import java.util.ArrayList;

import CommonClasses.FirstSixCardsMessage;
import javafx.collections.ObservableList;

public class Game implements Serializable {
	
	private ObservableList<PlayerInGame> players;
	private Deck deck;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
		players = model.playeringame;
		deck = new Deck();
		
		//sendFirstSixCards(deck.getCard(), deck.getCard(), deck.getCard(), deck.getCard(), deck.getCard(), deck.getCard(), int getTurn());
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
		for (PlayerInGame p : players){
			if(p.getId() == id) {
				//p.setHand(pos, deck.getCard(pos)); <---- muss noch gemacht werden. PlayerInGame set Hand at Position x to anzahl..
				//cardFromServerMessage(deck.getDeck(), int playerTurnID); <---- füllt cardFromServerMessage mit neuem Deck und mit der player id des spielers, der an der Reihe ist.
				//model.broatcastToPlayerInGame(cardFromServerMessage); <---- Alle Spieler in Game erhalten neuen Deck
				//for(PlayerInGame p :players){
				//
				//pointsOfPlayer(id, points)
				//model.broatcastToPlayerInGame(pointsOfPlayer)
				//}
			}
		}
		
	}


	public void evaluate() {
		
	
	}
}