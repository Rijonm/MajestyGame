package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DeckArea extends VBox {
	public VBox personsDeck = new VBox();
	public Label deckCardLbl = new Label();
	public Button b1 = new Button("b1");
	public Button b2 = new Button("b2");
	public Button b3 = new Button("b3");
	public Button b4 = new Button("b4");
	public Button b5 = new Button("b5");
	public Button b6 = new Button("b6");
		
	public DeckArea() {
		super();
		this.getStyleClass().add("deckArea");
		
			
		for (int i = 0; i < 6; i++) {
			Button b = new Button("b"+i);
			b.getStyleClass().add("gameButtons");
			personsDeck.getChildren().add(b);
		}
		
		
		deckCardLbl.getStyleClass().add("deckCardLbl");
//		b1.getStyleClass().addAll("gameButtonFirst", "gameButtons");
//		b2.getStyleClass().addAll("gameButtonSecond", "gameButtons");
//		b3.getStyleClass().addAll("gameButtonThird", "gameButtons");
//		b4.getStyleClass().addAll("gameButtonFourth", "gameButtons");
//		b5.getStyleClass().addAll("gameButtonFifth", "gameButtons");
//		b6.getStyleClass().addAll("gameButtonSixth", "gameButtons");
		
	}

}
