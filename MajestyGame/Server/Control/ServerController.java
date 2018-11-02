package Control;

import Model.ServerModel;
import View.ServerView;
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
		
		//connectButton handler
		view.connectB.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
			
			model.serverStart(Integer.parseInt((view.port.getText())));
			view.primaryStage.setScene(view.setServerScene());
			view.start();
						
						
			}
		});
		
	}
	
	

}
