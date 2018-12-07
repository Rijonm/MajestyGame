package streamtest;

import CommonClasses.GameStartMessage;

class StreamTest {

	public static void main(String[] args) {
		try {
			new Thread(new Server()).start();

			countdownMessage("clients start in", 3);

			Client c1 = new Client("c1", Server.ADDRESS, Server.PORT);
			Client c2 = new Client("c2", Server.ADDRESS, Server.PORT);

			new Thread(c1).start();
			new Thread(c2).start();

			countdownMessage("messaging starts in ", 3);

			c1.send("hello there");
			c2.send("hi");
			c1.send("it's been a long time");
			c2.send("no.");
			
			//c1.send(new GameStartMessage(c1.name));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static void countdownMessage(String msg, int sec) throws InterruptedException {
		for (int i = sec; i > 0; i--) {
			System.out.print(msg + " " + i + "\r");
			Thread.sleep(1000);
		}
		System.out.println();
	}
}
