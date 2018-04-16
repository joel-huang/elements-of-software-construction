package Week9;
import java.lang.Thread;
import java.math.BigInteger;

class FactorPrimeMultiThread extends Thread {

    BigInteger semiPrime;
    boolean found = false;

    FactorPrimeMultiThread(BigInteger n) {
        semiPrime = n;
    }

    public boolean isFound() {
        return found;
    }

    @Override
    public void run() {
        try {
            BigInteger i = new BigInteger("2");
            BigInteger zero = new BigInteger("0");

            factor:
            while (i.compareTo(semiPrime) < 0) {
                if (isInterrupted()) {
                    System.out.println("im interrupted");
                    throw new InterruptedException();
                }
                if (semiPrime.remainder(i).compareTo(zero) == 0) {
                    found = true;
                    break factor;
                }

                i = i.add(new BigInteger("1"));
            }

        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
