package CommonClasses;

/**
 * 
 * @author livio
 *
 */
public class LoginSuccessMessage extends Message {

	public enum State {SUCCESS, COULD_NOT_CONNECT, FAILURE, WRONG_LOGIN};

	private State loginState;

	public LoginSuccessMessage(State state) {
		super(MessageType.LoginSuccessMessage);
		this.loginState = state;
	}

	public State getState() {
		return loginState;
	}

}
