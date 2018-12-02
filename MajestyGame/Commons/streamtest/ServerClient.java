package streamtest;

import java.net.Socket;

class ServerClient implements Runnable {

	Socket socket;

	ServerClient(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this + " receive: " + Communication.receive(socket));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}