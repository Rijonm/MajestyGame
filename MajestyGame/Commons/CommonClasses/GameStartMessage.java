package CommonClasses;

public class GameStartMessage extends Message{

	private String playerName;
	
	public GameStartMessage(String playerName) {
		super(MessageType.GameStartMessage);
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	
	}
}
