package CommonClasses;

public class LoginSuccessMessage extends Message{
	
	private boolean success;

	public LoginSuccessMessage(boolean success) {
		super(MessageType.LoginSuccessMessage);
		this.success = success;
	}
	
	public boolean getSuccess() {
		return success;
	}

}
