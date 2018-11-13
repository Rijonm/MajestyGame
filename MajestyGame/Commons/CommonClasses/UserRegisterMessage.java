package CommonClasses;

public class UserRegisterMessage extends Message {
	private String username;
	private String password;

	public UserRegisterMessage(String username, String password) {
		super(MessageType.UserRegisterMessage);
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
