package CommonClasses;

public class PlayerMessage extends Message{
	
	public enum State {SUCCESS, PLAYER_NOT_FOUND, FAILURE, COULD_NOT_CONNECT}
	
	private Player player;
	private State state;

	public PlayerMessage(Player player, State state) {
		super(MessageType.PlayerMessage);
		this.player = player;
		this.state = state;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public State getState() {
		return state;
	}
}
