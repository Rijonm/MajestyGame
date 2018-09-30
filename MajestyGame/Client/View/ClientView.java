package View;

import Model.ClientModel;
import javafx.stage.Stage;

public class ClientView{
	
	Stage primaryStage;
	ClientModel model;

	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		
	}

	public void start() {
		
		primaryStage.show();
		
	}

}
