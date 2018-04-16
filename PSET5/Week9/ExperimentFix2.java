package Week9;

import java.util.concurrent.locks.ReentrantLock;

public class ExperimentFix2 {

    private static int MY_INT = 0;
    public static ReentrantLock mutex = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException
    {
        new ChangeListener().start();
        System.out.println("Waiting two seconds so the JIT will probably optimize ChangeListener");
        Thread.sleep(2000);
 
        new ChangeMaker().start();
    }
 
    static class ChangeListener extends Thread {
        public void run() {

            int local_value = MY_INT;
            while ( local_value < 5){
                ExperimentFix2.mutex.lock();
                if( local_value!= MY_INT){
                    System.out.println("Got Change for MY_INT : "+ MY_INT);
                     local_value= MY_INT;
                }
                ExperimentFix2.mutex.unlock();
            }
        }
    }
 
    static class ChangeMaker extends Thread{
        public void run() {

            int local_value = MY_INT;
            while (MY_INT <5) {
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                ExperimentFix2.mutex.lock();
                MY_INT = ++local_value;
                ExperimentFix2.mutex.unlock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}