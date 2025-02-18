package selenium.framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		// By this chromedriver will automaticaly set to the local:-
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		// implicitlyWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// login
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("dkoctober31@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Diwakar@123");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(".mb-3")));

		// grab all the items present in the dashboard
		List<WebElement> dashboard = driver.findElements(By.className(".mb-3"));
		if (dashboard.isEmpty()) {
			System.out.println("No elements found with class 'mb-3'.");
		}

		// using java streams to iterate every items
		Thread.sleep(2000);
		WebElement prod = null;
		String product="ADIDAS ORIGINAL";
		for (WebElement dashboards : dashboard) {
			System.out.println("check");
			// System.out.println("Checking: " + dashboards.getText());
			if (dashboards.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(product)) {
				prod = dashboards;
				break; // Exit loop after finding the first match
			}
		}
		// in this we will find the element in the dashboards and it the text which we
		// get in b tag then if it equals to QWERT then it will print or else it is
		// passing null
		if (prod != null) {
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		} else {
			System.out.println("No product found with text 'qwerty'.");
		}

		// confirmation popup after click on cart
		// using Explicit wait beacause popul is getting hidden after sometime so giving
		// cutom time so that driver will wait for 5 sec beacuse it failed the steps
		// we have to wait untile the animating icon disaaper beacuse after disappering
		// then only the add to cart popup is appearing
		System.out.println("thread1");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'toast')]")));
System.out.println(driver.getPageSource());

		Thread.sleep(2000);
		// click on the cart button
		driver.findElement(By.xpath("//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")).click();


		List<WebElement> cart=driver.findElements(By.xpath("//div[@class='cart']//ul//li"));
		
		
	}

}
