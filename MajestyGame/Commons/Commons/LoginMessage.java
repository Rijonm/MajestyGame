package Commons;

public class LoginMessage extends Message {
	
	private String username;
	private String password;
	
	public LoginMessage(String username, String password) {
		super(MessageType.UserLoginMessage);
		this.username = username;
		this.password = password;
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
