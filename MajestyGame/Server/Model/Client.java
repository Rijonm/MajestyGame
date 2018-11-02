package Model;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import Commons.LoginMessage;
import Commons.Message;
import Commons.MessageType;

public class Client implements Serializable {
	
	private LoginMessage lmsg;

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
						
						if (msg.getMessageType() == MessageType.UserLoginMessage) {
							lmsg = (LoginMessage) msg;
							System.out.println(lmsg.getUsername() + " " +lmsg.getPassword());
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
