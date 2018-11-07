package CommonClasses;

public class ChatMessage extends Message{

	private String user;
	private String message;
	public ChatMessage(String user, String message) {
		super(MessageType.ChatMessage);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUser() {
		return user;
	}

}
