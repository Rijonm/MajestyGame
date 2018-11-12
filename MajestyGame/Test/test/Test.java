package test;

import db.Database;

/**
 * Test Klasse zum testen einer Beispiel-API-Implementation
 * 
 * Es wird ein lokaler Client und Server gebraucht um die API Funktionen zu testen.
 * @author david
 *
 */
public class Test {

	private TestServer server;
	private TestClient client;

	public Test() {
		// der Server wird mit der API initialisiert
		// dies kann "Database" sein oder eine andere Klasse die vielleicht auf eine andere Art und Weise die Daten beschafft (z.B Mockup Daten via TestApi)
		server = new TestServer(new TestApi());
		client = new TestClient();
		TestConnection.setServer(server);
		TestConnection.setClient(client);
	}

	public static void main(String[] args) {
		new Test().run();
	}

	/**
	 * Funktionen durchfuehren
	 */
	public void run() {
		// register
		client.register("xX_PlayerBoiih420_Xx", "somepassword");
		// login
		client.login("xX_PlayerBoiih420_Xx", "somepassword");
	}
}
