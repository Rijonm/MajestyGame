package CommonClasses;

public class EvaluateGameMessage extends Message{

	private enum EvaluationType {Winner, Loser,  Drawn};
	private EvaluationType type;
	
	public EvaluateGameMessage(EvaluationType type) {
		super(MessageType.EvaluateGameMessage);
		this.type = type;
	}
	
	

}
