package Week9;

public class NoVisiability {
	//private static volatile boolean ready;
	private static boolean ready;	
	private static int number;

	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			
			System.out.println(number);
			//Question: what will be printed here?
		}
	}
	
	public static void main (String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}