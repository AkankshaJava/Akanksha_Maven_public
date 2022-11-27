package testScripts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import constant_Av.ConstantValues_AV;

public class MissignLink {
	static WebDriver driver;

	public static void start(String url) {
		System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@Test
	public void verifyMissingLink() throws IOException {
		start("file:///D:/TechnoCredits/Projects/AUG22/MavenHybridDrivenAug2022/BrokenLink.html");
		List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));
		
		for(WebElement e : listOfLinks) {
			if(e.getAttribute("href") == null || e.getAttribute("href").equals(""))
				System.out.println(e.getText() + " is a missing link");
			else {
				URL url = new URL(e.getAttribute("href"));
	
				HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
				httpConn.setConnectTimeout(500);
				httpConn.connect();
				
				if(httpConn.getResponseCode() == 200) {
					System.out.println(e.getText() + "--" + httpConn.getResponseCode());
				}else if(httpConn.getResponseCode() > 399 ) {
					System.out.println(e.getText() + "--" + httpConn.getResponseCode());
				}
			}
		}
	}
}
