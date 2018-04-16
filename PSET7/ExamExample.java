import java.util.concurrent.CyclicBarrier;

public class ExamExample {
    private static int NUM_STUDENTS = 100;
    static CyclicBarrier startExam = new CyclicBarrier(NUM_STUDENTS+1);
    static CyclicBarrier endExam = new CyclicBarrier(NUM_STUDENTS+1);
    static Thread[] threads = new Thread[NUM_STUDENTS];

    public static void main(String[] args) {

        for (int i = 0; i < NUM_STUDENTS; i++) {
            StudentThread studentThread = new StudentThread(i, startExam, endExam);
            threads[i] = studentThread;
        }
        for (Thread t : threads) {
            t.start();
        }
        try {
            startExam.await();
            System.out.println("Exam started");
            endExam.await();
            System.out.println("Exam ended");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class StudentThread extends Thread {

    private int studentId;
    private CyclicBarrier startExam;
    private CyclicBarrier endExam;

    StudentThread(int id, CyclicBarrier s, CyclicBarrier e) {
        studentId = id;
        startExam = s;
        endExam = e;
    }

    public void run() {
        try {
            System.out.println("Student " + studentId + " waiting for exam to start...");
            startExam.await();
            endExam.await();
            System.out.println("Student " + studentId + " handed in paper.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}