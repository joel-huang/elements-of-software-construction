import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    @Test
    public void test() throws InterruptedException {

        SynchronizedAccount account = new SynchronizedAccount(1,5);

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            WithdrawThread w = new WithdrawThread(account);
            DepositThread d = new DepositThread(account);
            CheckBalanceThread c = new CheckBalanceThread(account);
            threads.add(w);
            threads.add(d);
            threads.add(c);
        }

        System.out.println("Balance " + account.getBalance());

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }


    }
}

class WithdrawThread extends Thread {
    SynchronizedAccount acc;
    WithdrawThread(SynchronizedAccount s) { acc = s; }
    public void run() {
        acc.withdraw(new Random().nextInt(10));
    }
}

class DepositThread extends Thread {
    SynchronizedAccount acc;
    DepositThread(SynchronizedAccount s) { acc = s; }
    public void run() {
        acc.deposit(new Random().nextInt(10));
    }
}

class CheckBalanceThread extends Thread {
    SynchronizedAccount acc;
    CheckBalanceThread(SynchronizedAccount s) { acc = s; }
    public void run() {
        System.out.println("Balance " + acc.getBalance());
    }
}

class SynchronizedAccount {
    private int id;
    private int balance;
    private static ReentrantLock mutex = new ReentrantLock();
    // The constructor has to lock, because the balance variable
    // must not be accessible till it is set.
    public SynchronizedAccount(int registerId, int initialAmount) {
        mutex.lock();
        id = registerId;
        balance = initialAmount;
        mutex.unlock();
    }
    // precondition: deposit >= 0
    public boolean deposit(int deposit) {
        if (deposit < 0) return false;
        mutex.lock();
        System.out.println("Depositing "+deposit);
        balance += deposit;
        mutex.unlock();
        return true;
    }
    public boolean withdraw(int withdrawal) {
        if (withdrawal < 0) return false;
        mutex.lock();
        if (withdrawal > balance) {
            mutex.unlock();
            return false;
        }
        System.out.println("Withdrawing "+withdrawal);
        balance -= withdrawal;
        mutex.unlock();
        return true;
    }
    public int getBalance() {
        return balance;
    }
}
