package Week4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuickSortParameterizedTest {

    private int[] array;
    private int[] result;

    public QuickSortParameterizedTest(int[] arr, int[] res) {
        array = arr;
        result = res;
    }

    @Parameters
    public static Collection<Object[]> parameters() {
        Object[][] testArray = new Object[][] { {new int[] {1,3,2,4,5,7,6,8}, new int[]{1,2,3,4,5,6,7,8}}};
        return Arrays.asList (testArray);
    }

    @Test
    public void test() {
        QuickSort q = new QuickSort();
        q.sort(array);
        String valueOfResult = Arrays.toString(result);
        String valueOfArray = Arrays.toString(q.getArray());
        assertEquals(valueOfResult, valueOfArray);
    }

}
