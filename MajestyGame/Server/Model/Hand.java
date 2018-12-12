package Model;

import java.util.ArrayList;


public class Hand {
	
	int[] handSize = new int[8]; 
	Integer[] hand = new Integer[8]; //!
	int coins; //!
	
	Card cardBrewer = new CardBrewer(coins, null, 0, 0);
	Card cardDefense = new CardDefense(coins, null, 0, 0);
	Card cardFarmer = new CardFarmer(coins, null, 0 ,0);
	Card cardQueen = new CardQueen(coins, null, 0 ,0);
	Card cardSoldier = new CardSoldier(coins, null,0 ,0);
	Card cardWitch = new CardWitch(coins, null,0 ,0);
	Card cardTavern = new CardTavern(coins, null,0 ,0);
	
	
	
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
	
	public int[] getPlayerCards() {
	handSize[0] = Brewer.size();
	handSize[1] = Defense.size();
	handSize[2] = Farmer.size();
	handSize[3] = Queen.size();
	handSize[4] = Soldier.size();
	handSize[5] = Witch.size();
	handSize[6]	= Tavern.size();
	handSize[7] = Lazarett.size();	
	return handSize;
	}
	
	public void playerChoose(int cardID) {
		int i = cardID;

		switch (i) {
        case 0:
        	Brewer.add(cardBrewer);
        	int brewerSize = hand[0]+1; 
        	hand[0] = brewerSize;
        	break;
        case 1:
        	Defense.add(cardDefense);
        	int defenseSize = hand[1]+1; 
        	hand[1] = defenseSize;
        	break;
        case 2:
        	Farmer.add(cardFarmer);
        	int farmerSize = hand[2]+1; 
        	hand[2] = farmerSize;
        	break;
        case 3:
        	Queen.add(cardQueen);
        	int queenSize = hand[3]+1; 
        	hand[3] = queenSize;
        	break;
        case 4:
        	Soldier.add(cardSoldier);
        	int soldierSize = hand[4]+1; 
        	hand[4] = soldierSize;
        	// Verteidung der Gegner ueberpruefen
        	// Gegner mit weniger Verteidiger als unser anzahl Soldaten, werrden angefriffen.
        	break;
        case 5:
        	Witch.add(cardWitch);
        	int witchSize = hand[5]+1; 
        	hand[5] = witchSize;
        	int s = Lazarett.size()-1;
        	Card c = Lazarett.get(s);
        	Lazarett.remove(s);
        	int id = c.getCardID();
        	if(id == 0)
        		Brewer.add(cardBrewer);
        	if(id == 1)
        		Defense.add(cardDefense);
        	if(id == 2)
        		Farmer.add(cardFarmer);
        	if(id == 3)
        		Queen.add(cardQueen);
        	if(id == 4)
        		Soldier.add(cardSoldier);
        	if(id == 5)
        		Witch.add(cardWitch);
        	break;
        case 6:
        	Tavern.add(cardTavern);
        	int tavernSize = hand[6]+1; 
        	hand[6] = tavernSize;
        	break;   
		}
		
	}
	
}