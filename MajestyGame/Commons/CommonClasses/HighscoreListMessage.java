package CommonClasses;

import java.util.List;

public class HighscoreListMessage extends Message {
	
	private List<Player> players; 

	public HighscoreListMessage(List<Player> players) {
		super(MessageType.HighscoreListMessage);
		this.players = players;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
}
