package CommonClasses;

public class RegisterSuccessMessage extends Message{
	
	public enum RegisterMessage {
		UserAllreadyExsists,
		Success,
		CouldNotConnectToDB
	};
	RegisterMessage message;

	public RegisterSuccessMessage(RegisterMessage message) {
		super(MessageType.RegisterSuccessMessage);
		this.message = message;
	}
	
	public String getRegisterMessageString() {
		return message.toString();
	}
	
	public RegisterMessage getRegisterMessageTpye() {
		return message;
	}
}
