import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindAllLink {
	
	public static void main(String[] args) {		
		System.setProperty("webdriver.chrome.driver","/Users/Joel/Desktop/SUTD/Term 5/50.003 Elements of Software Construction/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://istd.sutd.edu.sg");
		
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
				
		System.out.println("***Prining all link names***");
		// print all the links
		for (int i = 0; i < links.size(); i=i+1) {
			System.out.println(i + " " + links.get(i).getText());
		}	
		System.out.println("***Prining all link addresses***");
		// print all the hyper links
		for (int i = 0; i < links.size(); i=i+1) {
			System.out.println(i + " " + links.get(i).getAttribute("href"));
		}		

	}
}
