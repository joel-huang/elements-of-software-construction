import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Test results
// using newFixedThreadPool gives better performance vs other methods.
// Using ExecutorWebServer factoring BigInteger n = new BigInteger("4294967297");

// 10 THREADS - negligible difference

// 100 THREADS
// Spent time: 74ms, 66ms, 98ms, 88ms, 71ms
// vs using ThreadPerTask:
// Spent time: 111ms, 105ms, 173ms, 98ms, 124ms
// vs using Sequential:
// Spent time: 112ms, 71ms, 106ms, 124ms, 99ms

// 1000 THREADS
// Executor: 831ms, 661ms, 578ms, 629ms, 518ms
// SingleThread: 1080ms, 1400ms, 629ms, 1029ms, 789ms
// Sequential: 1540ms, 941ms, 541ms, 509ms, 675ms

public class ExecutorWebServer {
	private static final int NTHREADS = 50;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable () {
				public void run() {
					try {
						handleRequest(connection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			exec.execute(task);
		}
    }

	private static void handleRequest(Socket connection) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		PrintWriter out = new PrintWriter(connection.getOutputStream(), true);                   
        BigInteger number = new BigInteger(in.readLine());
        BigInteger result = factor(number);
        //System.out.println("sending results: " + String.valueOf(result));
		out.println(result);
		out.flush();
        in.close();
		out.close();
		connection.close();
	}
	
	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}