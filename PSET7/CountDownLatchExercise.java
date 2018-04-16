import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountDownLatchExercise {

    public static final int NUM_THREADS = 7;
    public static final int GRADES_SIZE = 2048;
    private static final int NUM_F = 10;
    private static ArrayList<Thread> threadList = new ArrayList<>();
    private static String[] gradesList = new String[GRADES_SIZE];

    // generates a random grades list of GRADES_SIZE, with NUM_F "F"s randomly scattered
    private static void populateGradesList() {
        Random rand = new Random();
        int[] intGradesList = new int[GRADES_SIZE];

        for (int i = 0; i < intGradesList.length; i++) {
            intGradesList[i] = rand.nextInt(5);
        }

        for (int i = 0; i < NUM_F; i++) {
            intGradesList[rand.nextInt(GRADES_SIZE-1)] = 5;
        }

        HashMap<Integer,String> mapping = new HashMap<>();
        mapping.put(0, "A");
        mapping.put(1, "B");
        mapping.put(2, "C");
        mapping.put(3, "D");
        mapping.put(4, "E");
        mapping.put(5, "F");

        for (int i = 0; i < intGradesList.length; i++) {
            gradesList[i] = mapping.get(intGradesList[i]);
        }
    }

    public static void main(String args[]) {

        // Populate and print out the grades
        populateGradesList();
        System.out.println(Arrays.toString(gradesList));

        // Define the number of Fs we want to find
        final CountDownLatch latch = new CountDownLatch(7);

        // Divide the array so each thread takes a section of it
        int divisions = GRADES_SIZE/NUM_THREADS;

        // Initialize the threads
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(new Check7F(i, gradesList, divisions, latch));
            threadList.add(t);
        }

        for (Thread t : threadList) {
            t.start();
        }

        try {
            latch.await();  //main thread is waiting on CountDownLatch to finish
            // Interrupt all threads once the CountDownLatch reaches 0
            for (Thread t : threadList) {
                t.interrupt();
                System.out.println("Interrupted thread " + t.getName());
            }
            Thread.sleep(1000);
            System.out.println("7 Fs have been found");
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}

class Check7F implements Runnable{
    private final int index;
    private final String[] grades;
    private final int divisions;
    private final CountDownLatch latch;

    public Check7F(int index, String[] grades, int div, CountDownLatch latch) {
        this.index = index;
        this.grades = grades;
        this.divisions = div;
        this.latch = latch;
    }

    public void run() {

        // Calculate the starting and ending indices of the array that this thread is handling
        int start = divisions*index;
        int end = divisions*(index+1);
        if (index == CountDownLatchExercise.NUM_THREADS - 1) {
            end = CountDownLatchExercise.GRADES_SIZE - 1; // last thread will take the rest of the array
        }

        try {
            Thread.sleep(1000);
            System.out.println("This is thread " + index + " checking from array index " + start + " to " + end);
            for (int i = start; i < end; i++) {
                if (grades[i].equalsIgnoreCase("F")) {
                    latch.countDown();
                    System.out.println("Thread " + index + " found an F at position " + i + ",reducing latch count to " + latch.getCount());
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
