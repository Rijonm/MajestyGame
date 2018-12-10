package test;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.LogoutMessage;
import CommonClasses.Message;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
import CommonClasses.UserLogout;
import CommonClasses.UserRegisterMessage;
import api.Api;

/**
 * Der lokale Server, der ueber die gegebene API mit der Datenbank kommuniziert
 * 
 * @author david
 *
 */
public class TestServer implements TestReceiver {

	private Api api;

	/**
	 * Es kann eine beliebige API uebergeben werden 
	 * 
	 * @param api
	 */
	public TestServer(Api api) {
		this.api = api;
	}

	@Override
	public void receive(Message request) {
		if (request != null) {
			switch (request.getMessageType()) {
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
			case LoginSuccessMessage:
				break;
			case LogoutMessage:
				break;
			case PlayerMessage:
				break;
			case PlayerMoveMessage:
				break;
			case PlayersMessage:
				break;
			case RegisterSuccessMessage:
				break;
			case UserLoginMessage:
				login((UserLoginMessage) request);
				break;
			case UserLogout:
				logout((UserLogout) request);
				break;
			case UserRegisterMessage:
				register((UserRegisterMessage) request);
				break;
			default:
				break;
			}
		} else {
			System.err.println("empty request from client");
		}
	}

	public void register(UserRegisterMessage message) {
		System.out.println("TestServer.register()");
		RegisterSuccessMessage result = api.register(message);
		TestConnection.sendToClient(result);
	}

	public void login(UserLoginMessage message) {
		System.out.println("TestServer.login()");
		LoginSuccessMessage result = api.login(message);
		TestConnection.sendToClient(result);
	}
	
	public void logout(UserLogout message) {
		System.out.println("TestServer.logout()");
		LogoutMessage result = api.logout(message);
		TestConnection.sendToClient(result);
	}
}
