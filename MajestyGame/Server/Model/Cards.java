package Model;

public abstract class Cards {
	
	//@author Mert Emek
	
	protected int CardID;
	protected String CardName;
	protected int CardQuantity;
	protected int CardCoins;
	
	public Cards(int CardID, String CardName, int CardQuantity, int CardCoins) {
		this.CardID = CardID;
		this.CardName = CardName;
		this.CardQuantity = CardQuantity;
		this.CardCoins = CardCoins;
	}
	public Cards(int CardID, String CardName, int CardCoins) {
		this.CardID = CardID;
		this.CardName = CardName;
		this.CardCoins = CardCoins;
	}
	
	public void setCardID(int c) {
		this.CardID = c;
	}
	
	// set Quantity of each Card Group
	public void setCardQuantity(int q) {
		this.CardQuantity = q;
	}
	
	//to set the amount of Coins of each Player Card
	public void setCardCoins(int CardCoins) {
		this.CardCoins = CardCoins;
	};
	
	public int getCardID() {
		return this.CardID;
	};
	
	public int getCardQuantity() {
		return this.CardQuantity;
	};
	
	public int getCardCoins() {
		return this.CardCoins;
	};
	
	public String getCardName() {
		return this.CardName;
	};
	
	
	
}
