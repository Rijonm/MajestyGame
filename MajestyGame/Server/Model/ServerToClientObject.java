package Model;

import java.io.Serializable;

public class ServerToClientObject implements Serializable {
	private Game game;
	private EnumServerToClientAction actionToPerform;
}
