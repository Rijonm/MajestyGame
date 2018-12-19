package Model;

import java.util.ArrayList;
import java.util.Random;

	// Neue Reihenfolge

	// Ordinal, Kategorie, Art
	// 0 Orange Farmer
	// 1 Brown Brewer
	// 2 Green Witch
	// 3 Blue Defense
	// 4 Red Soldier
	// 5 Yellow Host
	// 6 Violet Queen
	// 7 Orange/Brown
	// 8 Orange/Red
	// 9 Brown/Green
	// 10 Brown/Red
	// 11 Green/Blue
	// 12 Green/Yellow
	// 13 Green/Violet
	// 14 Blue/Red
	// 15 Blue/Yellow
	// 16 Blue/Violet
	// 17 Red/Yellow
	// 18 Yellow/Violet



//@ Mert Emek

public class DeckA {
	
	public int[] cards = {9, 6, 5, 5, 3, 4, 5, 3, 2, 3, 1, 3, 1, 1, 2, 1, 1, 3,2};
	
	public ArrayList<Integer> openCards = new ArrayList<>();
	
	public DeckA() {
		
	}
	
	//Immer wenn getCard aufgerufen wird, wird eine zahl zwischen 0-17 randomly erzeugt und dient dann als ID fuer den Deck
	public int getCard() {
		Random rand = new Random();
		int cardID = rand.nextInt(18);
		int contain;
		contain = cards[cardID]; //Speicher Anzahl der vorhandenen Karten am ensprechenden Index(cardID).
		if(contain==0) { // Schaut ob an der ensprechenden Stelle noch Karten vorhanden sind. Wenn nicht dann nächstmögliche Karte zurueck.
			for(int b = 0; b < cards.length; b++) {
				if(cards[b]!=0) {
					cardID = b;
					break;
				}
			}
		}
		cards[cardID] = cards[cardID]-1; //Nachdem Karte gewählt wird, wird 1 Karte abgezogen.
		return cardID;
	}
	//openCards muss jedesmal bei einem SpielerZug entsprechend angepasst werden
	public void playerMove(int pos) {
		openCards.remove(pos); //Löscht gezogene Karte aus openCards
		openCards.add(getCard()); // Fügt eine neue Karte ein.
	}
	
	public int getFirstSixCards() {
		int card = getCard();
		openCards.add(card);
		return card;
	}
	
}
