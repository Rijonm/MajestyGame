package CommonClasses;

import java.util.List;

public class WinnerMessage extends Message{
	
	private List<String> winners;

	public WinnerMessage(List<String> winners) {
		super(MessageType.WinnerMessage);
		this.winners = winners;
	}
	
	public List<String> getPlayers() {
		return winners;
	}

}
