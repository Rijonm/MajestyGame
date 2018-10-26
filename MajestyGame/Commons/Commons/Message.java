package Commons;

import java.io.Serializable;

public abstract class Message implements Serializable {
	
	MessageType messageType;
	
	public Message(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public void send() {
		
	}
	
	public Message receive() {
		Message message = null;
		
		return message;
		
	}

}
