package Model;

public abstract class Card {
	
	//@author Mert Emek
	
	protected int cardID;
	protected String cardName;
	protected int cardQuantity;
	protected int cardCoins;
	
	public Card(int CardID, String CardName, int CardQuantity, int CardCoins) {
		this.cardID = CardID;
		this.cardName = CardName;
		this.cardQuantity = CardQuantity;
		this.cardCoins = CardCoins;
	}
	public Card(int CardID, String CardName, int CardCoins) {
		this.cardID = CardID;
		this.cardName = CardName;
		this.cardCoins = CardCoins;
	}
	
	public void setCardID(int c) {
		this.cardID = c;
	}
	
	// set Quantity of each Card Group
	public void setCardQuantity(int q) {
		this.cardQuantity = q;
	}
	
	//to set the amount of Coins of each Player Card
	public void setCardCoins(int CardCoins) {
		this.cardCoins = CardCoins;
	};
	
	public int getCardID() {
		
		return this.cardID;
	};
	
	public int getCardQuantity() {
		return this.cardQuantity;
	};
	
	public int getCardCoins() {
		return this.cardCoins;
	};
	
	public String getCardName() {
		return this.cardName;
	};
	
	
	
}
