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

public class FillMessageText {
	
	static String myUserName = "sudiptaonline";
	static String myPassword = "thisisnotmypassword";
	
	public static void main(String[] args) throws InterruptedException {		

		System.setProperty("webdriver.chrome.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin/identifier?elo=1&flowName=GlifWebSignIn&flowEntry=AddSession/");
				
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		// get the user name field of the account page
		WebElement username = driver.findElement(By.name("identifier"));
		
		// send my user name to fill up the box
		username.sendKeys(myUserName);

		// locate the "Next" button in the account page
		WebElement nextButton = driver.findElement(By.id("identifierNext"));		
		nextButton.click();
		
		// sleep until the page loads
		Thread.sleep(10000);

		// now locate the password field in the current page
		WebElement password = driver.findElement(By.name("password"));		

		// send password 
		password.sendKeys(myPassword);
		
		// login and :)
		nextButton = driver.findElement(By.id("passwordNext"));		
		nextButton.click();

	}
}
