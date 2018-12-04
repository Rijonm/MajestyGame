package View;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class BuildingsArea extends GridPane {
	
	private Region spacerB = new Region();
	Label buildingsLbl = new Label();
	Label bCounter1 = new Label("1");
	Label bCounter2 = new Label("2");
	Label bCounter3 = new Label("3");
	Label bCounter4 = new Label("4");
	Label bCounter5 = new Label("5");
	Label bCounter6 = new Label("6");
	Label bCounter7 = new Label("7");
	Label bCounter8 = new Label("8");
	
	public BuildingsArea() {
		super();
		
		buildingsLbl.getStyleClass().add("buildingCard");
		
		
		this.add(bCounter1, 0, 0);
		this.add(bCounter2, 1, 0);
		this.add(bCounter3, 2, 0);
		this.add(bCounter4, 3, 0);
		this.add(bCounter5, 4, 0);
		this.add(bCounter6, 5, 0);
		this.add(bCounter7, 6, 0);
		this.add(bCounter8, 7, 0);
		this.add(buildingsLbl, 0, 1);
		
		HBox.setHgrow(spacerB, Priority.ALWAYS);
		this.setId("BuildingsArea");
	}

}
