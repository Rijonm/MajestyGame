package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatArea extends VBox {
	Label titleLbl = new Label("Chat");
	Label contentLbl = new Label("Yusuf: Hallo Welt");
	Button sendButton = new Button("send");
	TextField inputTf = new TextField();
	
	public ChatArea() {
		super();
		this.getStyleClass().add("chatArea");
	}

}
