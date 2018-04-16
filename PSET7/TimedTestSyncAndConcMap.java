import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class TimedTestSyncAndConcMap {

    public static final int NUM_THREADS = 10000;
    private Map<Integer,Integer> syncMap = Collections.synchronizedMap(new HashMap<Integer,Integer>());
    private Map<Integer,Integer> chMap = new ConcurrentHashMap<>();
    CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS);
    private CyclicBarrier endBarrier = new CyclicBarrier(NUM_THREADS+1);
    private Thread[] threads = new Thread[NUM_THREADS];

    @Test
    public void syncMapPutTest() throws BrokenBarrierException, InterruptedException {
        // init the test threads and await barrier
        System.out.println("Setting up threads...");
        for (int i = 0; i < NUM_THREADS; i++) {
            MapTesterThread t = new MapTesterThread(syncMap, barrier, endBarrier);
            threads[i] = t;
        }
        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("Barrier ok... Starting timer");
        long startTime = System.nanoTime();
        endBarrier.await();
        long elapsed = System.nanoTime() - startTime;
        System.out.println("Done in " + elapsed/1000000 + " ms");
    }

    @Test
    public void chMapPutTest() throws BrokenBarrierException, InterruptedException {
        // init the test threads and await barrier
        System.out.println("Setting up threads...");
        for (int i = 0; i < NUM_THREADS; i++) {
            MapTesterThread t = new MapTesterThread(chMap, barrier, endBarrier);
            threads[i] = t;
        }
        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("Barrier ok... Starting timer");
        long startTime = System.nanoTime();
        endBarrier.await();
        long elapsed = System.nanoTime() - startTime;
        System.out.println("Done in " + elapsed/1000000 + " ms");
    }
}

class MapTesterThread extends Thread {
    private Map<Integer,Integer> map;
    private CyclicBarrier startBarrier;
    private CyclicBarrier endBarrier;
    MapTesterThread(Map<Integer,Integer> map, CyclicBarrier startBarrier, CyclicBarrier endBarrier) {
        this.map = map;
        this.startBarrier = startBarrier;
        this.endBarrier = endBarrier;
    }
    public void run() {
        try {
            startBarrier.await();
            map.put(1, 1);
            endBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}