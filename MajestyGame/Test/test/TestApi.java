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
		System.out.println("TestApi.register()");
		RegisterSuccessMessage message;
		int result;
		Player player = new Player();
		player.setUsername(register.getUsername());
		player.setPassword(register.getPassword());
		result = TestDatabase.insertPlayer(player);
		
		// evaluate result
		if (result == -1) {
			message = new RegisterSuccessMessage(RegisterSuccessMessage.State.USER_ALREADY_EXISTS);
		} else {
			message = new RegisterSuccessMessage(RegisterSuccessMessage.State.SUCCESS);
		}
		return message;
	}

	@Override
	public LoginSuccessMessage login(UserLoginMessage login) {
		System.out.println("TestApi.login()");
		LoginSuccessMessage message;
		Player player = TestDatabase.checkLogin(login.getUsername(), login.getPassword());
		if (player != null) {
			player.setOnline(true);
			if (TestDatabase.update(player) != null) {
				message = new LoginSuccessMessage(LoginSuccessMessage.State.SUCCESS);
			} else {
				message = new LoginSuccessMessage(LoginSuccessMessage.State.FAILURE);
			}
		} else {
			message = new LoginSuccessMessage(LoginSuccessMessage.State.WRONG_LOGIN);
		}
		return message;
	}

	@Override
	public LogoutMessage logout(UserLogout logout) {
		System.out.println("TestApi.logout()");
		LogoutMessage message;
		Player player = TestDatabase.getPlayer(logout.getPlayerId());
		player.setOnline(false);
		player = TestDatabase.update(player);
		
		if (player != null && !player.isOnline()) {
			message = new LogoutMessage(LogoutMessage.State.SUCCESS);
		} else {
			message = new LogoutMessage(LogoutMessage.State.FAILURE);
		}
		return message;
	}

	@Override
	public LoggedInPlayers getLoggedInPlayers() {
		System.out.println("TestApi.getLoggedInPlayers()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMessage getPlayer(int playerId) {
		System.out.println("TestApi.getPlayer()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMessage setHighscore(int playerId, int highscore) {
		System.out.println("TestApi.setHighscore()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayersMessage getPlayers(String orderBy, boolean ascending, int limit) {
		System.out.println("TestApi.getPlayers()");
		// TODO Auto-generated method stub
		return null;
	}

}
