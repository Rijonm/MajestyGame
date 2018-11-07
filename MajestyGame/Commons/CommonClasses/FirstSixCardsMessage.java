package CommonClasses;

public class FirstSixCardsMessage extends Message{

	private int[] firstSixCards;
	public FirstSixCardsMessage(int ... firstSixCards) {
		super(MessageType.FirstSixCardsMessage);
		this.firstSixCards = firstSixCards;
	}

}
