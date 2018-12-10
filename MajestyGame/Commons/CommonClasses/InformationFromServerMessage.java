package CommonClasses;

public class InformationFromServerMessage extends Message{

	private Integer[] openCards;
	private int[] meeples;
	
	public InformationFromServerMessage(Integer[] openCards) {
		super(MessageType.InformationFromServerMessage);
		
		this.openCards = openCards;
		this.meeples = meeples;
	}

	public Integer[] getOpenCards() {
		return openCards;
	}
	
}
