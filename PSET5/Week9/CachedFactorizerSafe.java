package Week9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FactorizerUser {
	public static void main (String[] args) {
		CachedFactorizerSafe factorizer = new CachedFactorizerSafe();
		Thread tr1 = new Thread (new MyRunnable(factorizer));
		Thread tr2 = new Thread (new MyRunnable(factorizer));
		tr1.start();
		tr2.start();
		
		try {
			tr1.join();
			tr2.join();
		}
		catch (Exception e) {
			
		}
	}
}

class MyRunnable implements Runnable {
	private CachedFactorizerSafe factorizer;
	
	public MyRunnable (CachedFactorizerSafe factorizer) {
		this.factorizer = factorizer; 
	}
	
	public void run () {
		Random random = new Random ();
		factorizer.service(random.nextInt(100));
	}
}

public class CachedFactorizerSafe {
	private volatile int lastNumber;
	private volatile List<Integer> lastFactors;
	private volatile long hits;
	private volatile long cacheHits;
	
	public long getHits () {
		return hits;
	}
	
	public synchronized double getCacheHitRatio () {
		return (double) cacheHits/ (double) hits;
	}
	
	public synchronized List<Integer> service (int input) {
		List<Integer> factors = null;
		++hits;
		
		if (input == lastNumber) {
			++cacheHits;
			factors = new ArrayList<Integer>(lastFactors);
		}
		
		if (factors == null) {
			factors = factor(input);
			lastNumber = input;
			lastFactors = new ArrayList<Integer>(factors);
		}
		
		return factors;
	}
	
	public synchronized List<Integer> factor(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
}