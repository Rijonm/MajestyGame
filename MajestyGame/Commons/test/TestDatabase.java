package test;

import java.util.HashMap;
import java.util.Map;

import CommonClasses.Player;

public class TestDatabase {
	
	// Tabelle "player"
	private static Map<Integer, Player> players = new HashMap<Integer, Player>();
	// playerId counter
	private static int lastInsertedPlayerId = 0;
	
	public static Map<Integer, Player> getPlayers() {
		return players;
	}
	
	/**
	 * 
	 * @param player
	 * @return -1 if username already exists, else the playerId of the inserted player
	 */
	public static int insertPlayer(Player player) {
		int newPlayerId = -1;
		
		// if unique insert new player
		if (isUniqueUsername(player.getUsername())) {
			newPlayerId = ++lastInsertedPlayerId;
			player.setPlayerId(newPlayerId);
			player.setHighscore(0);
			player.setOnline(false);
			players.put(newPlayerId, player);
		}
		return newPlayerId;
	}
	
	public static Player getPlayer(int playerId) {
		return new Player(players.get(playerId));
	}
	
	public static Player checkLogin(String username, String password) {
		Player player = null;
		for (Player e : players.values()) {
			if (e.getUsername().equalsIgnoreCase(username) 
					&& e.getPassword().equals(password)) {
				player = e;
				break;
			}
		}
		return new Player(player);
	}
	
	public static Player update(Player player) {
		int playerId = player.getPlayerId();
		Player playerToUpdate = players.get(playerId);
		
		if (playerToUpdate != null) {
			String username = player.getUsername();
			String password = player.getPassword();
			boolean online = player.isOnline();
			int highscore = player.getHighscore();
			
			if (!playerToUpdate.getUsername().equals(username) 
					&& isUniqueUsername(username)) {
				playerToUpdate.setUsername(username);
			}
			if (!playerToUpdate.getPassword().equals(password)) {
				playerToUpdate.setPassword(password);
			}
			if (playerToUpdate.isOnline() != online) {
				playerToUpdate.setOnline(online);	
			}
			if (playerToUpdate.getHighscore() != highscore) {
				playerToUpdate.setHighscore(highscore);
			}
		}
		return new Player(playerToUpdate);
	}
	
	private static boolean isUniqueUsername(String username) {
		// check unique username
		boolean unique = true;
		for (Player e : players.values()) {
			if (e.getUsername().compareToIgnoreCase(username) == 0) {
				unique = false;
			}
		}
		return unique;
	}
}
