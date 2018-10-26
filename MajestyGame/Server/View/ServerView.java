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
	public BorderPane serverStartPane;
	public Scene serverScene;
	public TextField ip;
	public TextField port;
	public VBox ipAndPort;
	public Button connectB;
	//ServerScene
	public BorderPane serverPane;
	

	public ServerView(Stage primaryStage, ServerModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		serverStartPane = new BorderPane();
		connectB = new Button("Connect");
		ipAndPort = new VBox();
		ip = new TextField("IP");
		port = new TextField("Port");
		
		ipAndPort.getChildren().addAll(ip, port);
		
		
		serverStartPane.setCenter(ipAndPort);
		serverStartPane.setBottom(connectB);
		serverStartPane.setAlignment(connectB, Pos.BOTTOM_RIGHT);
		
		
		serverScene = new Scene(serverStartPane);
		
		primaryStage.setTitle("Server");
		primaryStage.setScene(serverScene);
		
	}

	public void start() {
		
		primaryStage.show();
		
	}

}
