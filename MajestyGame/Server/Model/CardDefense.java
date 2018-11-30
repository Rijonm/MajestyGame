package Model;

public class CardDefense extends Card{
	
	//@author Mert Emek

	

	public CardDefense(int cardID, String cardName, int cardQuantity, int cardCoins) {
		super(cardCoins, cardName, cardCoins, cardCoins);
		// TODO Auto-generated constructor stub
	}
	//for defense
	public void defend() {
		
	};
	public int getCardQuantity() {
		return this.cardQuantity;
	};

}
