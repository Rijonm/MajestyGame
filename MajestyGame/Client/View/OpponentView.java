package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class OpponentView extends HBox{
	
	public Label lAr[] = new Label[8];
	
	public Label buildingsLbl = new Label();
//	public Label cB1 = new Label("0");
//	public Label cB2 = new Label("0");
//	public Label cB3 = new Label("0");
//	public Label cB4 = new Label("5");
//	public Label cB5 = new Label("2");
//	public Label cB6 = new Label("0");
//	public Label cB7 = new Label("4");
//	public Label cB8 = new Label("0");
	
	public VBox enemyBs = new VBox();
	
	public Region spacerE = new Region();
	
	public OpponentView() {
		super();
		this.buildingsLbl.setId("enemyBuildingsImg");
		this.buildingsLbl.setMinHeight(240);
		
		for(int i = 0; i < 8; i++) {
			lAr[i] = new Label();
			lAr[i].setMinHeight(30);
			lAr[i].getStyleClass().add("ebCounter");
			enemyBs.getChildren().add(lAr[i]);
		}
		//enemyBs.getChildren().addAll(cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8);
		this.getChildren().addAll(buildingsLbl, enemyBs, spacerE);
		
		HBox.setHgrow(spacerE, Priority.ALWAYS);
		this.setId("EnemyArea");
	}

}









