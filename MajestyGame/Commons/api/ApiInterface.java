package api;

import java.util.List;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.Player;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;

/**
 * Application Programming Interface (Schnittstelle)
 * @author livio
 *
 */
public interface ApiInterface {
	
	/**
	 * Neuen Benutzer registrieren
	 * @param register Message Objekt mit den gewünschten Benutzerdaten
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
	public List<Player> getLoggedInPlayers();
	
	
	// set highscore
		// score
	
	// get highscore from player
		// join user on highscore to get name and score
		// score
	
	// get highscore top n
		// join user on highscore to get name and score
		// sort highscore
		// top n

}
