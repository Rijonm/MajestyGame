package Model;

import java.util.ArrayList;

//@ Mert Emek

public class Hand {
	
	int[] handSize = new int[8]; 
	Integer[] hand = new Integer[8]; //!
	public int coins;
	public int meeples = 5; //!
	
	Card cardBrewer = new CardBrewer();
	Card cardDefense = new CardDefense();
	Card cardFarmer = new CardFarmer();
	Card cardQueen = new CardQueen();
	Card cardSoldier = new CardSoldier();
	Card cardWitch = new CardWitch();
	Card cardTavern = new CardTavern();
	
	
	// Gebaude Karten
	public static ArrayList<Card> Brewer = new ArrayList <>();
	public static ArrayList<Card> Defense = new ArrayList <>();
	public static ArrayList<Card> Farmer = new ArrayList <>();
	public static ArrayList<Card> Queen = new ArrayList <>();
	public static ArrayList<Card> Soldier = new ArrayList <>();
	public static ArrayList<Card> Witch = new ArrayList <>();
	public static ArrayList<Card> Tavern = new ArrayList <>();
	public static ArrayList<Card> Lazarett = new ArrayList <>();
	
	public Hand() {
		for(int i = 0; i<hand.length; i++) { // Punktezahl 0,0,0,0,0,0,0,0
			hand[i] = 0;
		}
		coins = 0;
	}
	
	// Mit dieser Methode erhaelt mann die Karten anzahl im Hand
	public int[] getPlayerCards() {
	handSize[0] = Farmer.size();
	handSize[1] = Brewer.size();
	handSize[2] = Witch.size();
	handSize[3] = Defense.size();
	handSize[4] = Soldier.size();
	handSize[5] = Tavern.size();
	handSize[6]	= Queen.size();
	handSize[7] = Lazarett.size();	
	return handSize;
	}
	
	// Diese Methode wird ausgeführt, wenn der Spieler auf ein Button im Deck klickt
	public void playerChoose(int cardID) {
		int i = cardID;

		switch (i) {
        case 0:
        	Farmer.add(cardFarmer);
        	int farmerSize = hand[0]+1; 
        	hand[0] = farmerSize;
        	break;
        case 1:
        	Brewer.add(cardBrewer);
        	int brewerSize = hand[1]+1; 
        	hand[1] = brewerSize;
        	break;
        	
        case 2:
        	Witch.add(cardWitch);
        	int witchSize = hand[2]+1; 
        	hand[2] = witchSize;
        	if(Lazarett.size()>0) {
        	int s = Lazarett.size();
        	Card c = Lazarett.get(s-1);
        	System.out.println(c.cardName + c.cardID + "HIIIIER");
        	if(s>0) {
        	this.hand[7] = 	hand[7]-1;
        	Lazarett.remove(Lazarett.size()-1);
        	int id = c.getCardID();
        	if(id == 0) {
        		Farmer.add(cardFarmer);
        	this.hand[0] = 	hand[0]+1;
        	}
        	if(id == 1) {
        		Brewer.add(cardBrewer);
        	this.hand[1] = 	hand[1]+1;
        	}
        	if(id == 2) {
        		Witch.add(cardWitch);
        	this.hand[2] = 	hand[2]+1;
        	}
        	if(id == 3) {
        		Defense.add(cardDefense);
        	this.hand[3] = 	hand[3]+1;
        	}
        	if(id == 4) {
        		Soldier.add(cardSoldier);
        	this.hand[4] = 	hand[4]+1;
        	}
        	if(id == 5) {
        		Witch.add(cardWitch);
        	this.hand[5] = 	hand[5]+1;
        	}
        	if(id == 6) {
        		Queen.add(cardQueen);
        	this.hand[6] = 	hand[6]+1;
        	}
        	}
        	}	
     
        	break;
        	
        case 3:
        	Defense.add(cardDefense);
        	int defenseSize = hand[3]+1; 
        	hand[3] = defenseSize;
        	break;
        	
        case 4:
        	Soldier.add(cardSoldier);
        	int soldierSize = hand[4]+1; 
        	hand[4] = soldierSize;
        	// Verteidung der Gegner ueberpruefen
        	// Gegner mit weniger Verteidiger als unser anzahl Soldaten, werrden angefriffen.
        	break;
        case 5:
        	Tavern.add(cardTavern);
        	int tavernSize = hand[5]+1; 
        	hand[5] = tavernSize;
        	break;  
        	
        case 6:
        	Queen.add(cardQueen);
        	int queenSize = hand[6]+1; 
        	hand[6] = queenSize;
        	break;
        	 
		}
		coins = coins + distributeCoinsCardA(cardID);
		actuallMeeples();
	}
	
	// Aktuele Handgroesse eines Gebaude 
	public int getHandSize(int cardID) {
		int actuallHandSize = hand[cardID];
		return actuallHandSize;
	}
	// falls der Spieler mehr als Meeples hat, setzt es zurueck auf 5 meeples und verteilt dementsprechen Coins
	// 1 Meeple = 1 Coin
	public void actuallMeeples() {
		switch (this.meeples) {
        case 6:
        	this.coins = coins +1;
        	this.meeples = meeples-1;
        	break;
        case 7:
        	this.coins = coins +2;
        	this.meeples = meeples-2;
        	break;
        case 8:
        	this.coins = coins +3;
        	this.meeples = meeples-3;
        	break;
        case 9:
        	this.coins = coins +4;
        	this.meeples = meeples-4;
        	break;
        case 10:
        	this.coins = coins +5;
        	this.meeples = meeples-5;
        	break;
        case 11:
        	this.coins = coins +6;
        	this.meeples = meeples-6;
        	break;
        case 12:
        	this.coins = coins +7;
        	this.meeples = meeples-7;
        	break;
        default:
		meeples = meeples;
	}
		this.meeples = meeples;
	}
	// Verteilt die Coins bei gezogener Karte
		public  int distributeCoinsCardA(int cardID) {
			int handSize = getHandSize(cardID);
			int disCoins = 0; // coins which will distribute 
			int disMeeples;
			switch (cardID) {
	        case 0:
	        	disCoins = handSize*2;
	        	break;
	        case 1:
	        	disCoins = handSize*2;
	        	disMeeples = handSize;
	        	meeples = meeples+disMeeples;
	        	break;
	        	
	        case 2:
	        	if(hand[0]==1 && hand[1]==1 && hand[2]==1) {
	        		disCoins = 2;
	        	}
	        	if(hand[0]==2 && hand[1]==2 && hand[2]==2) {
	        		disCoins = 4;
	        	}
	        	if(hand[0]==3 && hand[1]==3 && hand[2]==3) {
	        		disCoins = 6;
	        	}
	        	break;
	        	
	        case 3:
	        	if(hand[3]==1 && hand[4]==1 && hand[5]==1) {
	        		disCoins = 2;
	        	}
	        	if(hand[3]==2 && hand[4]==2 && hand[5]==2) {
	        		disCoins = 4;
	        	}
	        	if(hand[3]==3 && hand[4]==3 && hand[5]==3) {
	        		disCoins = 6;
	        	}
	        	
	        	
	        	break;
	        	
	        case 4:
	        	disCoins = handSize*3;
	        	break;
	        case 5:
	        	disCoins = handSize*4;
	        	break;
	        	
	        case 6:
	        	disCoins = handSize*5;
	        	disMeeples = handSize;
	        	meeples = meeples+disMeeples;
	        	break;
	        	
		}
			return disCoins;
		
		}
		
		// falls ein Spieler angegriffen wird, die 1. von Links ins Lazarett abgelegt, falls 1. 0 sein sollte, wird 
		// 2. in Lazarett abgelegt usw.
		public void setToLazarett(int Ordinal) {
			switch (Ordinal) {
	        case 0:
	        	Lazarett.add(cardFarmer);
	        this.hand[7] = 	hand[7]+1;
	        	break;
	        case 1:
	        	Lazarett.add(cardBrewer);
	        this.hand[7] = 	hand[7]+1;
	        	break;
	        case 2:
	        	Lazarett.add(cardWitch);
	        	this.hand[7] = 	hand[7]+1;
	        	break;
	        case 3:
	        	Lazarett.add(cardDefense);
	        	this.hand[7] = 	hand[7]+1;
	        	break;
	        case 4:
	        	Lazarett.add(cardSoldier);
	        	this.hand[7] = 	hand[7]+1;
	        	break;
	        case 5:
	        	Lazarett.add(cardTavern);
	        	this.hand[7] = 	hand[7]+1;
	        	break;
	        case 6:
	        	Lazarett.add(cardQueen);
	        	this.hand[7] = 	hand[7]+1;
	        	break;   
			}
			
		}
		
	
	

	
}