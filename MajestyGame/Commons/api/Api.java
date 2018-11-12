package api;

import java.util.List;

import CommonClasses.PlayersMessage;
import CommonClasses.LoggedInPlayers;
import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.Player;
import CommonClasses.PlayerMessage;
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
	 * 
	 * @param login
	 * @return
	 */
	public LoginSuccessMessage login(UserLoginMessage login);
	
	/**
	 * 
	 * @param logout
	 * @return
	 */
	public LogoutMessage logout(UserLogout logout);
	
	/**
	 * 
	 * @return
	 */
	public LoggedInPlayers getLoggedInPlayers();
	
	/**
	 * 
	 * @param playerId
	 * @return
	 */
	public PlayerMessage getPlayer(int playerId);
	
	/**
	 * 
	 * @param playerId
	 * @param highscore
	 * @return
	 */
	public PlayerMessage setHighscore(int playerId, int highscore);
	
	/**
	 * 
	 * @param orderBy Spalte nach der die Benutzer sortiert werden sollen (null f�r keine Sortierung)
	 * @param limit Wie viel Benutzer maximal angezeigt werden sollen (-1 f�r alle) 
	 * @return
	 */
	public PlayersMessage getPlayers(String orderBy, boolean ascending, int limit);
}
