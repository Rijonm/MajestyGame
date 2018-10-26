package Commons;

public class LoginMessage extends Message {
	
	private String username;
	private String password;
	
	public LoginMessage(String username, String password) {
		super(MessageType.UserLogin);
		this.username = username;
		this.password = password;
		
	}

}
