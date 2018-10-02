package Control;

import Model.ClientModel;
import View.ClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class ClientController {
	
	public ClientModel model;
	public ClientView view;

	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
		
		// ---------------------LOGIN-BUTTON
		view.loginB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setSecondScene());
				view.start();
				
				
			}
		});
		// ---------------------LOGIN-BUTTON
		
		// --------------------- REGISTRIEREN-BUTTON
		view.registrierenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				
			}
		});
		
		// --------------------- REGISTRIEREN-BUTTON
		
		// ---------------------START-BUTTON
		view.startGame.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
						
				view.primaryStage.setScene(view.setThirdScene());
				view.start();
						
						
			}
		});
		// ---------------------START-BUTTON
		
		
		
		
//		view.loginB.setOnAction(new EventHandler<ActionEvent> (){
//			
//			public void handle(ActionEvent event) {
		
//				// ------WRITE HERE WHAT TO HANDLE -------
//			}
//		});
		
		
		
	}
	
	

}
