import org.junit.Test;

import java.util.Vector;

public class FirstExampleFixed {

    static Vector<Object> vect;


    // Test results: a ArrayIndexOutOfBounds is not thrown! yay
    @Test
    public void test1() throws InterruptedException {
        // try the situtation described in the slides

        while (true) {

            vect = new Vector<>();
            // add 10 new objects
            for (int i = 0; i < 10; i++) {
                vect.add(new Object());
            }

            System.out.println("the last object is " + vect.get(9));

            ThreadA a = new ThreadA();
            ThreadB b = new ThreadB();

            a.start();
            b.start();
            a.join();
            b.join();

            Thread.sleep(2000);
        }
    }
}

class ThreadA extends Thread {
    Vector v = FirstExampleFixed.vect;
    public void run() {
        System.out.println((FirstExampleMethods.getLast(v)));
    }
}

class ThreadB extends Thread {
    Vector v = FirstExampleFixed.vect;
    public void run() {
        FirstExampleMethods.deleteLast(v);
    }
}


class FirstExampleMethods {

	public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
	}

	public static void deleteLast(Vector list) {
	    synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
	}	
	
	public static boolean contains(Vector list, Object obj) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(obj)) {
				return true;
			}
		}
		return false;
	}
}
