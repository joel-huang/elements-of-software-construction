package Week4;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestFindMax {
    public int[] testArray;
    public int expected;

    public TestFindMax(Object a, Object b, Object c, Object d, Object exp) {
        testArray = new int[] {(int)a,(int)b,(int)c,(int)d};
        expected = (int)exp;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> setupTests() {
        // last element is the expected max
        Object[][] testArrays = new Object[][] { {1,2,4,3,4}, {null, null, 3, null, 3}, {1,2,3,4,4} };
        return Arrays.asList(testArrays);
    }

    @ Test
    public void runTest() {
        System.out.printf("Running test with array " + Arrays.toString(testArray));
        assertEquals(expected, FindMax.max(testArray));
        System.out.println(" ... OK.");
    }
}
