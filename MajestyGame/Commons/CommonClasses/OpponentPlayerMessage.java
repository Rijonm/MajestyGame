package CommonClasses;

import java.util.List;

public class OpponentPlayerMessage extends Message{

	int id;
	String username;
	Integer[] hand;
	int meeples;

	public OpponentPlayerMessage(int id, String username, Integer[] hand, int meeples) {
		super(MessageType.OpponentPlayerMessage);
		this.id = id;
		this.username = username;
		this.hand = hand;
		this.meeples = meeples;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Integer[] getHand() {
		return hand;
	}

	public int getMeeples() {
		return meeples;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setHand(Integer hand[]) {
		this.hand = hand;
	}

	public void setMeeples(int meeples) {
		this.meeples = meeples;
	}

}
