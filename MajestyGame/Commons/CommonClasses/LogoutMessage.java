package CommonClasses;

/**
 * 
 * @author livio
 *
 */
public class LogoutMessage extends Message {
	
	public enum LogoutState {SUCCESS, FAILURE, COULD_NOT_CONNECT};
	
	private LogoutState state;

	public LogoutMessage(LogoutState state) {
		super(MessageType.LogoutMessage);
		this.state = state;
	}
	
	public LogoutState getState() {
		return state;
	}
}
