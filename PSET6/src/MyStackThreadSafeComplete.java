import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
public class MyStackThreadSafeComplete {

    static ArrayList<Thread> threads = new ArrayList<>();

    // tries to pop 50 from a full stack of 100
    // assert result = 50
    @Test
    public void popTest() throws InterruptedException {

        SafeStack s = new SafeStack(100);

        // push 100 entries into the stack
        for (int i = 0; i < 100; i++) {
            s.push(1);
        }

        for (int i = 0; i < 50; i++) {
            PopThread p = new PopThread(s, i);
            threads.add(p);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        assertEquals(100, s.count());
    }

    // tries to push 50 into an empty stack with maxsize 100
    // assert result = 50
    @Test
    public void pushTest() throws InterruptedException {

        SafeStack s = new SafeStack(100);

        for (int i = 0; i < 50; i++) {
            PushThread p = new PushThread(s, i);
            threads.add(p);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        assertEquals(50,s.count());
    }

    // this test should wait forever as there is one more thread trying to push than
    // there are slots in the stack
    @Test
    public void pushWhileFullTest() throws InterruptedException {

        SafeStack s = new SafeStack(10);

        for (int i = 0; i < 11; i++) {
            PushThread p = new PushThread(s, i);
            threads.add(p);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

}

class PushThread extends Thread {
    int id;
    SafeStack stack;
    PushThread(SafeStack s, int i) {
        stack = s;
        id = i;
    }
    public void run() {
        System.out.println("pushthread " + id + " pushing");
        stack.push(1);
    }
}

class PopThread extends Thread {
    int id;
    SafeStack stack;
    PopThread(SafeStack s, int i) {
        stack = s;
        id = i;
    }
    public void run() {
        long i = stack.pop();
        System.out.println("popthread " + id + " popping " + i);

    }
}