package View;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerArea extends VBox {
	Label pNameLbl1 = new Label("Player: Yusuf");
	Label coinsLbl = new Label("Coins: 100");
	Label meeplesLbl = new Label("Meeples: 5");
	
	public PlayerArea() {
		super();
		this.getStyleClass().add("playerArea");
		this.getChildren().addAll(pNameLbl1, coinsLbl, meeplesLbl);
		
	}

}
