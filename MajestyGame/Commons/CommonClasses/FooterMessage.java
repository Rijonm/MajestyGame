package CommonClasses;

public class FooterMessage extends Message{
	
	int turnId;
	int playedCards;
	int round;
	
	public FooterMessage(int turnId, int playedCards, int round) {
		super(MessageType.FooterMessage);
		this.turnId = turnId;
		this.playedCards = playedCards;
		this.round = round;
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

}
