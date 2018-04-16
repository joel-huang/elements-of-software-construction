import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;

public class FindMaxTest {

    @Test
    public void test1() {

        // create the context
        Mockery context = new JUnit4Mockery();

        // create a test case
        int[] inputArray = new int[] {1,3,2};

        // mock the Sorter implementation
        final Sorter sorter = context.mock(Sorter.class);

        // what do we expect the mock class to do?
        context.checking(new Expectations() {{
            // we expect mock sorter to run sort once on inputArray
            oneOf(sorter).sort(inputArray);
            // we expect it to return a sorted array
            will(returnValue(new int[] {1,2,3}));
        }});

        // write the result
        int result = FindMaxUsingSorting.findmax(inputArray, sorter);
        System.out.println(result);
    }
}
