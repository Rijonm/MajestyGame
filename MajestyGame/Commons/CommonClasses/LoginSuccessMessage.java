package CommonClasses;

public class LoginSuccessMessage extends Message{

	// livio
	public enum State {SUCCESS, COULD_NOT_CONNECT, FAILURE, WRONG_LOGIN, WRONG_USERNAME, WRONG_PASSWORD};

	private boolean success;
	private State loginState; // livio

	public LoginSuccessMessage(boolean success, State state /* livio */) {
		super(MessageType.LoginSuccessMessage);
		this.success = success;
		this.loginState = state; // livio
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	// livio
	public State getState() {
		return loginState;
	}

}
