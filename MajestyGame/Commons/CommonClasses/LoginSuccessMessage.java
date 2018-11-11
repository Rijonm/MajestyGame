package CommonClasses;

public class LoginSuccessMessage extends Message{

	// livio
	public enum LoginState {SUCCESS, COULD_NOT_CONNECT, GENERAL_ERROR, WRONG_LOGIN, WRONG_USERNAME, WRONG_PASSWORD};

	private boolean success;
	private LoginState loginState; // livio

	public LoginSuccessMessage(boolean success, LoginState state /* livio */) {
		super(MessageType.LoginSuccessMessage);
		this.success = success;
		this.loginState = state; // livio
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	// livio
	public LoginState getState() {
		return loginState;
	}

}
