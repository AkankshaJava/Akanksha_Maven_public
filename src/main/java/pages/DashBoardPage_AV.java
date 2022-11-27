package pages;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import constant_Av.ConstantValues_AV;
import base.PredefinedActions;
import utility.PropertyFileOpeartions;

public class DashBoardPage_AV extends PredefinedActions{
	
	Logger log = Logger.getLogger(DashBoardPage_AV.class);
	
	private static DashBoardPage_AV dashboardPage;

	@FindBy(xpath="//div[contains(@class,'oxd dashboard-widget-shell') and not(contains(@class,'ng-hide'))]//div[@class='widget-header']//span//following-sibling::span")
	private List<WebElement> listOfWidgets;
	
	@FindBy(css = "div[id='sidebar-profile-picture']")
	private WebElement profile;

	@FindBy(css = "a#aboutDisplayLink")
	private WebElement profileAboutLink;

	@FindBy(xpath = "//div[@class='sub-menu-container-php profile-context-menu-handler opened']/div/div")
	private List<WebElement> profileOptions;

	@FindBy(css = "a[class*='xt-menu-handler']")
	private WebElement profileSetting;

	@FindBy(css = "div#companyInfo p")
	private List<WebElement> profileAboutDetails;
	
	@FindBy(css = "div#companyInfo>div>div:nth-child(1)>p")
	private WebElement aboutContentFirstP;
	
	
	private PropertyFileOpeartions prop;
	
	private DashBoardPage_AV() {
		try {
			prop = new PropertyFileOpeartions(ConstantValues_AV.DASHBOARDPAGELOCATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DashBoardPage_AV getObject() {
		if(dashboardPage == null)
			dashboardPage = new DashBoardPage_AV();
		PageFactory.initElements(driver, dashboardPage);
		return dashboardPage;
	}
	
/*
 * 	public DashBoardPage_AV() {
 * PageFactory.initElements(driver, this);
	}
 */
		

	public int getNumberOfWidgets() {
			return listOfWidgets.size();
	}

	public List<String> getAllWidgetsText() {
		return getListOfWebElementText(listOfWidgets);
	}
	
	public boolean isProfileDisplayed() {
		return isElementDisplayed(profile);
	}
	
	private void expandProfileOptions() {
		mouseHoverOnElement(profile); // click on setting button
		clickOnElement(profileSetting, false);
	}
	
	public List<String> getSettingProfileOptions() {
		expandProfileOptions();
		return getListOfWebElementText(profileOptions);
	}
	
	public void clickOnProfileAbout() {
		if (!isElementDisplayed(profileAboutLink)) {
			expandProfileOptions();
		}
		clickOnElement(profileAboutLink, false);
	}
	
	public Map<String, String> getAboutText() {
		
		boolean flag = waitForVisibilityOfElement(aboutContentFirstP);
		if(!flag)
			throw new NoSuchElementException("About content not being loaded in given time out");
		
		List<String> aboutDetailsList = getListOfWebElementText(profileAboutDetails);
		Map<String, String> aboutDetailsMap = new LinkedHashMap<>();

		for (String text : aboutDetailsList) {
			String[] arr = text.split(":");
			aboutDetailsMap.put(arr[0].trim(), arr[1].trim());
		}
		return aboutDetailsMap;
	}
	
	public String getCompanyName() {
		return getAboutText().get("Company Name"); //get key value from key
	}
	
	public String getVersion() {
		return getAboutText().get("Version");
	}
	
	public String getEmployee() {
		return getAboutText().get("Employees");
	}
	
	public String getUsers() {
		return getAboutText().get("Users");
	}
	
	public String getRenewalOn() {
		return getAboutText().get("Renewal on");
	}

	public void clickOnAboutPopupBtn(String btnName) { // OK, ok, oK --- Ok, Cancel [enum]
		String locatorValue = String.format(prop.getKeyValue("aboutBtnLocator"), btnName);
		WebElement e = getElement("xpath", locatorValue, false);
		clickOnElement(e, false);
	}
	
	public enum Menu{
		EMPLOYEELIST("Employee List"),
		MYINFO("My Info"),
		DIRECTORY("Directory");
		
		public String menuItem;
		
		private Menu(String menuTitle) {
			this.menuItem = menuTitle;
		}
	}
	
	enum Browser{
		CHROME,
		FF,
		SAFARI;
	}
	
	
	public void gotoMenu(Menu menuName) {
		String menuText = menuName.menuItem;
		String locatorValue = String.format(prop.getKeyValue("menuLocator"), menuText);
		clickOnElement(getElement("xpath", locatorValue, true),false);
	}

}
