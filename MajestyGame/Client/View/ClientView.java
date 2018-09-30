package View;

import Model.ClientModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView{
	
	Stage primaryStage;
	ClientModel model;

	public ClientView(Stage primaryStage, ClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		BorderPane loginPage = new BorderPane();
		Button spielStartenButton = new Button("Spiel starten");
		Button loginButton = new Button ("Login");
		VBox startOptionBox = new VBox();
		
		startOptionBox.getChildren().addAll(spielStartenButton, loginButton);
		loginPage.setCenter(startOptionBox);
		
		Scene scene = new Scene(loginPage);
		
		primaryStage.setScene(scene);
		
		
	}

	public void start() {
		
		primaryStage.show();
		
	}

}
