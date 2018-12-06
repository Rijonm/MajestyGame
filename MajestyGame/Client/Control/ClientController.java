package Control;


import CommonClasses.Message;
import CommonClasses.UserLoginMessage;
import Model.ClientModel;
import View.ClientView;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class ClientController {
	
	public ClientModel model;
	public ClientView view;

	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
		
		view.connectB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				

				//model.connect(view.ip.getText(), Integer.parseInt(view.port.getText()));
				//model.connect("localhost", 1111);

				view.primaryStage.setScene(view.setFirstScene());
				view.primaryStage.setFullScreen(false);  /// muss true gesetzt werden
				//primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); 	//User Information Text after going fullscreen
				view.primaryStage.setResizable(true); // muss false gesetz werden
				view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
				view.primaryStage.setTitle("Majesty Group");				
				//model.connect(view.ip.getText(), Integer.parseInt(view.port.getText()));
				view.start();
				
			}
		});
		
		//loginScene buttons
		view.loginB.setOnAction(new EventHandler<ActionEvent> (){
				
			public void handle(ActionEvent event) {

				//ACTIVATE
				//model.sendUserLoginMessage(view.userNameLogin.getText(), view.userpasswordLogin.getText());

				//Message loginMessage = new UserLoginMessage(view.userNameLogin.getText(), view.passwordTf.getText());
				
				//Message.send(model.socket, loginMessage);

				
				view.primaryStage.setScene(view.setThirdScene());
				view.primaryStage.setFullScreen(false);
				//primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); 	//User Information Text after going fullscreen
				view.primaryStage.setResizable(true);
				view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
				view.primaryStage.setTitle("Majesty Group");
				view.start();
				
				
			}
		});
		
		
		
		view.registrierenB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				
				view.primaryStage.setScene(view.setSecondScene());
				view.primaryStage.setFullScreen(true);
				//primaryStage.setFullScreenExitHint("Sie koennen mit ESC schliessen"); 	//User Information Text after going fullscreen
				view.primaryStage.setResizable(false);
				view.primaryStage.getIcons().add(new Image("images/majestyIcon.png"));
				view.primaryStage.setTitle("Majesty Group");				
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
						

				//model.sendUserRegisterMessage(view.userNameRegister.getText(), view.userPasswordRegister.getText());
				
				//DB 

						
				view.primaryStage.setScene(view.setThirdScene());
				view.start();		
			}
		});
		//--------------------------
		
		//spielStartenScene buttons
		view.spielstartenB.setOnAction(new EventHandler<ActionEvent> (){
							
			public void handle(ActionEvent event) {
				
				model.sendGameStartMessage();
				System.out.println("start");
//				view.primaryStage.setScene(view.setFifthScene());
//				view.primaryStage.setFullScreen(false);
						
								
								
			}
		});
		
		
		
		//chatButton
		view.sendButton.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
				//model.sendChatMessage("rijon", view.chatInput.getText());
				view.chatContent.setText(view.chatInput.getText());	
				System.out.println(view.chatInput.getText());
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
								
			}
		});
		
		
		view.logoutB.setOnAction(new EventHandler<ActionEvent> (){
			
			public void handle(ActionEvent event) {
			
				view.primaryStage.setScene(view.firstScene);
								
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
		/**
		 * Wird aktiviert, sobald eine registerSuccessMessage eintrifft. Die GUI wird entsprechend veraendert.
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
		 * Aktualisiert Buttons.
		 * 
		 * @author Rijon
		 */
		model.deck.addListener((ListChangeListener<Integer>) c-> {
			//1)startGameScene
		//view.primaryStage.setScene(view.setFifthScene());
		//view.primaryStage.setFullScreen(false);
			//2)Set new Cards
//			for(int i = 0; i<= buttons.length; i++) {
//				buttons[i].setText("" + model.getFirstSixCards().get(i));
//			}
			
		});
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.chatContent.appendText(newValue + "\n");
			
		} );
		
		
	}
	
	

}
