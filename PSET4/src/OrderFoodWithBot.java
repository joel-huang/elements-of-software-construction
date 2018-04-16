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

public class OrderFoodWithBot {
	
	static String myUserName = "huang.joel@hotmail.com";
	static String myPassword = "falsepw";
	static String incorrectPassword = "falserpw";
	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","/Users/Joel/Desktop/SUTD/Term 5/50.003 Elements of Software Construction/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://deliveroo.com.sg/login");
				
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for (int i = 0; i < links.size(); i=i+1) {
			System.out.println(i + " " + links.get(i).getAttribute("href"));
		}		

		
		// get the user name field of the account page
		WebElement username = driver.findElement(By.name("login_email"));
		
		// send my user name to fill up the box
		username.sendKeys(myUserName);
		
		// now locate the password field in the current page
		WebElement password = driver.findElement(By.name("login_password"));		

		// send incorrect password 
		password.sendKeys(incorrectPassword);
		
		// submit user name and password to order
		username.submit();
		password.submit();
		
		// clear the password
		password.clear();
		
		// send correct password 
		password.sendKeys(myPassword);
		password.submit();
		
	}
}
