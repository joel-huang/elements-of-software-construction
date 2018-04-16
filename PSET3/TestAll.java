package Week4;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;

// This section declares all of the test classes in the program.
@RunWith (Suite.class)
@Suite.SuiteClasses ({ StackTest.class, AccountTest.class })  // Add test classes here.

public class TestAll
{
    // Execution begins at main().  In this test class, we will execute
    // a text test runner that will tell you if any of your tests fail.
    public static void main (String[] args)
    {
    	JUnit4TestAdapter suite = new JUnit4TestAdapter (TestAll.class);
    	junit.textui.TestRunner.run(suite);
    	
    	/*List<Test> list = suite.getTests();
    	System.out.println("test count " + list.size());
    	for (int i = 0; i < list.size(); i++) {
    		junit.textui.TestRunner.run(list.get(i));
    	}*/
    }
}
