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
		Button spielStartB = new Button("Spiel starten");
		Button loginB = new Button ("Login");
		Button accountOptionsB = new Button("Registrieren/Löschen");
		Button einstellungenB = new Button("Einstellungen");
		Button beendenB = new Button("Beenden");
		VBox startOptionBox = new VBox();
		
		startOptionBox.getChildren().addAll(loginB, spielStartB, accountOptionsB, einstellungenB, beendenB);
		loginPage.setCenter(startOptionBox);
		
		Scene scene = new Scene(loginPage);
		
		primaryStage.setScene(scene);
		
		
	}

	public void start() {
		
		primaryStage.show();
		
	}

}
