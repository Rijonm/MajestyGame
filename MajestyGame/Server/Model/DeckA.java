package Model;

import java.util.ArrayList;
import java.util.Random;


	// 0 Brown Brewer
	// 1 Blue Defense
	// 2 Orange Farmer
	// 3 Violet Queen
	// 4 Red Soldier
	// 5 Yellow Host
	// 6 Green Witch
	// 7 Orange/Brown
	// 8 Orange/Red
	// 9 Brown/Green
	// 10 Brown/Red
	// 11 Green/Blue
	// 12 Green/Yellow
	// 13 Blue/Red
	// 14 Blue/Yellow
	// 15 Blue/Violet 
	// 16 Red/Yeelow
	// 17 Yellow/Violet
public class DeckA {
	
	public int[] cards = {6, 5, 9, 5, 3, 4, 5, 3, 2, 3, 1, 3, 1, 2, 1, 1, 3, 2};
	public ArrayList<Integer> openCards = new ArrayList<>();
	
	public DeckA() {
		
	}
	
	//Immer wenn getCard aufgerufen wird, wird eine zahl zwischen 0-17 randomly erzeugt und dient dann als ID für den Deck
	public int getCard() {
		Random rand = new Random();
		int cardID = rand.nextInt(18);
		System.out.println(cardID);
		int contain;
		contain = cards[cardID]; //Speicher Anzahl der vorhandenen Karten am ensprechenden Index(cardID).
		if(contain==0) { // Schaut ob an der ensprechenden Stelle noch Karten vorhanden sind. Wenn nicht dann nächstmögliche Karte zurück.
			for(int b = 0; b < cards.length; b++) {
				if(cards[b]!=0) {
					cardID = b;
					break;
				}
			}
		}
		cards[cardID] = cards[cardID]-1; //Nachdem Karte gewählt wird, wird 1 Karte abgezogen.
		if(openCards.size()<=5) { // Die ArrayList openCards muss zuerst gefüllt werden, dies geschieht beim ziehen der ersten 6 Karten
		openCards.add(cardID);
		}
		return cardID;
	}
	//openCards muss jedesmal bei einem SpielerZug entsprechend angepasst werden
	public void playerMove(int pos) {
		openCards.remove(pos); //Löscht gezogene Karte aus openCards
		openCards.add(getCard()); // Fügt eine neue ein
	}
	
	
	
	
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
	
	
	
//	public int[] getOpenCards() {
//		int a = 0;
//		for(Card c : openCards) { //Holt sich die CardID von openCards und fügt die ID in openCardsID
//			openCardsID[a] = c.cardID;
//			a++;
//		}
//		
//		return openCardsID;
//	}
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
	public void playerChoose(int cardID) {
		int i = cardID;
		
//		switch (i) {
//        case 1:
//        	Brewer.add(cardBrewer);
//        	break;
//        case 2:
//        	Defense.add(cardDefense);
//        	break;
//        case 3:
//        	Farmer.add(cardFarmer);
//        	break;
//        case 4:
//        	Queen.add(cardQueen);
//        	break;
//        case 5:
//        	Soldier.add(cardSoldier);
//        	// Verteidung der Gegner ueberpruefen
//        	// Gegner mit weniger Verteidiger als unser anzahl Soldaten, werrden angefriffen.
//        	break;
//        case 6:
//        	Witch.add(cardWitch);
//        	int s = Lazarett.size()-1;
//        	Card c = Lazarett.get(s);
//        	Lazarett.remove(s);
//        	int id = c.getCardID();
//        	if(id == 1)
//        		Brewer.add(cardBrewer);
//        	if(id == 2)
//        		Defense.add(cardDefense);
//        	if(id == 3)
//        		Farmer.add(cardFarmer);
//        	if(id == 4)
//        		Queen.add(cardQueen);
//        	if(id == 5)
//        		Soldier.add(cardSoldier);
//        	if(id == 6)
//        		Witch.add(cardWitch);
//        	break;
//        case 7:
//        	Tavern.add(cardTavern);
//        	break;   
//    }
		
		//
	}
}
