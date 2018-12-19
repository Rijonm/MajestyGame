package CommonClasses;

public class OtherPlayerLoggedOutMessage extends Message {

	private int playerId;

	public OtherPlayerLoggedOutMessage(int playerId) {
		super(MessageType.OtherPlayerLoggedOutMessage);
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
