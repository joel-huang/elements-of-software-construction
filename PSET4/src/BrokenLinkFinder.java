import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinkFinder {
	
	// this is a function which checks whether a given hyper link in a web page is broken
	public static void brokenLinkChecker(URL hyperLink) throws Exception {
		//hyperLink = new URL("https://sudiptac.bitbucket.io");
		String acknowledge = null;
		HttpURLConnection linkConnection = (HttpURLConnection) hyperLink.openConnection();

		try {
			System.out.println("*** Checking link " + hyperLink.toString());
			// initiate an HTTP connection
		    linkConnection.connect();
		    // check whether the connection is responding
		    acknowledge = linkConnection.getResponseMessage();
		    // disconnect the connection link
		    linkConnection.disconnect();
		    System.out.println("*** The link " + "returned " + acknowledge);
		} catch(Exception e) {
		    System.out.println("*** The link " + "is broken, message = " + acknowledge);
		}  				
	}
		
	public static void main(String[] args) throws Exception {		
		System.setProperty("webdriver.gecko.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/geckodriver");
		WebDriver driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/chromedriver");
		//WebDriver driver = new ChromeDriver();

		driver.get("https://sudiptac.bitbucket.io");
		//driver.get("https://istd.sutd.edu.sg/");
		//driver.get("https://www.google.com.sg");
		
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
				
		// print all the links
		for (int i = 0; i < links.size(); i=i+1) {
			System.out.println(i + " " + links.get(i).getText());
			System.out.println(i + " " + links.get(i).getAttribute("href"));
		}
		
		// call broken link checker for all the links found
		for (int i = 0; i < links.size(); i=i+1) {
			try {
				brokenLinkChecker(new URL(links.get(i).getAttribute("href")));
			} catch (Exception e) {
				System.out.println("This is not a proper URL to connect to " + links.get(i).getAttribute("href"));
			}
		}
	}
}
