import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CacheV3 {

	private final ConcurrentHashMap<Integer, Future<List<Integer>>> results = new ConcurrentHashMap<Integer, Future<List<Integer>>>(); //the last factors must be the factors of the last number

	public List<Integer> service (final int input) throws Exception {

		// the future result of factorization
		Future<List<Integer>> f;
		// set up the futuretask
        Callable<List<Integer>> eval = new Callable<List<Integer>>() {
            public List<Integer> call () throws InterruptedException {
                return factor(input);
            }
        };
        FutureTask<List<Integer>> ft = new FutureTask<List<Integer>>(eval);

        // execute put if absent, if there was no mapping, will put and
        // return null. Use f for convenience
        f = results.putIfAbsent(input, ft);

        // If there was no previous mapping, go execute the FutureTask.
		if (f == null) {
		    f = ft;
			ft.run();
		}
		return f.get();
	}

	public List<Integer> factor(int n) {		
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