package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.Player;
import CommonClasses.PlayerMessage;
import CommonClasses.PlayersMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;
import api.Api;

/**
 * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html
 * 
 * Falls ein Fehler mit der Zeitzone auftritt: default-time-zone im MySQL Config
 * File setzen
 * 
 * fuer Windows:
 * 
 * folgende Datei oeffnen: "C:\ProgramData\MySQL\MySQL Server 5.7\my.ini"
 * 
 * folgendes am Ende der Datei anfuegen (Winterzeit: +01:00, Sommerzeit: +02:00):
 * default-time-zone='+01:00'
 * 
 * speichern und MySQL Service neu starten
 * 
 * @author livio
 *
 */
public class Database implements Api {

	private static final String DB_NAME = "majesty";
	private static final String DB_USER = "majesty";
	private static final String DB_PASSWORD = "majesty2018";
	private static final String DB_HOST = "localhost";
	// jdbc:mysql://localhost/majesty?user=majesty&password=majesty2018
	private static final String CONNECTION_URL = "jdbc:mysql://" + DB_HOST + "/" 
			+ DB_NAME 
			+ "?user=" + DB_USER 
			+ "&password=" + DB_PASSWORD;
	
	private static final String TABLE_PLAYER = "player";
	
	private static final String COL_PLAYER_ID = "playerId";
	private static final String COL_PASSWORD = "password";
	private static final String COL_USERNAME = "username";
	private static final String COL_ONLINE = "online";
	private static final String COL_HIGHSCORE = "highscore";
	
	// Queries
	// Registration
	private static final String QUERY_REGISTER = "insert into " + TABLE_PLAYER // Tabelle 
			+ " (" + COL_USERNAME + "," + COL_PASSWORD + ") " // zu f�llende Felder
			+ " values (?,?)"; // Werte f�r Felder
	// Login pr�fen
	private static final String QUERY_CHECK_LOGIN = "select * from " + TABLE_PLAYER // Tabelle
			+ " where " + COL_USERNAME + " = ? and " + COL_PASSWORD + " = ?"; // nach Benutzername und Passwort suchen
	// online setzen
	private static final String QUERY_SET_ONLINE = "update " + TABLE_PLAYER // Tabelle
			+ " set " + COL_ONLINE + " = ?" // online setzen
			+ " where " + COL_PLAYER_ID + " = ?"; // nach Benutzer ID suchen
	// online Benutzer
	private static final String QUERY_GET_ONLINE_PLAYERS = "select * from " + TABLE_PLAYER // Tabelle
			+ " where " + COL_ONLINE + " = ?" // nach Online Status suchen
			+ " order by " + COL_USERNAME; // sortieren
	// Benutzer suchen
	private static final String QUERY_GET_PLAYER = "select * from " + TABLE_PLAYER // Tabelle
			+ " where " + COL_PLAYER_ID + " = ?"; // nach Benutzer ID suchen
	// alle Benutzer auslesen
	private static final String QUERY_GET_PLAYERS = "select * from " + TABLE_PLAYER // Tabelle
			+ " order by %s %s" // soriteren nach
			+ " limit ?"; // nur die obersten X Benutzer anzeigen
	// Highscore setzen
	private static final String QUERY_SET_HIGHSCORE = "update " + TABLE_PLAYER // Tabelle
			+ " set " + COL_HIGHSCORE + "= ?" // Highscore setzen
			+ " where " + COL_PLAYER_ID + " = ?"; // nach Benutzer ID suchen

	private static Connection connection;

	/**
	 * f�r das Testen der Klassen Funktionen
	 * @param args
	 */
//	public static void main(String[] args) {
//		Database db = new Database();
//		// testing
//		
//		// Registration
//		System.out.println(db.register(new UserRegisterMessage("user1", "pw1")).getState());
//		
//		// Login
//		System.out.println(db.login(new UserLoginMessage("user1", "pw1")).getState());
//		
//		// Logout
//		System.out.println(db.logout(new UserLogout(1)).getState());
//		
//		// Online Benutzer auslesen
//		LoggedInPlayers loggedInPlayersMessage = db.getLoggedInPlayers();
//		List<Player> loggedInPlayers = loggedInPlayersMessage.getPlayers();
//		if (loggedInPlayers != null) {
//			for (Player player : loggedInPlayers) {
//				System.out.println(player.getPlayerId()+":"+player.getUsername()+":"+player.getHighscore());
//			}
//		}
//		
//		// Benutzer mit ID suchen
//		System.out.println(db.getPlayer(1).getState());
//
//		// Benutzer auslesen
//		PlayersMessage playersMessage = db.getPlayers(COL_USERNAME, false, 4);
//		List<Player> players = playersMessage.getPlayers();
//		if (players != null) {
//			for (Player player : players) {
//				System.out.println(player.getPlayerId()+":"+player.getUsername()+":"+player.getHighscore());
//			}
//		}
//		
//		// Highscore setzen
//		Player oldHighscorePlayer = db.setHighscore(1, 100).getPlayer();
//		Player newHighscorePlayer = db.setHighscore(1, 101).getPlayer();
//		Player newestHighscorePlayer = db.setHighscore(1, 99).getPlayer();
//		if (oldHighscorePlayer != null 
//				&& newHighscorePlayer != null 
//				&& newestHighscorePlayer != null) {
//			System.out.println("first highscore 100: " + oldHighscorePlayer.getHighscore());
//			System.out.println("second highscore 101: " + newHighscorePlayer.getHighscore());
//			System.out.println("third highscore 99: " + newestHighscorePlayer.getHighscore());
//		}
//	}

	/**
	 * initialisiert die Datenbank-Verbindung
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException 
	 */
	private static void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO: Datenbank Verbindungs URL erstellen
		String connectionUrl = "";
		
		
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); // erstellt Instanz von der Library Klasse f�r den Datenbank Treiber
		connection = DriverManager.getConnection(CONNECTION_URL); // �ffnet Verbindung zur Datenbank �ber die URL mit dem Benutzer und Passwort
		System.out.println("database init");
	}

	/**
	 * Falls die Datenbank-Verbindung nicht initialisiert oder geschlossen wurde, wird diese initialisiert und anschliessend zur�ckgegeben.
	 * 
	 * @return Verbindung f�r Datenbank Interaktionen
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException Verbindung zur Datenbank konnte nicht hergestellt werden
	 */
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// pr�fen ob Verbindung schon erstellt (singleton)
		if (connection == null || connection.isClosed()) {
			init();
		}
		return connection;
	}

	// API Funktionen
	@Override
	public RegisterSuccessMessage register(UserRegisterMessage register) {
		System.out.println("register");
		RegisterSuccessMessage message = null;
		PreparedStatement statement = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(QUERY_REGISTER);
				statement.setString(1, register.getUsername()); // Benutzername
				statement.setString(2, register.getPassword()); // Passwort
				
				System.out.println(statement.toString()); // loggen des Query
				
				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				statement.executeUpdate(); // ausf�hren 
				
				message = new RegisterSuccessMessage(RegisterSuccessMessage.State.SUCCESS); // erfolgreich registriert
			} catch (SQLException e) {
				sqlError(e);
				message = new RegisterSuccessMessage(RegisterSuccessMessage.State.USER_ALREADY_EXISTS); // Benutzer exisitiert bereits
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new RegisterSuccessMessage(RegisterSuccessMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	@Override
	public LoginSuccessMessage login(UserLoginMessage login) {
		System.out.println("login");
		LoginSuccessMessage message = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(QUERY_CHECK_LOGIN);
				statement.setString(1, login.getUsername()); // Benutzername
				statement.setString(2, login.getPassword()); // Passwort
				
				System.out.println(statement.toString()); // loggen des Query

				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				result = statement.executeQuery(); // ausf�hren

				// wurde eine Benutzername/Passwort Kombination gefunden?
				// gefunden
				if (result.first()) {
					// Benutzer online setzen
					int playerId = result.getInt(COL_PLAYER_ID); // eindeutige Benutzer ID auslesen
					// Query vorbereiten
					statement = getConnection().prepareStatement(QUERY_SET_ONLINE);
					statement.setObject(1, 1, Types.TINYINT);
					statement.setInt(2, playerId);

					System.out.println(statement.toString()); // loggen des Query

					statement.closeOnCompletion(); // schliessen nach Ausf�hrung
					statement.executeUpdate(); // ausf�hren
					
					message = new LoginSuccessMessage(LoginSuccessMessage.State.SUCCESS, playerId); // erfolgreich eingeloggt
				}
				// nicht gefunden
				else {
					message = new LoginSuccessMessage(LoginSuccessMessage.State.WRONG_LOGIN); // falsche Login Daten
				}
			} catch (SQLException e) {
				sqlError(e);
				message = new LoginSuccessMessage(LoginSuccessMessage.State.FAILURE); // konnte Query nicht ausf�hren
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new LoginSuccessMessage(LoginSuccessMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}
	
	@Override
	public LogoutMessage logout(UserLogout logout) {
		System.out.println("logout");
		LogoutMessage message = null;
		PreparedStatement statement = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(QUERY_SET_ONLINE);
				statement.setObject(1, 0, Types.TINYINT);
				statement.setInt(2, logout.getPlayerId()); // WICHTIG! es kann auch ein nicht existierender (playerId) Benutzer ausgeloggt werden! Kein Fehler!
				
				System.out.println(statement.toString()); // loggen des Query

				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				statement.executeUpdate(); // ausf�hren
				
				message = new LogoutMessage(LogoutMessage.State.SUCCESS); // erfolgreich ausgeloggt
			} catch (SQLException e) {
				sqlError(e);
				message = new LogoutMessage(LogoutMessage.State.FAILURE); // konnte Benutzer nicht auf offline stellen
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new LogoutMessage(LogoutMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message ;
	}

	@Override
	public LoggedInPlayers getLoggedInPlayers() {
		System.out.println("getLoggedInPlayers");
		LoggedInPlayers message = null;
		List<Player> players = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(QUERY_GET_ONLINE_PLAYERS);
				statement.setObject(1, 1, Types.TINYINT); // online
				
				System.out.println(statement.toString()); // loggen des Query

				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				result = statement.executeQuery(); // ausf�hren
				
				players = resultSetToPlayerList(result); // SQL Resultat zu Benutzer Liste
				
				System.out.println(players.size() + " online players"); // loggen der Anzahl Online Benutzer
				
				message = new LoggedInPlayers(players, LoggedInPlayers.State.SUCCESS); // Online Benutzer erfolgreich ausgelesen
			} catch (SQLException e) {
				sqlError(e);
				message = new LoggedInPlayers(null, LoggedInPlayers.State.FAILURE); // konnte Benutzer nicht auslesen
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new LoggedInPlayers(null, LoggedInPlayers.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	@Override
	public PlayerMessage getPlayer(int playerId) {
		System.out.println("getPlayer");
		PlayerMessage message = null;
		Player player = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(QUERY_GET_PLAYER);
				statement.setInt(1, playerId);
				
				System.out.println(statement.toString()); // loggen des Query

				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				result = statement.executeQuery(); // ausf�hren
				
				// wurde ein Benutzer mit gegebener ID gefunden?
				// gefunden
				if (result.first()) {
					player = resultSetToPlayer(result); // gefundener Benutzer in Benutzer Objekt umwandeln
					
					System.out.println(player.getPlayerId()+":"+player.getUsername()+":"+player.getHighscore()); // loggen des gefundenen Benutzers
					
					message = new PlayerMessage(player, PlayerMessage.State.SUCCESS); // Benutzer gefunden
				}
				// nicht gefunden
				else {
					message = new PlayerMessage(null, PlayerMessage.State.PLAYER_NOT_FOUND); // Benutzer nicht gefunden
				}
			} catch (SQLException e) {
				sqlError(e);
				message = new PlayerMessage(null, PlayerMessage.State.FAILURE); // konnte Query nicht ausf�hren
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new PlayerMessage(null, PlayerMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	@Override
	public PlayerMessage setHighscore(int playerId, int highscore) {
		System.out.println("setHighscore");
		PlayerMessage message = null;
		PreparedStatement statement = null;
		Player oldPlayer = null;
		Player player = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// prüfen, ob neue Highscore höher ist
				oldPlayer = getPlayer(playerId).getPlayer();
				if (oldPlayer != null && oldPlayer.getHighscore() < highscore) {				
					// Query vorbereiten
					statement = getConnection().prepareStatement(QUERY_SET_HIGHSCORE);
					statement.setInt(1, highscore);
					statement.setInt(2, playerId);
	
					System.out.println(statement.toString()); // loggen des Query
	
					statement.closeOnCompletion(); // schliessen nach Ausf�hrung
					statement.executeUpdate(); // ausf�hren
					
					player = getPlayer(playerId).getPlayer(); // Benutzer mit aktualisierter Highscore suchen
				} else {
					// gleiche Highscore wie vorher
					player = new Player(oldPlayer);
				}
				message = new PlayerMessage(player, PlayerMessage.State.SUCCESS); // Highscore erfolgreich gesetzt 
			} catch (SQLException e) {
				sqlError(e);
				message = new PlayerMessage(null, PlayerMessage.State.FAILURE); // konnte Query nicht ausf�hren
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new PlayerMessage(null, PlayerMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	@Override
	public PlayersMessage getPlayers(String orderBy, boolean ascending, int limit) {
		System.out.println("getPlayers");
		PlayersMessage message = null;
		List<Player> players = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		StringBuilder sql = null;
		
		// Verbidungs Test
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				sql = new StringBuilder(String.format(QUERY_GET_PLAYERS, 
						new Object[] {
								orderBy
								,ascending ? "asc" : "desc"
						}));
				
				statement = getConnection().prepareStatement(sql.toString());
				statement.setInt(1, limit);

				System.out.println(statement.toString()); // loggen des Query
				
				statement.closeOnCompletion(); // schliessen nach Ausf�hrung
				result = statement.executeQuery(); // ausf�hren
				
				players = resultSetToPlayerList(result); // gefundene Benutzer in Benutzer Liste umwandeln
				
				System.out.println(players.size() + " players found"); // loggen der Anzahl Benutzer
				
				message = new PlayersMessage(players, PlayersMessage.State.SUCCESS); // Benutzer erfolgreich ausgelesen
			} catch (SQLException e) {
				
				message = new PlayersMessage(null, PlayersMessage.State.FAILURE); // konnte Query nicht ausf�hren
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new PlayersMessage(null, PlayersMessage.State.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	/**
	 * SQL spezifische Fehler ausgeben
	 * 
	 * @param e
	 */
	private void sqlError(SQLException e) {
		// SQL Errors
		e.printStackTrace();
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
	/**
	 * Aktuelle Zeile eines SQL Resultats in ein Benutzer Objekt umwandeln
	 * 
	 * @param result
	 * @return
	 */
	private Player resultSetToPlayer(ResultSet result) {
		Player player = null;
		try {
			player = new Player();
			// Daten aus dem SQL Resultat in das Objekt speichern
			player.setPlayerId(result.getInt(COL_PLAYER_ID));
			player.setUsername(result.getString(COL_USERNAME));
			player.setPassword(result.getString(COL_PASSWORD));
			player.setOnline(result.getBoolean(COL_ONLINE));
			player.setHighscore(result.getInt(COL_HIGHSCORE));
		} catch (SQLException e) {
			sqlError(e);
		}
		return player;
	}
	/**
	 * SQL Resultat in Benutzerliste umwandeln
	 * 
	 * @param result
	 * @return
	 */
	private List<Player> resultSetToPlayerList(ResultSet result) {
		List<Player> players = new LinkedList<>();
		
		try {
			// Kursor vor die erste Zeile setzen
			result.beforeFirst();
			// alle Resultate Reihen durchgehen
			while (result.next()) {				
				players.add(resultSetToPlayer(result)); // Benutzer der Liste hinzuf�gen
			}
		} catch (SQLException e) {
			sqlError(e);
		}
		return players;
	}
}
