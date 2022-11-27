package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import constant_Av.ConstantValues_AV;

public class CalendarDemo {

	@Test
	public void openBrowser() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");

		System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.goibibo.com/");

		driver.findElement(By.xpath("//p[text()='Saturday']")).click();
		Thread.sleep(2000);

		String expectedMonth = "March 2023";
		String expectedDate = "12";

		WebElement nextMonthButton = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));

		WebElement monthTitle = driver
				.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]//div[@class='DayPicker-Caption']/div"));

		while (!monthTitle.getText().equalsIgnoreCase(expectedMonth)) {
			nextMonthButton.click();
		}

		driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]//p[text()='" + expectedDate + "']"))
				.click();

		driver.findElement(By.xpath("//span[text()='Done']")).click();
	}

	public void selectDate(String year, String month, String date) {
		String expectedMonth = year + " " + month;
		String expectedDate = "12";
		
		
	}

}
