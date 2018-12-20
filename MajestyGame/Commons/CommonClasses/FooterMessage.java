package CommonClasses;

public class FooterMessage extends Message{
	
	int turnId;
	int playedCards;
	int round;
	public String turnUsername;
	
	public FooterMessage(String turnUsername, int turnId, int playedCards, int round) {
		super(MessageType.FooterMessage);
		this.turnId = turnId;
		this.playedCards = playedCards;
		this.round = round;
		this.turnUsername = turnUsername;
	}

	public int getTurnId() {
		return turnId;
	}

	public int getPlayedCards() {
		return playedCards;
	}

	public int getRound() {
		return round;
	}
	
	public String getTurnUsername() {
		return turnUsername;
	}

}
