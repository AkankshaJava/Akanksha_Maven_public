package testScripts;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import constant_Av.ConstantValues_AV;

public class WaitForPageLoadDemo {

	@Test
	public void openBrowser() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");

		System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//a[@class='underLineOnHover'][text()='Blog']")));

		js.executeScript("window.scrollBy(0,-500)");

		driver.findElement(By.xpath("//a[@class='underLineOnHover'][text()='Blog']")).click();

//		boolean isPageLoad = js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete");

		DateTime dateTime = DateTime.now().plusSeconds(20);

		System.out.println("Time: " + dateTime.isAfterNow());
		while (dateTime.isAfterNow()
				&& !js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
			System.out.println("Wait for page load");
		}

		System.out.println("Page Loaded...");

	}
}
