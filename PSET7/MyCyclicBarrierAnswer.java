public class MyCyclicBarrierAnswer {
	private int count = 0; 
	private Runnable torun;
	
	public MyCyclicBarrierAnswer(int count, Runnable torun) {
		this.count = count;
		this.torun = torun;
	}

	public MyCyclicBarrierAnswer(int count) {
	    this.count = count;
	}

	// each thread calls await to wait for the barrier condition.
    // so each thread reduces count, similar to a counting semaphore
    // on count 0,
	public synchronized void await() throws InterruptedException {
		count--;
        if (count == 0) {
            torun.run();
            notifyAll();
        }
        else {
            wait();
        }
	}
}
