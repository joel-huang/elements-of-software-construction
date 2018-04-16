import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class CheckLinksTitle {

	@Test
	public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/Users/Joel/Desktop/SUTD/Term 5/50.003 Elements of Software Construction/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://istd.sutd.edu.sg");
		
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		ArrayList<String> urls = new ArrayList<>();
		for (int i=0;i<links.size();i++) {
		    String url = links.get(i).getAttribute("href");
		    // add all non-null (directly reachable) webpages from this
		    if (url != null && !urls.contains(url)) {
                urls.add(url);
                System.out.println(i + " " + url);
            }
        }

        for (String url : urls) {
		    driver.get(url);
		    // make sure there is a nonzero, nonnull title
            // else fail the test
            assertFalse(driver.getTitle() == null);
            assertFalse(driver.getTitle().isEmpty());
        }

	}
}
