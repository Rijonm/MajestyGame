package streamtest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server implements Runnable {

	public static final String ADDRESS = "127.0.0.1";
	public static final int PORT = 1337;

	ServerSocket serverSocket;
	Socket socket;
	List<ServerClient> clients;

	Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
		clients = new ArrayList<>();
	}

	@Override
	public void run() {
		try {
			System.out.println("server started at " 
					+ serverSocket.getInetAddress().toString() + ":"
					+ serverSocket.getLocalPort());
			while (true) {
				socket = serverSocket.accept();
				System.out.println("new client connected: " + socket);
				ServerClient client = new ServerClient(socket);
				new Thread(client).start();
				clients.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}