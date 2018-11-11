package CommonClasses;

/**
 * 
 * @author livio
 *
 */
public class LogoutMessage extends Message {
	
	public enum State {SUCCESS, FAILURE, COULD_NOT_CONNECT};
	
	private State state;

	public LogoutMessage(State state) {
		super(MessageType.LogoutMessage);
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
}
