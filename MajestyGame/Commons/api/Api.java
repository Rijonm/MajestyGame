package api;

import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.PlayerMessage;
import CommonClasses.PlayersMessage;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;

/**
 * Application Programming Interface (Schnittstelle)
 * 
 * @author livio
 *
 */
public interface Api {
	
	/**
	 * Neuen Benutzer registrieren
	 * @param register Message Objekt mit den gew�nschten Benutzerdaten
	 * @return Message Objekt mit dem Ergebnis des Registrationsversuches
	 */
	public RegisterSuccessMessage register(UserRegisterMessage register);
	
	/**
	 * Benutzer einloggen
	 * @param login Message Objekt mit den Logindaten
	 * @return Message Objekt mit dem Ergebnis des Loginversuches
	 */
	public LoginSuccessMessage login(UserLoginMessage login);
	
	/**
	 * Benutzer ausloggen
	 * @param logout Message Objekt mit der ID des Benutzers, der ausgeloggt werden soll
	 * @return Message Objekt mit dem Ergebnis des Logoutversuches
	 */
	public LogoutMessage logout(UserLogout logout);
	
	/**
	 * Gibt alle eingeloggte Benutzer zurück
	 * @return Message Objekt mit allen Benutzern, die online sind
	 */
	public LoggedInPlayers getLoggedInPlayers();
	
	/**
	 * Gibt einen einzelnen Benutzer zurück
	 * @param playerId ID des gewollten Benutzers
	 * @return Message Objekt mit dem gefundenen Benutzer
	 */
	public PlayerMessage getPlayer(int playerId);
	
	/**
	 * Highscore eines Benutzers setzen. Wird nur aktualisiert, wenn neue Highscore höher ist als die alte.
	 * @param playerId ID des Benutzers, dessen Highscore aktualisiert werden soll
	 * @param highscore neue Highscore des Benutzers
	 * @return Message Objekt mit dem aktualisierten Benutzer
	 */
	public PlayerMessage setHighscore(int playerId, int highscore);
	
	/**
	 * Sortierte Liste von Benutzern auslesen
	 * @param orderBy Spalte nach der die Benutzer sortiert werden sollen (null f�r keine Sortierung)
	 * @param ascending true für absteigend sortieren, false für aufsteigend sortieren
	 * @param limit Wie viel Benutzer maximal angezeigt werden sollen (-1 f�r alle) 
	 * @return Message Objekt mit Benutzer Liste
	 */
	public PlayersMessage getPlayers(String orderBy, boolean ascending, int limit);
}
