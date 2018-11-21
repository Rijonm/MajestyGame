package Model;

import java.util.ArrayList;

public class Deck {
	
	
	// Cards[] cards2 = new Cards[19]
	
	private final ArrayList<Cards> cards = new ArrayList<>();
	
	
	// CardID, Name, Quantity of remaining Cards, Coins
	Cards brewer = new CardBrewer(1, "Brauer", 10, 5);
	Cards defense = new CardBrewer(2, "Verteidigung", 10, 5);
	Cards farmer = new CardBrewer(3, "Bauer", 10, 5);
	Cards queen = new CardBrewer(4, "Königin", 10, 5);
	Cards soldier = new CardBrewer(5, "Soldat", 10, 5);
	Cards tavern = new CardBrewer(6, "Taverne", 10, 5);
	Cards witch = new CardBrewer(7, "Hexe", 10, 5);
	
	/*add more cards (split cards)
	 * 
	 * Cards brewer = new CardBrewer(8, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(9, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(10, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(11, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(12, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(13, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(14, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(15, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(16, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(17, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(18, "Bauer", 10, 5);
	Cards brewer = new CardBrewer(18, "Bauer", 10, 5);
	*/

	
	/*
	allCards[0] = 7; //Orange
	allCards[1] = 4; //Brown
	allCards[2] = 3; //Green
	allCards[3] = 3; //Blue
	allCards[4] = 2; //Red
	allCards[5] = 2; //Yellow
	allCards[6] = 3; //Violet
	allCards[7] = 2; //Orange/Brown
	allCards[8] = 1; //Orange/Red
	allCards[9] = 1; //Brown/Green
	allCards[10] = 0; //Brown/Red
	allCards[11] = 1; //Green/Blue
	allCards[12] = 0; //Green/Yellow
	allCards[13] = 0; //Green/Violet
	allCards[14] = 0; //Blue/Red
	allCards[15] = 1; //Blue/Yellow
	allCards[16] = 1; //Blue/Violet
	allCards[17] = 1; //Red/Yellow
	allCards[18] = 1; //Yellow/Violet
	*/
}
