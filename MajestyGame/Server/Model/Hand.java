package Model;

import java.util.ArrayList;


public class Hand {
	
	int[] handSize = new int[8]; 
	int[] hand = new int[8]; //!
	int coins; //!
	
	
	public static ArrayList<CardBrewer> Brewer = new ArrayList <>();
	public static ArrayList<CardDefense> Defense = new ArrayList <>();
	public static ArrayList<CardFarmer> Farmer = new ArrayList <>();
	public static ArrayList<CardQueen> Queen = new ArrayList <>();
	public static ArrayList<CardSoldier> Soldier = new ArrayList <>();
	public static ArrayList<CardWitch> Witch = new ArrayList <>();
	public static ArrayList<CardTavern> Tavern = new ArrayList <>();
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