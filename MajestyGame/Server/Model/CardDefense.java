package Model;

public class CardDefense extends Cards{
	
	//@author Mert Emek

	

	public CardDefense(int CardID, String CardName, int CardQuantity, int CardCoins) {
		super(CardCoins, CardName, CardCoins, CardCoins);
		// TODO Auto-generated constructor stub
	}
	//for defense
	public void defend() {
		
	};
	public int getCardQuantity() {
		return this.CardQuantity;
	};

}
