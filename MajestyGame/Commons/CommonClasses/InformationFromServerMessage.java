package CommonClasses;

public class InformationFromServerMessage extends Message{

	private Integer[] openCards;
	private Integer[] meeples;
	
	public InformationFromServerMessage(Integer[] openCards, Integer[] meeples) {
		super(MessageType.InformationFromServerMessage);
		
		this.openCards = openCards;
		this.meeples = meeples;
	}

	public Integer[] getOpenCards() {
		return openCards;
	}

	public Integer[] getMeeples() {
		return meeples;
	}
	
}
