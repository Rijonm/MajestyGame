package Model;

public abstract class Card {
	
	//@author Mert Emek
	
	protected int cardID;
	protected String cardName;
	
	public Card(int cardID, String cardName) {
		this.cardID = cardID;
		this.cardName = cardName;
	}


	public void setCardID(int c) {
		this.cardID = c;
	}
	
	;
	
	public int getCardID() {
		
		return this.cardID;
	};
	
	
	
	public String getCardName() {
		return this.cardName;
	};
	
	// Verteilt die Coins bei gezogener Karte
	public static int distributeCoinsCardA(int cardID) {
		int handSize = Hand.getHandSize(cardID);
		int disCoins = 0; // coins which will distribute 
		int disMeeples;
		switch (cardID) {
        case 0:
        	disCoins = handSize*2;
        	disMeeples = handSize;
        	Hand.meeples = Hand.meeples+disMeeples;
        	break;
        case 1:
        	if(Hand.hand[1]>=1 && Hand.hand[4]>=1 && Hand.hand[6]>=1) {
        		disCoins = 2;
        	}
        	break;
        case 2:
        	disCoins = handSize*2;
        	break;
        case 3:
        	disCoins = handSize*5;
        	disMeeples = handSize;
        	Hand.meeples = Hand.meeples+disMeeples;
        	break;
        case 4:
        	disCoins = handSize*3;
        	break;
        case 5:
        	int farmer = Hand.hand[2];
        	int brewer = Hand.hand[0];
        	int witch = Hand.hand[5];
        		disCoins = (farmer+brewer+witch)*2;
        	break;
        case 6:
        	disCoins = handSize*4;
        	break;
	}
		return handSize;
	
	}
}
