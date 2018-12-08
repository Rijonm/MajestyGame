package Model;

import java.util.ArrayList;


public class Hand {
	
	int[]handSize = new int[8];
	
	//Not EnumCard, it should be something else, Hand represent players Building Cards with choosen player Cards
	public CardBrewer cardBrewer = new CardBrewer(1, "Brauer", 5, 5);
	public CardDefense cardDefense = new CardDefense(1, "Verteidigung", 5, 5);
	public CardFarmer cardFarmer = new CardFarmer(1, "Bauer", 5, 5);
	public CardQueen cardQueen = new CardQueen(1, "Königin", 5, 5);
	public CardSoldier cardSoldier = new CardSoldier(1, "Soldat", 5, 5);
	public CardTavern cardTavern = new CardTavern(1, "Saeufer", 5, 5);
	public CardWitch cardWitch = new CardWitch(1, "Hexe", 5, 5);
	
	
	public static ArrayList<CardBrewer> Brewer = new ArrayList <>();
	public static ArrayList<CardDefense> Defense = new ArrayList <>();
	public static ArrayList<CardFarmer> Farmer = new ArrayList <>();
	public static ArrayList<CardQueen> Queen = new ArrayList <>();
	public static ArrayList<CardSoldier> Soldier = new ArrayList <>();
	public static ArrayList<CardWitch> Witch = new ArrayList <>();
	public static ArrayList<CardTavern> Tavern = new ArrayList <>();
	public static ArrayList<Card> Lazarett = new ArrayList <>();
	
	
	
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
        case 1:
        	Brewer.add(cardBrewer);
        	break;
        case 2:
        	Defense.add(cardDefense);
        	break;
        case 3:
        	Farmer.add(cardFarmer);
        	break;
        case 4:
        	Queen.add(cardQueen);
        	break;
        case 5:
        	Soldier.add(cardSoldier);
        	// Verteidung der Gegner ueberpruefen
        	// Gegner mit weniger Verteidiger als unser anzahl Soldaten, werrden angefriffen.
        	break;
        case 6:
        	Witch.add(cardWitch);
        	int s = Lazarett.size()-1;
        	Card c = Lazarett.get(s);
        	Lazarett.remove(s);
        	int id = c.getCardID();
        	if(id == 1)
        		Brewer.add(cardBrewer);
        	if(id == 2)
        		Defense.add(cardDefense);
        	if(id == 3)
        		Farmer.add(cardFarmer);
        	if(id == 4)
        		Queen.add(cardQueen);
        	if(id == 5)
        		Soldier.add(cardSoldier);
        	if(id == 6)
        		Witch.add(cardWitch);
        	break;
        case 7:
        	Tavern.add(cardTavern);
        	break;   
    }
		
		//
}
}