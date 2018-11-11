package CommonClasses;

import java.util.List;

public class PlayersMessage extends Message{
	
	public enum State {SUCCESS, FAILURE, COULD_NOT_CONNECT}
	
	private List<Player> players;
	private State state;

	public PlayersMessage(List<Player> players, State state) {
		super(MessageType.PlayersMessage);
		this.players = players;
		this.state = state;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public State getState() {
		return state;
	}
}
