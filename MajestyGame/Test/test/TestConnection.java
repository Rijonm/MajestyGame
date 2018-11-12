package test;

import CommonClasses.Message;

public class TestConnection {

	private static TestServer server;
	private static TestClient client;

	public static void setServer(TestServer server) {
		TestConnection.server = server;
	}

	public static void setClient(TestClient client) {
		TestConnection.client = client;
	}

	public static void sendToServer(Message request) {
		server.receive(request);
	}

	public static void sendToClient(Message request) {
		client.receive(request);
	}
}
