package CommonClasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class Message implements Serializable {
 
	MessageType messageType;
	
	public Message(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public static void send(Socket socket, Message message) {
		try {
			OutputStream ops = socket.getOutputStream();
			ObjectOutputStream oops = new ObjectOutputStream(ops);
			
			oops.writeObject(message);
			oops.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Message receive(Socket socket) throws IOException, ClassNotFoundException {
		InputStream ips = socket.getInputStream();
		ObjectInputStream oips = new ObjectInputStream(ips);
		Message message = (Message) oips.readObject();
		System.out.println("message");
		return message;
		
	}

	public MessageType getMessageType() {
		return messageType;
	}


}
