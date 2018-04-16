import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class BoundedBufferTest {
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;

	// Tests the BoundedBuffer is empty after calling take()
    // Initialize BB with 10 integers, try to take 10 and
    // assert that it is empty after the action.
	@Test
    public void testIsEmptyAfterTake() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        // Put 10 integers into BoundedBuffer
        for (int i = 0; i < 10; i++) {
            bb.put((new Random()).nextInt());
        }

        Runnable task = new Runnable () {
            public void run() {
                try {
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread (task);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());

    }

    // Test if puts() blocks after full, initialize BB with capacity 9
    // Try and put 10 integers in BB, all threads should block
    @Test
    public void testPutsBlocksWhenFull() throws InterruptedException {
	    int capacity = 9;
	    int numThreads = 10;
	    BoundedBuffer<Integer> bb = new BoundedBuffer<>(capacity);
	    Thread[] putters = new Thread[numThreads];

	    // Check that 9 < 10, if not the test would be pointless
        //noinspection ConstantConditions
        assertTrue(capacity < numThreads);

        // Make threads
        for (int i = 0; i < 10; i++) {
            Thread putter = new Thread() {
                public void run() {
                    try {
                        int next = new Random().nextInt();
                        System.out.println("Putting " + next + " into BB");
                        bb.put(next);
                    } catch (InterruptedException success) {

                    } //if interrupted, the exception is caught here
                }
            };
            putters[i] = putter;
        }

        try {
            for (Thread p : putters) {
                p.start();
            }
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            for (Thread p : putters) {
                p.interrupt();
            }
            for (Thread p : putters) {
                p.join(LOCKUP_DETECT_TIMEOUT);
            }
            for (Thread p : putters) {
                System.out.println("Checking for thread dead...");
                assertFalse(p.isAlive()); //the taker should not be alive for some time
            }
            System.out.println("All dead");
        } catch (Exception unexpected) {
            fail();
        }
    }

	@Test
	public void testIsEmptyWhenConstructed () {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

	@Test
	public void testIsFullAfterPuts () throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    fail();
                } catch (InterruptedException success) {

                } //if interrupted, the exception is caught here
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive()); //the taker should not be alive for some time
        } catch (Exception unexpected) {
            fail();
        }
	}

	@Test
	public void testTakeBlocksWhenEmpty () {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();
					fail();
				} catch (InterruptedException success) {

                } //if interrupted, the exception is caught here
			}
		};

		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive()); //the taker should not be alive for some time
		} catch (Exception unexpected) {
			fail();
		}
	}
}
