package streamtest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class Client implements Runnable {

	String name;
	Socket socket;

	Client(String name, String address, int port) throws UnknownHostException, IOException {
		this.name = name;
		socket = new Socket(address, port);
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("client receive: " + Communication.receive(socket));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void send(Object o) {
		Communication.send(socket, name + ": " + o);
	}
}
