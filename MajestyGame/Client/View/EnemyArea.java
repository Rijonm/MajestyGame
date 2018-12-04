package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class EnemyArea extends HBox{
	private Region spacerE = new Region();
	Label buildingsLbl = new Label();
	Label cB1 = new Label();
	Label cB2 = new Label();
	Label cB3 = new Label();
	Label cB4 = new Label();
	Label cB5 = new Label();
	Label cB6 = new Label();
	Label cB7 = new Label();
	Label cB8 = new Label();
	
	public EnemyArea() {
		super();
		
		this.getChildren().addAll(buildingsLbl, spacerE);
		
		HBox.setHgrow(spacerE, Priority.ALWAYS);
		this.setId("EnemyArea");
	}

}









