package CommonClasses;

public class RegisterSuccessMessage extends Message{
	
	private enum RegisterMessage{PasswortCorrect, PasswortIncorrect, UserAllreadyExsists, CouldNotConnectToDB};
	RegisterMessage failMessage;

	public RegisterSuccessMessage(RegisterMessage failMessage) {
		super(MessageType.RegisterSuccessMessage);
		this.failMessage = failMessage;
		
		
	}
	
	public String getRegisterMessage() {
		return failMessage.toString();
	}
	
	

}
