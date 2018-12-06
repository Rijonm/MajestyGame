package CommonClasses;

public class CardFromServerMessage extends Message{

	private int[] openCards;
	private int[] meeples;
	public CardFromServerMessage(int[] openCards, int[] meeples) {
		super(MessageType.CardFromServerMessage);
		
		this.openCards = openCards;
		this.meeples = meeples;
	}

}
