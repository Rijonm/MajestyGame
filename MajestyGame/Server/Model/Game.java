package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
	
	private ArrayList<PlayerInGame> players;
	private Deck deck;
	private ServerModel model;
	
	public Game(ServerModel model) {
		this.model = model;
	}


	public void evaluate() {
		
	
	}
}