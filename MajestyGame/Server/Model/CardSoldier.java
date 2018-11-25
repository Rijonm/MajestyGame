package Model;

public class CardSoldier extends Cards{
	
	//@author Mert Emek


	public CardSoldier(int CardID, String CardName, int CardQuantity, int CardCoins) {
		super(CardID, CardName, CardQuantity, CardCoins);
		// TODO Auto-generated constructor stub
	}

	//for attack players hand
		public static void attack() {
			int DefensOfOtherPlayers = Model.Hand.Defense.size();
			
			if(DefensOfOtherPlayers < Model.Hand.Soldier.size()) {
				
			}
			
			
			
		
			
		};

	
	
	
	
	
	
	

}
