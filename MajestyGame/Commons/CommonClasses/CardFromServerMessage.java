package CommonClasses;

public class CardFromServerMessage extends Message{

	private int cardID;
	public CardFromServerMessage(int cardID) {
		super(MessageType.CardFromServerMessage);
		
		this.cardID = cardID;
	}

}
