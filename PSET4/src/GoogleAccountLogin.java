import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Random;

public class GoogleAccountLogin {
		
	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver","/Users/Joel/Desktop/SUTD/Term 5/50.003 Elements of Software Construction/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://accounts.google.com/ServiceLogin/signinchooser?passive=1209600&continue=https%3A%2F%2Faccounts.google.com%2FManageAccount&followup=https%3A%2F%2Faccounts.google.com%2FManageAccount&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        WebElement username = driver.findElement(By.name("identifier"));
        WebElement nextButton = driver.findElement(By.id("identifierNext"));

        for (int i = 0; i < 20; i++) {
            // Run the fuzz.exe program.
            // fuzz.exe is modified simple-fuzzer.c with a constant output of 32 fuzz chars.
            Process p = Runtime.getRuntime().exec("/Users/Joel/Desktop/SUTD/Term 5/50.003 Elements of Software Construction/PSET4/src/fuzz.exe");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String fuzz = in.readLine();
            username.sendKeys(fuzz);
            nextButton.click();
            username.clear();
            Thread.sleep(500);
        }

        username.sendKeys("huang.joel@hotmail.com");
        nextButton.click();
        Thread.sleep(1000);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // wait only until the password element becomes visible
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            WebElement password = driver.findElement(By.name("password"));
            WebElement pwButton = driver.findElement(By.id("passwordNext"));
            password.sendKeys("mypw"); // not my actual password
            pwButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("no password field/button?");
        }
    }
}
