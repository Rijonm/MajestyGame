package View;

public abstract class Cards {
	
	//code by Mert Emek
	
	int CardID;
	String CardName = null;
	int CardQuantity;
	int CardCoins;
	
	public Cards(String CardName) {
		this.CardName = CardName;
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
	
	
	//for soldier
	public abstract void attack();
	
	//for defense
	public abstract void defend();
	
}
