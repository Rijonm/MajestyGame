package test;

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

public class TestApi implements Api {

	@Override
	public RegisterSuccessMessage register(UserRegisterMessage register) {
		int result;
		Player player = new Player();
		player.setUsername(register.getUsername());
		player.setPassword(register.getPassword());
		result = TestDatabase.insertPlayer(player);
		if (result == -1) {
			
		}
		RegisterSuccessMessage message = new RegisterSuccessMessage(RegisterSuccessMessage.State.SUCCESS);
		return message;
	}

	@Override
	public LoginSuccessMessage login(UserLoginMessage login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogoutMessage logout(UserLogout logout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoggedInPlayers getLoggedInPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMessage getPlayer(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMessage setHighscore(int playerId, int highscore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayersMessage getPlayers(String orderBy, boolean ascending, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
