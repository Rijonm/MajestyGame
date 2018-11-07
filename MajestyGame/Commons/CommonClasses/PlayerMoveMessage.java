package CommonClasses;

public class PlayerMoveMessage extends Message{

	private int positionPlayed;
	
	public PlayerMoveMessage(int pos) {
		super(MessageType.PlayerMoveMessage);
		this.positionPlayed = pos;
	}
	
	public int getPositionPlayed() {
		return positionPlayed;
	}

}
