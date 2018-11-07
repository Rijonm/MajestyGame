package CommonClasses;

public class LobbyInformationMessage extends Message{
	
	private  enum StatusType {playerLogin, playerLogout };
	private StatusType state;
	private String playerName;
	private int playerID;
	
	public LobbyInformationMessage(String playerName, int playerID, StatusType s) {
		super(MessageType.LobbyInformationMessage);
		this.playerName = playerName;
		this.playerID = playerID;
		this.state = s;
		
	}
	
	

}
