package CommonClasses;

public class LoginSuccessMessage extends Message{

	// livio
	private enum FailureType{WRONG_USERNAME, WRONG_PASSWORD};

	private boolean success;
	private FailureType failure; // livio

	public LoginSuccessMessage(boolean success, FailureType failure /* livio */) {
		super(MessageType.LoginSuccessMessage);
		this.success = success;
		this.failure = failure; // livio
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	// livio
	public FailureType getFailure() {
		return failure;
	}

}
