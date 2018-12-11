package CommonClasses;

public class PlayerMoveMessage extends Message{

	private int positionPlayed;
	private int cardID;
	private int playerID;
	
	public PlayerMoveMessage(int pos, int cardID, int playerID) {
		super(MessageType.PlayerMoveMessage);
		this.positionPlayed = pos;
		this.cardID = cardID;
		this.playerID = playerID;
	}
	
	public int getPositionPlayed() {
		return positionPlayed;
	}

	public int getCardID() {
		return cardID;
	}
	
	public int getPlayerID() {
		return playerID;
	}

}
