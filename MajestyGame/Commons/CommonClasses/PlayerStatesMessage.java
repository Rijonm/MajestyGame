package CommonClasses;

public class PlayerStatesMessage extends Message{
	
	int id;
	Integer[] hand;
	int meeples;
	int coins;
	
	public PlayerStatesMessage(int id, Integer[] hand, int meeples, int coins) {
		super(MessageType.PlayerStatesMessage);
		this.id = id;
		this.hand = hand;
		this.meeples = meeples;
		this.coins = coins;
	}

	public int getId() {
		return id;
	}

	public Integer[] getHand() {
		return hand;
	}

	public int getMeeples() {
		return meeples;
	}

	public int getCoins() {
		return coins;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHand(Integer[] hand) {
		this.hand = hand;
	}

	public void setMeeples(int meeples) {
		this.meeples = meeples;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	

}
