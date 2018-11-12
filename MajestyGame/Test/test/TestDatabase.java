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
	 * @return -1 if username alreay exists, else the playerId of the inserted player
	 */
	public static int insertPlayer(Player player) {
		String usernameNew = player.getUsername();
		int playerIdNew = -1;
		
		// check unique username
		boolean unique = true;
		for (Player e : players.values()) {
			if (e.getUsername().compareToIgnoreCase(usernameNew) == 0) {
				unique = false;
			}
		}
		
		// if unique insert new player
		if (unique) {
			playerIdNew = ++lastInsertedPlayerId;
			player.setPlayerId(playerIdNew);
			player.setHighscore(0);
			player.setOnline(false);
			players.put(playerIdNew, player);
		}
		return playerIdNew;
	}
}
