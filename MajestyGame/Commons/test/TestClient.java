package test;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.Message;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;

public class TestClient implements TestReceiver {

	@Override
	public void receive(Message response) {
		if (response != null) {
			switch (response.getMessageType()) {
			case RegisterSuccessMessage:
				registerResponse((RegisterSuccessMessage) response);
				break;
			case LoginSuccessMessage:
				loginResponse((LoginSuccessMessage) response);
				break;
			case InformationFromServerMessage:
				break;
			case ChatMessage:
				break;
			case EvaluateGameMessage:
				break;
			case FirstSixCardsMessage:
				break;
			case GameStartMessage:
				break;
			case LobbyInformationMessage:
				break;
			case LoggedInPlayers:
				break;
			case LogoutMessage:
				logoutResponse((LogoutMessage) response);
				break;
			case PlayerMessage:
				break;
			case PlayerMoveMessage:
				break;
			case PlayersMessage:
				break;
			case UserLoginMessage:
				break;
			case UserLogout:
				break;
			case UserRegisterMessage:
				break;
			default:
				break;
			}
		} else {
			System.err.println("empty response from server");
		}
	}

// register
	public void register(String username, String password) {
		System.out.println("TestClient.register()");
		TestConnection.sendToServer(new UserRegisterMessage(username, password));
	}

	public void registerResponse(RegisterSuccessMessage response) {
		System.out.println("TestClient.registerResponse()");
		System.out.println(response.getState());
		switch (response.getState()) {
		case SUCCESS:
			System.out.println("successfully registered");
			break;
		case USER_ALREADY_EXISTS:
			System.err.println("user with this username already exists");
			break;
		case COULD_NOT_CONNECT:
			couldNotConnect();
			break;
		}
	}

// login
	public void login(String username, String password) {
		System.out.println("TestClient.login()");
		TestConnection.sendToServer(new UserLoginMessage(username, password));
	}

	public void loginResponse(LoginSuccessMessage response) {
		System.out.println("TestClient.loginResponse()");
		System.out.println(response.getState());
		switch (response.getState()) {
		case SUCCESS:
			System.out.println("successfully logged in");
			break;
		case WRONG_LOGIN:
			System.err.println("wrong login data");
			break;
		case FAILURE:
			System.err.println("could not login");
			break;
		case COULD_NOT_CONNECT:
			couldNotConnect();
			break;

		}
	}

// logout
	public void logout(int playerId) {
		System.out.println("TestClient.logout()");
		TestConnection.sendToServer(new UserLogout(playerId));
	}
	
	public void logoutResponse(LogoutMessage response) {
		System.out.println("TestClient.logoutResponse()");
		System.out.println(response.getState());
		switch (response.getState()) {
		case FAILURE:
			System.err.println("could not logout");
			break;
		case SUCCESS:
			System.err.println("successfully logged out");
			break;
		case COULD_NOT_CONNECT:
			couldNotConnect();
			break;
		}
	}

	private void couldNotConnect() {
		System.err.println("could not connect to database");
	}
}
