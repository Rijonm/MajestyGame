package CommonClasses;

/**
 * 
 * @author livio
 *
 */
public class LoginSuccessMessage extends Message {

	public enum State {SUCCESS, COULD_NOT_CONNECT, FAILURE, WRONG_LOGIN};

	private State loginState;
	private int id;

	public LoginSuccessMessage(State state) {
		super(MessageType.LoginSuccessMessage);
		this.loginState = state;
	}
	
	public LoginSuccessMessage(State state, int id) {
		super(MessageType.LoginSuccessMessage);
		this.loginState = state;
		this.id = id;
	}

	public State getState() {
		return loginState;
	}
	
	public int getId() {
		return id;
	}

}
