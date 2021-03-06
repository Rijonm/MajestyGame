package CommonClasses;

import java.io.Serializable;

public class Player implements Serializable {

	private int playerId;
	private String username;
	private String password;
	private boolean online;
	private int highscore;
	
	public Player() {
		// empty constructor
	}
	
	public Player(Player player) {
		setPlayerId(player.getPlayerId());
		setUsername(player.getUsername());
		setPassword(player.getPassword());
		setOnline(player.isOnline());
		setHighscore(player.getHighscore());
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
}
