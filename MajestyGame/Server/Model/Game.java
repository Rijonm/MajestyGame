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
		
		//sendFirstSixCards(deck.getCard(), deck.getCard, deck.getCard(), deck.getCard(), deck.getCard(), deck.getCard());
	}
	
	public void sendFirstSixCards(int a, int b, int c, int d, int e, int f) {
		FirstSixCardsMessage fscm= new FirstSixCardsMessage(a, b, c, d, e, f);
		model.broatcastToPlayerInGame(fscm);
	}


	public void evaluate() {
		
	
	}
}