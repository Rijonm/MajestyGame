package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.LogoutMessage.LogoutState;
import CommonClasses.LoginSuccessMessage.LoginState;
import CommonClasses.Player;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.RegisterSuccessMessage.RegisterMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;
import api.ApiInterface;

/**
 * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html
 * 
 * Falls ein Fehler mit der Zeitzone auftritt: default-time-zone im MySQL Config
 * File setzen
 * 
 * für Windows:
 * 
 * folgende Datei öffnen: "C:\ProgramData\MySQL\MySQL Server 5.7\my.ini"
 * 
 * folgendes am Ende der Datei anfügen (Winterzeit: +01:00, Sommerzeit: +02:00):
 * default-time-zone='+01:00'
 * 
 * speichern und MySQL Service neu starten
 * 
 * @author livio
 *
 */
public class Database implements ApiInterface {

	private final static String DB_NAME = "majesty";
	private final static String DB_USER = "majesty";
	private final static String DB_PASSWORD = "majesty2018";
	// jdbc:mysql://localhost/majesty?user=majesty&password=majesty2018
	private static String connectionUrl = "jdbc:mysql://localhost/" + DB_NAME 
			+ "?user=" + DB_USER 
			+ "&password=" + DB_PASSWORD;
	
	private static final String TABLE_NAME = "player";
	
	private static final String COL_PLAYER_ID = "playerId";
	private static final String COL_PASSWORD = "password";
	private static final String COL_USERNAME = "username";
	private static final String COL_ONLINE = "online";
	private static final String COL_HIGHSCORE = "highscore";
	
	// Queries
	// Registration
	private static final String QUERY_REGISTER = "insert into " + TABLE_NAME // Tabelle 
			+ " (" + COL_USERNAME + "," + COL_PASSWORD + ") " // Felder
			+ " values (?,?)"; // Werte
	// Login prüfen
	private static final String QUERY_CHECK_LOGIN = "select * from " + TABLE_NAME // Tabelle
			+ " where username = ? and password = ?"; // Felder + Werte
	// online setzen
	private static final String QUERY_SET_ONLINE = "update " + TABLE_NAME // Tabelle
			+ " set online=?" // Feld + Wert
			+ " where playerId=?"; // welecher Benutzer?
	
		
	private static Connection connection;

	/**
	 * für das Testen der Klassen Funktionen
	 * @param args
	 */
	public static void main(String[] args) {
		Database db = new Database();
		System.out.println(db.register(new UserRegisterMessage("svenson", "michi")).getRegisterMessageTpye().toString());
		System.out.println(db.login(new UserLoginMessage("svenson", "michi")).getState().toString());
		System.out.println(db.logout(new UserLogout(11)).getState().toString());
	}

	/**
	 * initialisiert die Datenbank-Verbindung
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException 
	 */
	private static void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); // erstellt Instanz von der Library Klasse für den Datenbank Treiber
		connection = DriverManager.getConnection(connectionUrl); // öffnet Verbindung zur Datenbank über die URL mit dem Benutzer und Passwort
		System.out.println("database init");
	}

	/**
	 * Falls die Datenbank-Verbindung nicht initialisiert oder geschlossen wurde, wird diese initialisiert und anschliessend zurückgegeben.
	 * 
	 * @return Verbindung für Datenbank Interaktionen
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException Verbindung zur Datenbank konnte nicht hergestellt werden
	 */
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// prüfen ob Verbindung schon erstellt (singleton)
		if (connection == null || connection.isClosed()) {
			init();
		}
		return connection;
	}

	// API Funktionen
	@Override
	public RegisterSuccessMessage register(UserRegisterMessage register) {
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
				
				statement.closeOnCompletion(); // schliessen nach Ausführung
				statement.executeUpdate(); // ausführen 
				
				message = new RegisterSuccessMessage(RegisterMessage.Success); // erfolgreich registriert
			} catch (SQLException e) {
				// SQL Errors
				e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				message = new RegisterSuccessMessage(RegisterMessage.UserAllreadyExsists); // Benutzer exisitiert bereits
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new RegisterSuccessMessage(RegisterMessage.CouldNotConnectToDB); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}

	@Override
	public LoginSuccessMessage login(UserLoginMessage login) {
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

				statement.closeOnCompletion(); // schliessen nach Ausführung
				result = statement.executeQuery(); // ausführen

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

					statement.closeOnCompletion(); // schliessen nach Ausführung
					statement.executeUpdate(); // ausführen
					
					message = new LoginSuccessMessage(true, LoginState.SUCCESS); // erfolgreich eingeloggt
				}
				// nicht gefunden
				else {
					message = new LoginSuccessMessage(false, LoginState.WRONG_LOGIN); // falsche Login Daten
				}
			} catch (SQLException e) {
				// SQL Errors
				e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				message = new LoginSuccessMessage(false, LoginState.GENERAL_ERROR); // konnte Query nicht ausführen
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new LoginSuccessMessage(false, LoginState.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message;
	}
	
	@Override
	public LogoutMessage logout(UserLogout logout) {
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

				statement.closeOnCompletion(); // schliessen nach Ausführung
				statement.executeUpdate(); // ausführen
				
				message = new LogoutMessage(LogoutState.SUCCESS); // erfolgreich ausgeloggt
			} catch (SQLException e) {
				// SQL Errors
				e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				message = new LogoutMessage(LogoutState.FAILURE); // konnte Benutzer nicht auf offline stellen
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new LogoutMessage(LogoutState.COULD_NOT_CONNECT); // Datenbank Verbindung fehlgeschlagen
		}
		return message ;
	}

	@Override
	public List<Player> getLoggedInPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
}
