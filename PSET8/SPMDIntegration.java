import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Apply SPMD (Single Program, Multiple Data) design pattern for concurrent programming to parallelize the program which 
 * approximates $\pi$ by integrating the following formula $4/(1+x^2 )$. Hint: In the SPMD design pattern, all threads 
 * run the same program, operating on different data.
 */
public class SPMDIntegration {
	public static void main(String[] args) throws Exception {
		int NUM_THREADS = 5;
		final double[] results = new double[NUM_THREADS];
		double sumResult = 0;
		ExecutorService exec = Executors.newFixedThreadPool(NUM_THREADS - 1);
		// todo: complete the program by writing your code below.
        for (int i = 0; i < NUM_THREADS; i++) {
            final int j = i;
            double upperBound = i * (1.0/NUM_THREADS);
            double lowerBound = (i+1.0) * (1.0/NUM_THREADS);
            Runnable task = () -> {
                double slice = integrate(upperBound, lowerBound);
                results[j] = slice;
            };
            exec.execute(task);
        }
        exec.shutdown();
        exec.awaitTermination(10, TimeUnit.SECONDS);
        for (int i = 0; i < NUM_THREADS; i++) {
            sumResult += results[i];
        }
        System.out.println(sumResult);
	}

	public static double f(double x) {
		return 4.0 / (1 + x * x);
	}

	// the following does numerical integration using Trapezoidal rule.
	public static double integrate(double a, double b) {
		int N = 10000; // preciseness parameter
		double h = (b - a) / (N - 1); // step size
		double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

		for (int i = 1; i < N - 1; i++) {
			double x = a + h * i;
			sum += f(x);
		}

		return sum * h;
	}
}
