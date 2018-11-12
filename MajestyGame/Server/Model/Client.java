package Model;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import CommonClasses.Message;
import CommonClasses.MessageType;
import CommonClasses.UserLoginMessage;
/**
 *Wenn ein neuer Client gestartet wird und der sich mit dem Server Connected wird ein neuer Client-Objekt
 *instanziiert.
 *
 *Hier werden die eingeheden Messages gehandlet.
 *
 * @author rijon
 *
 */
public class Client implements Serializable {
	
	private UserLoginMessage lmsg;

    private int id;
    
    private String userName;
    private String password;
    private Hand hand;
    
    private int points;
    
    public Client(ServerModel m, Socket socket){
    	
    	Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						
						Message msg = Message.receive(socket);
						
						if(msg.getMessageType() == MessageType.UserRegisterMessage) {
							
						}
						if (msg.getMessageType() == MessageType.UserLoginMessage) {
							lmsg = (UserLoginMessage) msg;
							System.out.println(lmsg.getUsername() + " " +lmsg.getPassword());
						}
						if(msg.getMessageType() == MessageType.GameStartMessage) {
							
						}
						if(msg.getMessageType() == MessageType.PlayerMoveMessage) {
							
						}
						if(msg.getMessageType() == MessageType.ChatMessage) {
							
						}
						
						
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
    	
    }
}
