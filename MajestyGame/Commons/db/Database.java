package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.Player;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.RegisterSuccessMessage.RegisterMessage;
import CommonClasses.UserLoginMessage;
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
	private static String connectionUrl = "jdbc:mysql://localhost/" + DB_NAME 
			+ "?user=" + DB_USER 
			+ "&password=" + DB_PASSWORD;
	
	
	private static final String SELECT = "select * from %s where 1 = 1";
	private static final String SELECT_WHERE = " and (%s);";
	private static final String INSERT = "insert into %s (%s) values (%s);";
	// update
	private static final String UPDATE_BASE = "update %s set %s=%s";
	private static final String UPDATE_EXTEND = ", %s=%s";
	private static final String UPDATE_WHERE = " where %s=%s";
	
	
	private static Connection connection;

	public static void main(String[] args) {
		Database db = new Database();
		System.out.println(db.register(new UserRegisterMessage("rjion", "lol")).getRegisterMessageTpye().toString());
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
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(connectionUrl);
		System.out.println("database init");
	}

	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// prüfen ob Verbindung schon erstellt (singleton)
		if (connection == null) {
			init();
		}
		// wenn Verbidung nicht hergestellt werden konnte -> Fehler
		if (connection == null) {
			// TODO: Datenbankfehler Message
		}
		return connection;
	}

	// API Funktionen
	@Override
	public RegisterSuccessMessage register(UserRegisterMessage register) {
		RegisterSuccessMessage message = null;
		PreparedStatement statement = null;
		// insert Query
		StringBuilder sql = new StringBuilder(String.format(INSERT,
				new Object[] {
						"player",
						"username,password",
						"\"" + register.getUsername() + "\",\"" + register.getPassword() + "\"" 
				}
		));
		System.out.println(sql.toString());
		
		// Verbidungs sTest
		try {
			getConnection();
			// insert
			try {
				// Query vorbereiten
				statement = getConnection().prepareStatement(sql.toString());
				// statement.closeOnCompletion();
				statement.executeUpdate();
				message = new RegisterSuccessMessage(RegisterMessage.Success);
			} catch (SQLException e) {
				// SQL Errors
				e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				message = new RegisterSuccessMessage(RegisterMessage.UserAllreadyExsists);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			message = new RegisterSuccessMessage(RegisterMessage.CouldNotConnectToDB);
		}
		return message;
	}

	@Override
	public LoginSuccessMessage login(UserLoginMessage login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getLoggedInPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
}
