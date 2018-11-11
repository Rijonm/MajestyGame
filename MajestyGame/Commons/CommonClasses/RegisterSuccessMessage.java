package CommonClasses;

public class RegisterSuccessMessage extends Message{
	
	public enum State {
		USER_ALREADY_EXISTS,
		SUCCESS,
		COULD_NOT_CONNECT
	};
	private State state;

	public RegisterSuccessMessage(State state) {
		super(MessageType.RegisterSuccessMessage);
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
}
