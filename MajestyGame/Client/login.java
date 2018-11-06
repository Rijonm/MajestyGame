
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class login {
	
	public void start(Stage primaryStage) throws Exception {
		
		Stage window = new Stage();
		window.setTitle("Login / Register for Mastey Game");
		BorderPane layout = new BorderPane();
		GridPane BorderPaneCenter = new GridPane();
		
		Label user = new Label("Benutzername:");
		Label password = new Label("Passwort:");
		ImageView iv = new ImageView();
		Button login = new Button("Anmelden");
		Button register = new Button("Registieren");
		TextField userText = new TextField();
		TextField passwortText = new TextField();
		VBox vbox = new VBox();
		
		layout.getChildren().add(BorderPaneCenter);
		layout.setBottom(vbox);
		vbox.getChildren().addAll(login, register);
		
		BorderPaneCenter.add(user, 0,0);
		BorderPaneCenter.add(userText, 0,1);
		
		BorderPaneCenter.add(password, 1,0);
		BorderPaneCenter.add(passwortText, 1,1);
		
		
		iv.setId("file:///Users/mertemek/git/MajestyGame/MajestyGame/Pictures/Parchment-42.jpg");
		
	
		
		
		
		
		Scene scene = new Scene(layout, 900, 500);
		window.setScene(scene);
		window.showAndWait();
			
	}

	}
