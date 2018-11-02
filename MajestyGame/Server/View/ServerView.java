package View;

import Model.ServerModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerView {
	
	public Stage primaryStage;
	public ServerModel model;
	//ServerStartScene
	public Scene serverStartScene;
	public BorderPane serverStartPane;
	public TextField port;
	public Button connectB;
	//ServerScene
	public Scene serverScene;
	public TextField serverLogger;
	public Button stopServerB;
	public BorderPane serverPane;
	

	public ServerView(Stage primaryStage, ServerModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		serverStartPane = new BorderPane();
		connectB = new Button("Connect");
		port = new TextField("Port");
		
		serverStartPane.setCenter(port);
		serverStartPane.setBottom(connectB);
		serverStartPane.setAlignment(connectB, Pos.BOTTOM_RIGHT);
		
		
		serverStartScene = new Scene(serverStartPane);
		
		primaryStage.setTitle("Server");
		primaryStage.setScene(serverStartScene);
		
	}

	public void start() {
		
		primaryStage.show();
		
	}
	
	
	public Scene setServerScene() {
		
		serverLogger = new TextField();
		stopServerB = new Button("Stop Server");
		serverPane = new BorderPane();
		serverPane.setCenter(serverLogger);
		serverPane.setBottom(stopServerB);
		serverLogger.setDisable(true);
		serverScene = new Scene(serverPane);
		return serverScene;
	}

}
