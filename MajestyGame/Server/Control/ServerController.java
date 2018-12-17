package Control;

import java.io.IOException;

import Model.Client;
import Model.ServerModel;
import View.ServerView;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ServerController {
	ServerModel model;
	ServerView view;
	/**
	 * 
	 * @author: rijon
	 */
	public ServerController(ServerModel model, ServerView view) {
		this.model = model;
		this.view = view;
		
//		model.clients.addListener((ListChangeListener<Client>) c ->{
//			
//			/**
//			 * @TODO Loggers for the server
//			 */
//			//view.serverLogger.setText(model.clients.get(0).socket.toString());
//		
//		});
		
				
		
		//connectButton handler
		view.connectB.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
			
			model.serverStart(Integer.parseInt((view.port.getText())));
			view.serverLogger.setText("Server started on port: " + view.port.getText());
			view.primaryStage.setScene(view.setServerScene());
			view.start();
						
						
			}
		});
		
		view.stopServerB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
//				model.stopServer();
						
			}
		});
		
	}

}
