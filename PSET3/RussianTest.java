package Week4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RussianTest {

    @Test
    public void blackBoxTest() {
        int result = Russian.multiply(2,4);
        assertEquals(8, result);
    }

    @Test
    public void whiteBoxTest() {

        // branch (n>0 == false)
        assertEquals(0, Russian.multiply(5,0)); // n=0
        assertEquals(-5, Russian.multiply(1,-5)); // n<0

        // branch (n>0 == true)

        // branch (n%2 == 1)
        assertEquals(15, Russian.multiply(3,5)); // n odd, m odd
        assertEquals(6, Russian.multiply(2,3)); // n odd, m even

        // branch (n%2 != 1)
        assertEquals(6, Russian.multiply(3,2)); // n even, m odd
        assertEquals(8, Russian.multiply(4,2)); // n even, m even

        // checking m<0
        assertEquals(-5, Russian.multiply(-5,1)); // m<0
    }

    @Test
    public void faultBasedTest() {
        assertEquals(-4, Russian.multiply(2,-2));
        assertEquals((int)(-Math.pow(Integer.MAX_VALUE, 2)), Russian.multiply(Integer.MAX_VALUE, -Integer.MAX_VALUE));
    }
}
