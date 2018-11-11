package api;

import java.util.List;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.Player;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserRegisterMessage;

/**
 * 
 * @author livio
 *
 */
public interface ApiInterface {
	
	// registration
		// check username
		// registration successful?
	public RegisterSuccessMessage register(UserRegisterMessage register);
	
	// login
		// username correct?
		// password correct?
	public LoginSuccessMessage login(UserLoginMessage login);
	
	// get logged in players
		// get usernames
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
