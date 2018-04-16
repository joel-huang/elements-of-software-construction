package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Final {

    // constants
    private static final int PORT_NUMBER = 4322;
    private static final int NUM_CLIENTS = 10;
    private static boolean factorFound = false;
    private static ArrayList<Client> clientList = new ArrayList<>();
    private static ArrayList<PrintWriter> outList = new ArrayList<>();
    private static ArrayList<BufferedReader> inList = new ArrayList<>();

	public static void main (String[] args) throws IOException, InterruptedException {

	    // initialize the resources we need
	    ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.println("(... expecting connection ...)");

        for (int i = 0; i < NUM_CLIENTS; i++) {
            System.out.println("creating client " + i);
            Client c = new Client(i,"127.0.0.1", PORT_NUMBER);
            clientList.add(c);
            Socket clientSocket = serverSocket.accept();
            System.out.println("(... " + i + " connection(s) established ...)");
            PrintWriter outClient = new PrintWriter(c.echoSocket.getOutputStream(), true);
            BufferedReader inClient = new BufferedReader(new InputStreamReader(c.echoSocket.getInputStream()));
            c.setIO(inClient, outClient);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outList.add(out);
            inList.add(in);
        }

        System.out.println("starting all clients...");
        for (Client c : clientList) {
            c.start();
        }

        System.out.println("notifying all threads...");
        for (PrintWriter out : outList) {
            out.println(4096);
        }

        // wait for a factor
        String reply = "";
        int whoFoundIt = -1;

        // busy wait for reply
        System.out.println("beginning wait for reply...");
        while (!factorFound) {
            for (int i = 0; i < inList.size(); i++) {
                if (inList.get(i).ready()) {
                    reply = inList.get(i).readLine();
                    whoFoundIt = i;
                    factorFound = true;
                }
            }
        }

        // interrupt all
        System.out.println("factor found, interrupting all...");
        for (Client c : clientList) {
            c.interrupt();
        }

        System.out.println("closing sockets...");
        for (int i = 0; i < outList.size(); i++) {
            outList.get(i).close();
            inList.get(i).close();
        }
        serverSocket.close();
        for (Client c : clientList) {
            c.echoSocket.close();
        }

        Thread.sleep(1000);
        System.out.println("all sockets closed.");
        System.out.println("Client " + whoFoundIt + " found factors " + reply + ".");

	}
}

class Client extends Thread {

    public int clientId;

    private String hostIP;
    private int portNumber;
    public Socket echoSocket;
    private SocketAddress socketAddress;
    private Integer assignment;
    private List<Integer> factors;
    public BufferedReader in;
    public PrintWriter out;
    private volatile int lastNumber;
    private volatile List<Integer> lastFactors;
    private volatile long hits;
    private volatile long cacheHits;


    Client(int id, String ip, int port) throws IOException {
        clientId = id;
        hostIP = ip;
        portNumber = port;
        echoSocket = new Socket();
        socketAddress = new InetSocketAddress(hostIP, portNumber);
        echoSocket.connect(socketAddress, 100);
    }

    public void setIO(BufferedReader inp, PrintWriter outp) {
        in = inp;
        out = outp;
    }

    public void run() {

        try {
            System.out.println("client " + clientId + " set up. Waiting for input...");
            assignment = Integer.parseInt(in.readLine()); // try converting to int
            System.out.println(clientId + " received assignment: factor " + assignment);
            System.out.println("client " + clientId + " starting factorizer...");
            factors = service(assignment);
            System.out.println("client " + clientId + " found factors!");
            out.println(factors);
            echoSocket.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getHits () {
        return hits;
    }

    public synchronized double getCacheHitRatio () {
        return (double) cacheHits/ (double) hits;
    }

    public synchronized List<Integer> service (int input) throws InterruptedException {
        List<Integer> factors = null;
        ++hits;
        if (input == lastNumber) {
            ++cacheHits;
            factors = new ArrayList<>(lastFactors);
        }
        if (factors == null) {
            factors = factor(input);
            lastNumber = input;
            lastFactors = new ArrayList<>(factors);
        }
        return factors;
    }

    public synchronized List<Integer> factor(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }
}

