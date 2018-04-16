import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenLinkName {
	
	public static void main(String[] args) {		
		System.setProperty("webdriver.chrome.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/chromedriver");

		// create the driver for Google Chrome
		WebDriver driver = new ChromeDriver();
		
		// open my webpage
		driver.get("https://sudiptac.bitbucket.io/");
				
		// click the link with name "press release"
		driver.findElement(By.linkText("press release")).click();
		
		// click the link name "Event"
		driver.findElement(By.linkText("Events")).click();
		
		// click the link name "Newsletter"
		driver.findElement(By.linkText("Newsletter")).click();

	}
}
