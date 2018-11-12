package test;

import CommonClasses.LoginSuccessMessage;
import CommonClasses.Message;
import CommonClasses.RegisterSuccessMessage;
import CommonClasses.UserLoginMessage;
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
		switch (request.getMessageType()) {
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
			break;
		case UserRegisterMessage:
			register((UserRegisterMessage) request);
			break;
		default:
			break;

		}
	}

	public void register(UserRegisterMessage message) {
		RegisterSuccessMessage result = api.register(message);
		TestConnection.sendToClient(result);
	}

	public void login(UserLoginMessage message) {
		LoginSuccessMessage result = api.login(message);
		TestConnection.sendToClient(result);
	}
}
