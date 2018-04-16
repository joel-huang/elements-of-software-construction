package Week4;

public class BiSectionExample {
	public double root(double d, double e, double f) throws IllegalArgumentException {
		double middle;
		if (d >= e) {
			throw new IllegalArgumentException ("low must be lower than high");
		}

		System.out.println(d + " " + e + " " + f);
		while(e-d > f) {
			System.out.println("once");
		   middle = (e + d) / 2;
		   if (middle < e) {
				 System.out.println("if " + middle + " " + d + " " + e);
			   d = middle;			   
		   }
		   else {
				 System.out.println("else " + middle + " " + d + " " + e);
			   e = middle;
		   }
	    }
		
			System.out.println("return");
	    return (e + d) / 2;
	}

	public static void main (String[] args) {
		BiSectionExample BB = new BiSectionExample();
		BB.root(0,100, 0);				
	}
}
