package Week9;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

public class FactorPrimeMultiThreadTest {

    int numThreads = 4;
    ArrayList<FactorPrimeMultiThread> threadList = new ArrayList<>();
    public volatile boolean found = false;

    @Test
    public void test1() throws InterruptedException {
        BigInteger n = new BigInteger("239839672845043");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            FactorPrimeMultiThread t = new FactorPrimeMultiThread(n);
            threadList.add(t);
        }

        for (FactorPrimeMultiThread t : threadList) {
            t.start();
        }

        for (FactorPrimeMultiThread t : threadList) {
            t.join();
        }

        check:
        while (!found) {
            for (FactorPrimeMultiThread t : threadList) {
                if (t.isFound()) {
                    System.out.println(t.semiPrime);
                    for (FactorPrimeMultiThread th : threadList) {
                        th.interrupt();
                    }
                    break check;
                }
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        int seconds = (int) (duration / 1000) % 60;
        int minutes = (int) ((duration / (1000 * 60)) % 60);
        int hours = (int) ((duration / (1000 * 60 * 60)) % 24);
        System.out.println("Time used: " + hours + " hours " + minutes + " minutes " + seconds + " seconds.");
    }

}
