package Control;


import java.util.HashMap;
import java.util.Map;

import CommonClasses.Message;
import CommonClasses.Player;
import CommonClasses.UserLoginMessage;
import Model.ClientModel;
import View.ClientView;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class ClientController {
	
	public ClientModel model;
	public ClientView view;
	private Map<Integer, String> imgurl = new HashMap();
	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
		imgurl.put(0, "url");
		imgurl.put(1, "");
		imgurl.put(2, "");
		imgurl.put(3, "");
		imgurl.put(4, "");
		imgurl.put(5, "");
		
		view.connectB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				//model.connect(view.ip.getText(), Integer.parseInt(view.port.getText()));
				view.primaryStage.setScene(view.setFirstScene());
				view.start();
				
			}
		});
		
		/**
		 * Wird aktiviert, sobald eine registerSuccessMessage eintrifft. Die GUI wird entsprechend verändert.
		 * 
		 * @author Rijon
		 */
		//TODO
		model.getRegisterSuccess().addListener(c -> {
			System.out.println(c);
			view.registrierenBB.setText(c.toString());
			//COULD NOT CONNECT
			if(c.toString().contains("COULD_NOT_CONNECT") || c.toString().equals("StringProperty [value: ]")) {
			
			}
			//REGISTER SUCCESS
			if(c.toString().contains("SUCCESS")) {
				
			}
			//PLAYER ALLREADY EXIST
			if(c.toString().contains("EXIST")) {
				
			}
		});
		
		
		/**
		 * Wird aktiviert, sobald eine loginSuccessMessage eintrifft. Die GUI wird entsprechend verändert.
		 * 
		 * @author Rijon
		 */
		
		model.getLoginSuccess().addListener(c -> {
			System.out.println(c);
			
			//COULD NOT CONNECT
			if(c.toString().contains("COULD_NOT_CONNECT") || c.toString().equals("StringProperty [value: ]")) {
				
			}
			//WRONG LOGIN
			if(c.toString().contains("WRONG")) {
				
			}
			//WRONG LOGIN
			if(c.toString().contains("FAILURE")) {
				
			}
			//LOGIN SUCCESS
			if(c.toString().contains("SUCCESS")) {
				view.primaryStage.setScene(view.setThirdScene());
				view.start();
			}
		});
		/**
		 * Aktualisiert LobbyListe, sobald sich jemand Ein- oder Ausloggt.
		 * 
		 * @author Rijon
		 */
		model.getLobbyPlayers().addListener((ListChangeListener<Player>) c -> {
			int a = 0;
			while (c.next()) {
				//Solange die liste Spieler hat, aktualisiere die liste
				c.getList().get(a).getUsername();
				a++;
			}
			
		});
		
		//loginScene buttons
		view.loginB.setOnAction(new EventHandler<ActionEvent> (){
				
			public void handle(ActionEvent event) {
				//ACTIVATE
				//model.sendUserLoginMessage(view.userNameLogin.getText(), view.userpasswordLogin.getText());
				
				view.primaryStage.setScene(view.setThirdScene());
				
				
				
			}
		});
		
		
		
		view.registrierenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setSecondScene());
				
				
			}
		});
		
		//--------------------------
		 
		//registrierenScene buttons
		view.backFirstSceneB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setFirstScene());
				
			}
		});
		
		
		view.registrierenBB.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
						
				//model.sendUserRegisterMessage(view.userNameRegister.getText(), view.userPasswordRegister.getText());
				
						
				view.primaryStage.setScene(view.setThirdScene());
						
			}
		});
		//--------------------------
		
		//spielStartenScene buttons
		view.spielstartenB.setOnAction(new EventHandler<ActionEvent> (){
							
			public void handle(ActionEvent event) {
				/*
				 * 
				 * @Yusuf Lobby muss noch gemacht werden, dann kann spieler wählen mit wem er spielen möchte namen als parameter
				 */
				//model.sendGameStartMessage(getName);
				view.primaryStage.setScene(view.setFifthScene());
						
								
								
			}
		});
		
		view.einstellungenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setFourthScene());								
								
			}
		});

		
		view.setupSaveB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setThirdScene());								
								
			}
		});		
		
		
		view.highscoreB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setSixthScene());				
				//model.getHighscoreList()				
			}
		});
		
		
		view.logoutB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setFirstScene());
								
			}
		});
		
		
		view.spielanleitungB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setSeventhScene());			
								
			}
		});
		
		
		view.backToGameScene.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.setThirdScene());				
								
			}
		});
		
		//-------------------------
		
		//gameScene buttons
		view.b1.setOnAction(new EventHandler<ActionEvent> (){
									
			public void handle(ActionEvent event) {
				System.out.println("b1");
				//remove at ordinal 0
										
			}
		});
				
		view.b2.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
				System.out.println("b2");						
										
			}
		});
				
				
		view.b3.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
				System.out.println("b3");						
										
			}
		});
				
		view.b4.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
				System.out.println("b4");						
										
			}
		});
				
		view.b5.setOnAction(new EventHandler<ActionEvent> (){
					
			public void handle(ActionEvent event) {
					
				System.out.println("b5");						
										
			}
		});
		
		view.b6.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
					
				System.out.println("b6");						
										
			}
		});
				
		//-------------------------
		
		
		
	}
	
	

}
