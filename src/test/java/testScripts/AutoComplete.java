package testScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import constant_Av.ConstantValues_AV;

public class AutoComplete {

	@Test
	public void openBrowser() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");

		System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
		WebDriver driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("techno");

		WebDriverWait wait = new WebDriverWait(driver, 5);
		List<WebElement> suggestionList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ul[role='listbox'] li div[role='option']"), 5));
		
//		List<WebElement> suggestionList = driver
//				.findElements(By.cssSelector("ul[role='listbox'] li div[role='option']"));

		for (WebElement ele : suggestionList) {
			if (ele.getText().equalsIgnoreCase("technology")) {
				ele.click();
			}
		}

	}

}
