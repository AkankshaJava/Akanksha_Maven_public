package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import CustomException.ElementNotEnabledException;

import constant_Av.ConstantValues_AV;

public class PredefinedActions {

	static Logger log = Logger.getLogger(PredefinedActions.class);
	
	protected static WebDriver driver;
	static WebDriverWait wait;
	private static Actions actions;
	
	protected PredefinedActions() {
		 
	}
	
		public static void start(String url) {
			
			String browser = System.getProperty("browserName");
			String env = System.getProperty("env");
			
			System.out.println(browser);
			System.out.println("Environment Name : " + env);
			
			switch(browser.toLowerCase()) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				options.addArguments("--incognito");
				System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
				driver=new ChromeDriver();
				break;
			
			case "firefox":
				System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
				driver=new FirefoxDriver();
				break;
				
			default:
				break;
				}
			
		//System.setProperty(ConstantValues_AV.CHROMEDRIVERKEY, ConstantValues_AV.CHROMEDRIVER);
	//	driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		//driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver,ConstantValues_AV.EXPLICTWAITTIME);
		log.trace("Browser launched");
	}
	
	protected WebElement getElement(String locatorType, String locatorValue,boolean isWaitRequired) {
		WebElement element = null;
		
		switch(locatorType.toLowerCase()) {
		case "id":
			if(isWaitRequired)
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element=driver.findElement(By.id(locatorValue));
		break;
		case "xpath":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}else
				element = driver.findElement(By.xpath(locatorValue));
			break;
			
		case "cssselector":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			}else
				element = driver.findElement(By.cssSelector(locatorValue));
			break;
		
		case "name":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				element = driver.findElement(By.name(locatorValue));
			break;	
			
		case "linktext":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			}else
				element = driver.findElement(By.linkText(locatorValue));
			break;
			
		case "partiallinktext":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			}else
				element = driver.findElement(By.partialLinkText(locatorValue));
			break;
			
		case "classname":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			}else
				element = driver.findElement(By.className(locatorValue));
			break;
			
		case "tagname":
			if(isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			}else
				element = driver.findElement(By.tagName(locatorValue));
			break;
		}
	
		return element;
		
	}
	
	protected boolean waitForVisibilityOfElement(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		}catch(Exception exception) {
			return false;
		}
		return true;
	}
		
	protected void setText(WebElement e, String text) {
		scrollToElement(e);
		if(e.isEnabled())
			e.sendKeys(text);
		else
			throw new ElementNotEnabledException(text + " can't be entered as element is not enabled");
	}
	
	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String text) {
		
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		if(e.isEnabled())
			e.sendKeys(text);
	}
	
	protected void clickOnElement(WebElement e, boolean isWaitRequiredBeforeClick) {
		scrollToElement(e);
		if(isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		e.click();
	}
	
	protected void scrollToElement(WebElement e) {
		if(!e.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript("arguments[0].scrollIntoView(true)", e);
		}
	}
	
	protected boolean isElementDisplayed(WebElement e) {
		scrollToElement(e);
		return e.isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	protected void mouseHoverOnElement(WebElement e) {
		actions.moveToElement(e).build().perform();
	}
	
	protected List<String> getListOfWebElementText(List<WebElement> list){
		List<String> listOfWebElementText= new ArrayList<String> ();
		for(WebElement e: list) {
			listOfWebElementText.add(e.getText());
	}
		return listOfWebElementText;
		
	}
	
	protected String getElementText(WebElement e, boolean isWaitRequired) {
		if(isWaitRequired)
			waitForVisibilityOfElement(e);
		String value = e.getText();
		if(value.equals("")) {
			value = e.getAttribute("value");
		}
		return value;
	}
	
	public static void takeScreenShot(String screenShotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(ConstantValues_AV.SCREENSHOTLOCATION+screenShotName+ConstantValues_AV.SCREENSHOTEXT));
	}
	
	public static void takeScreenshot(String testCaseName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(srcFile, new File("./FailedTest/" + testCaseName + ".jpg"));
		}catch (IOException e){
			e.getStackTrace();
		}
	}
	
	protected void clickUingJS(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}

	protected void sendKeyUsingJS(WebElement ele, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + text + "'", ele);
	}

	protected void markCheckbox(WebElement ele, boolean checkedOrUnchecked) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].checked=" + checkedOrUnchecked + "", ele);
	}
	
	
	public void PageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		DateTime dateTime = DateTime.now().plusSeconds(ConstantValues_AV.PAGE_LOAD_PLUSTIME);
		while(!js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete") && dateTime.isAfterNow()) {
			System.out.println("page not loaded");
		}
		System.out.println("page Loaded");
	}
	
	public static void closeBrowser() {
		driver.close();
	}

	
	public void fluentWait() {
		Wait wait = new FluentWait(driver)
				.withTimeout(12, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(Exception.class);
//				.until(ExpectedConditions.);

		WebElement foo = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver t) {
				PageLoad();
				return driver.findElement(By.id("foo"));
			}
		});
	}
	
	
	
}
