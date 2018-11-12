package test;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserRegisterMessage;

public class TestClient implements TestReceiver {

	@Override
	public void receive(Message response) {
		switch (response.getMessageType()) {
		case RegisterSuccessMessage:
			registerResponse((RegisterSuccessMessage) response);
			break;
		case LoginSuccessMessage:
			loginResponse((LoginSuccessMessage) response);
			break;
		case CardFromServerMessage:
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
	}

// register
	public void register(String username, String password) {
		TestConnection.sendToServer(new UserRegisterMessage(username, password));
	}

	public void registerResponse(RegisterSuccessMessage response) {
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
		TestConnection.sendToServer(new UserLoginMessage(username, password));
	}

	public void loginResponse(LoginSuccessMessage response) {
		switch (response.getState()) {
		case SUCCESS:
			break;
		case WRONG_LOGIN:
			break;
		case FAILURE:
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
