package CommonClasses;

/**
 * 
 * @author livio
 *
 */
public class UserLogout extends Message {
	
	private int playerId;

	public UserLogout(int playerId) {
		super(MessageType.UserLogout);
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return playerId;
	}
}
