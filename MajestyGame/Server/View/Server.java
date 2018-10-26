package View;

import Control.ServerController;
import Model.ServerModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Server extends Application{
	
	ServerModel model;
	ServerView view;
	ServerController controller;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		model = new ServerModel();
		view = new ServerView(primaryStage, model);
		controller = new ServerController(model, view);
		
		view.start();
		
	}
	

}