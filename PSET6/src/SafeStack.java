/*
STEP 1: IDENTIFYING STATES
    Mutable variables: stack pointer, long[]

STEP 2: IDENTIFYING REQUIREMENTS
    Are there invariants/constraints?
    -1 <= top < long[].length

STEP 3: POLICY
    Reentrantlock + Conditions.

STEP 4: IMPLEMENTATION
    Ensure every access of variables are guarded - push(), pop() all guarded
    Wait for preconditions - guaranteed by condition.await()

 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SafeStack {

    public ReentrantLock mutex = new ReentrantLock();
    public final Condition notFull = mutex.newCondition();
    public final Condition notEmpty = mutex.newCondition();

	private final int maxSize;
	private long[] stackArray; //guarded by "this"
	private int top; //invariant: top < stackArray.length && top >= -1; guarded by "this"

	//pre-condition: s > 0
	//post-condition: maxSize == s && top == -1 && stackArray != null
	public SafeStack(int s) { //Do we need "synchronized" for the constructor?
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}


	// top < stackArray.length
	public void push(long value) {

	    // grab the lock
        mutex.lock();

        // if full, await() to release the lock
        while (isFull()) {
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // critical section
        stackArray[++top] = value;
        notEmpty.signalAll();

        mutex.unlock();
    }

	//pre-condition: top >= 0
	//post-condition: the top element is removed
	public long pop() {

		long toReturn;

		// grab the lock
		mutex.lock();

		// if empty release the lock through await()
		while (isEmpty()) {
			try {
				notEmpty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// critical section
		toReturn = stackArray[top--];
        notFull.signalAll();

        mutex.unlock();
        return toReturn;
	}

	//pre-condition: true
	//post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public boolean isEmpty() {
		return (top == -1);
	}
	public boolean isFull() { return (top == maxSize - 1);}

	// return the number of nonzero elements in the array
	public int count() {
	    int res = 0;
        for (int i = 0; i < stackArray.length; i++) {
            if(stackArray[i] != 0) res++;
        }
        return res;
    }
}