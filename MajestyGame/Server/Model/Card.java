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
	
	
}
