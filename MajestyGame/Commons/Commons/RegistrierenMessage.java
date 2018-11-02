package Commons;

import java.io.Serializable;

public class RegistrierenMessage extends Message implements Serializable{
	private String username;
	private String password;

	public RegistrierenMessage(String username, String password) {
		super(MessageType.UserRegistratedMessage);
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
