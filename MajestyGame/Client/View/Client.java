package View;

import Control.ClientController;
import Model.ClientModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

	public ClientModel model;
	public ClientView view;
	public ClientController controller;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		model = new ClientModel();
		view = new ClientView(primaryStage, model);
		controller = new ClientController(model, view);

		view.start();

	}

}
