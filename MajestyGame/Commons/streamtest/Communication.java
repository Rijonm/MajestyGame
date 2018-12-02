package streamtest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

abstract class Communication {

	static void send(Socket s, Object o) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
			output.writeObject(o);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Object receive(Socket s) {
		Object o = null;
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(s.getInputStream());
			o = input.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
}
