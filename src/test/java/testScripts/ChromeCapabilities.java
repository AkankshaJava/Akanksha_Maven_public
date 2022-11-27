package testScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import constant_Av.ConstantValues_AV;

public class ChromeCapabilities {
	
	@Test
	public void openBrowser() {

		ChromeOptions options =new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		
		System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY,ConstantValues_AV.CHROMEDRIVER);
		WebDriver driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
		driver.get("http://automationbykrishna.com/");
	}

}
