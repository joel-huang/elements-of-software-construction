package Week4;

import org.junit.Before;
import org.junit.Test;

public class BiSectionTest {
	private BiSectionExample bi;
	
	@Before 
	public void runBeforeEachTest()
	{
		bi = new BiSectionExample();
	}
	
	@Test
	public void test4MethodCoverage () {
		assert (bi.root(0.5, 100.3, 0.1) >= 100);
		//question: should we assert the returned value is the exact value we expect?
	}
	
	@Test 
	public void test4LoopCoverage1 () {//loop once
		assert(bi.root(0,100,80) > 50);
	}
}
