package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class DeckA {
	
	
	//Card[] cards2 = new Cards[19]
	
	//private ArrayList<Card> card = new ArrayList<>();
	public ArrayList<Card> openCards = new ArrayList<>();
	public int[] openCardsID = new int[6];
	
	public int[] cards = {11, 6, 11, 6, 5, 6, 4};
	
	// 0 Brown Brewer
	// 1 Blue Defense
	// 2 Orange Farmer
	// 3 Violet Queen
	// 4 Red Soldier
	// 5 Yellow Host
	// 6 Green Witch
	
	
	
	// CardID, Name, Quantity of remaining Cards, Coins
//	Card brewer = new CardBrewer(1, "Brauer", 10, 5);
//	Card defense = new CardDefense(2, "Verteidigung", 2, 5);
//	Card farmer = new CardFarmer(3, "Bauer", 7, 5);
//	Card queen = new CardQueen(4, "Königin", 10, 5);
//	Card soldier = new CardSoldier(5, "Soldat", 3, 5);
//	Card tavern = new CardTavern(6, "Taverne", 4, 5);
//	Card witch = new CardWitch(7, "Hexe", 3, 5);
	
	
	
//	public DeckA() {
//		cards.add(brewer);
//		cards.add(defense);
//		cards.add(farmer);
//		cards.add(queen);
//		cards.add(soldier);
//		cards.add(tavern);
//		cards.add(witch);
//		Collections.shuffle(cards);
//	}
	
//	public int getCard(int a) {
//		openCards.add(cards.get(a));
//		return cards.get(a).getCardID();
//	}
	
	
	
	public int[] getOpenCards() {
		int a = 0;
		for(Card c : openCards) { //Holt sich die CardID von openCards und fügt die ID in openCardsID
			openCardsID[a] = c.cardID;
			a++;
		}
		
		return openCardsID;
	}
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
