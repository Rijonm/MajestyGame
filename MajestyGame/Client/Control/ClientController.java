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
		
		//loginScene buttons
		view.loginB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setThirdScene());
				view.start();
				
				
			}
		});
		
		
		
		view.registrierenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setSecondScene());
				view.start();
				
			}
		});
		
		//--------------------------
		 
		//registrierenScene buttons
		view.backFirstSceneB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.firstScene);
			}
		});
		
		
		view.registrierenBB.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
						
				//DB 
						
						
			}
		});
		//--------------------------
		
		//spielStartenScene buttons
		view.spielstartenB.setOnAction(new EventHandler<ActionEvent> (){
							
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setFifthScene());
						
								
								
			}
		});
		
		view.einstellungenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
								
								
			}
		});
		
		
		view.highscoreB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
								
								
			}
		});
		
		view.logoutB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
								
								
			}
		});
		
		view.spielanleitungB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
								
								
			}
		});
		
		//-------------------------
		
		//gameScene buttons
		view.b1.setOnAction(new EventHandler<ActionEvent> (){
									
			public void handle(ActionEvent event) {
				
				
				//remove at ordinal 0
										
			}
		});
				
		view.b2.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
										
										
			}
		});
				
				
		view.b3.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
										
										
			}
		});
				
		view.b4.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
										
										
			}
		});
				
		view.b5.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
										
										
			}
		});
		
		view.b6.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
					
										
										
			}
		});
				
		//-------------------------
		
		
		
	}
	
	

}
