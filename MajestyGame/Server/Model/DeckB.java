package Model;

import java.util.ArrayList;
import java.util.Collections;

public class DeckB {

	private ArrayList<Card> cards = new ArrayList<>();
	public ArrayList<Card> openCardsB = new ArrayList<>();
	
	// CardID, Name, Quantity of remaining Cards, Coins
	Card brewer = new CardBrewer(1, "Brauer", 10, 5);
	Card defense = new CardBrewer(2, "Verteidigung", 10, 5);
	Card farmer = new CardBrewer(3, "Bauer", 10, 5);
	Card queen = new CardBrewer(4, "Königin", 10, 5);
	Card soldier = new CardBrewer(5, "Soldat", 10, 5);
	Card tavern = new CardBrewer(6, "Taverne", 10, 5);
	Card witch = new CardBrewer(7, "Hexe", 10, 5);
	
	public DeckB() {
		cards.add(brewer);
		cards.add(defense);
		cards.add(farmer);
		cards.add(queen);
		cards.add(soldier);
		cards.add(tavern);
		cards.add(witch);
		Collections.shuffle(cards);
	}
	
	public int getCard(int a) {
		openCardsB.add(cards.get(a));
		return cards.get(a).getCardID();
	}
	
	
}
