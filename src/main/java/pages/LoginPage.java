package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions{
	private static LoginPage loginPage;

	@FindBy(id = "txtUsername")
	private WebElement userNameElement;
	@FindBy(id = "txtPassword")
	private WebElement passwordElement;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css = "div.organization-logo.shadow>img")
	private WebElement logo;
	
	@FindBy(css = "#txtUsername-error")
	private WebElement usernameErrorElement;
	
	@FindBy(css = "#txtPassword-error")
	private WebElement pwdErrorElement;
	
	private LoginPage() {
		
	}
	
	public static LoginPage getObject() {
		if(loginPage == null)
			loginPage = new LoginPage();
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}
	
	public void login(String userName, String password) {
		enterUsername(userName);
		enterPassword(password);
		hitOnLoginBtn();
	}
	
	public void enterUsername(String userName) {
		
		
		//driver.findElement(By.id("txtUsername")).sendKeys(userName);
		
		//first getElement then send-keys by predefinedActions SetText
		//WebElement element=getElement("id", "txtUsername", false);  
		//setText(element, userName);
		
		// getElement and sendKeys together
	//	setText("id", "txtUsername", false, userName);
		
		//by PageFactory and Send-keys by setText method from predefinedActions
		setText(userNameElement, userName);
	}
	
	public void enterPassword(String password) {
	//	driver.findElement(By.id("txtPassword")).sendKeys(password);
		setText(passwordElement, password);
		
	}
	
	public void hitOnLoginBtn() {
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		clickOnElement(submitBtn, false);
	}
	
	public boolean isUserNameErrorMsgeDisplayed() {
		WebElement userNameErrorMsg = driver.findElement(By.cssSelector("#txtUsername-error"));
		return userNameErrorMsg.isDisplayed();
	}
	
	public boolean isPasswordErrorMsgeDisplayed() {
		WebElement passwordErrorMsg = driver.findElement(By.cssSelector("#txtPassword-error"));
		return passwordErrorMsg.isDisplayed();
	}
	
	public boolean isLogoDisplayed() {
		return driver.findElement(By.cssSelector("div.organization-logo.shadow>img")).isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
}
