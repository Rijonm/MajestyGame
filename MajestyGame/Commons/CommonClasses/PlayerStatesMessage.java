package CommonClasses;

public class PlayerStatesMessage extends Message{
	
	public int id;
	public Integer[] hand = new Integer[8];
	public int meeples;
	public int coins;
	
	public PlayerStatesMessage(int id, Integer[] hand, int meeples, int coins) {
		super(MessageType.PlayerStatesMessage);
		this.id = id;
		//this.hand = hand;
		this.hand[0] = hand[0];
		this.hand[1] = hand[1];
		this.hand[2] = hand[2];
		this.hand[3] = hand[3];
		this.hand[4] = hand[4];
		this.hand[5] = hand[5];
		this.hand[6] = hand[6];
		this.hand[7] = hand[7];
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
