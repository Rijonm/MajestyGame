package CommonClasses;

public class FirstSixCardsMessage extends Message{

	private Integer[] firstSixCards = new Integer[6];
	
	public FirstSixCardsMessage(int a, int b, int c, int d, int e, int f) {
		super(MessageType.FirstSixCardsMessage);
		this.firstSixCards[0] = a;
		this.firstSixCards[1] = b;
		this.firstSixCards[2] = c;
		this.firstSixCards[3] = d;
		this.firstSixCards[4] = e;
		this.firstSixCards[5] = f;
	}
	
	public Integer[] getFirstSixCards() {
		return firstSixCards;
	}

}
