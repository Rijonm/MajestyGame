package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomArea extends HBox {
	public Label playedCards = new Label("Gespielte Karten: ");
	public Label playedRounds = new Label("Gespielte Runden: ");
	public Label playedTime = new Label("Gespielte Zeit: ");
	public Button rulesButton = new Button("Rules");
	public Button highScoreButton = new Button("Highscore");
	public Button logoutButton = new Button("Logout");
	
	public BottomArea() {
		super();
		this.getStyleClass().add("BottomArea");
		
		
	}

}
