package testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoardPage_AV;


public class DashBoardTest_AV extends TestBase {
	
	Logger log = Logger.getLogger(DashBoardPage_AV.class);
	

	@Test
	public void verifyWidgetsCountAndText() throws IOException{
		
		DashBoardPage_AV dashboardPage = DashBoardPage_AV.getObject();	
		
		log.info("VERIFY - Number of widgets on dashboard page");
		int totalWidgets = dashboardPage.getNumberOfWidgets();
		Assert.assertEquals(totalWidgets, 9, "totalWidegets was not displayed as expected, expected was 9, actual widgets displayed "+ totalWidgets);
	
		List<String> listOfExpectedWidgetsText = new ArrayList<String>(
				Arrays.asList("Quick Access","Buzz Latest Posts", "My Actions",
						"Latest Documents", "Latest News", "Employees on Leave Today",
						"Time At Work", "Headcount by Location", "COVID-19 Report"));
		
		log.info("STEP - Get list of all widgets text");
		List<String> listOfActualWidgetsText = dashboardPage.getAllWidgetsText();
		System.out.println(listOfActualWidgetsText);
	
		log.info("VERIFY - text of all widgets");
		Assert.assertEquals(listOfActualWidgetsText, listOfExpectedWidgetsText);
	}
	
	@Test
	public void verfiyProfileAboutContentTest() {
		DashBoardPage_AV dashboardPage =DashBoardPage_AV.getObject();
		
		log.info("STEP - Mouse hover on Profile and Click on Settings");
		List<String> expectedProfileSettingOptions = new ArrayList<String>(Arrays.asList("Change Password", "About"));
		List<String> profileSettingOptions = dashboardPage.getSettingProfileOptions();
	
		log.info("VERIFY - Available setting options");
		Assert.assertEquals(profileSettingOptions, expectedProfileSettingOptions);
		
		log.info("STEP - Click on About link");
		dashboardPage.clickOnProfileAbout();
		
		SoftAssert softAssert = new SoftAssert();
		
		log.info("VERIFY - Company name");
		String companyName = "OrangeHRM (Pvt) Ltd(Parallel Demo)";
		softAssert.assertEquals(dashboardPage.getCompanyName(), companyName);
		
		log.info("VERIFY - Version");
		String version = "OrangeHRM 7.6.174705";
		softAssert.assertEquals(dashboardPage.getVersion(), version, "Expected version was " + version + " but displayed bersion was " + dashboardPage.getVersion());
		
		log.info("VERIFY - Employee");
		String employees = "95 (105 more allowed)";
		softAssert.assertEquals(dashboardPage.getEmployee(), employees);
		
		log.info("VERIFY - Users");
		String users = "81 (419 more allowed)";
		softAssert.assertEquals(dashboardPage.getUsers(), users);
		
		log.info("VERIFY - Renewal on");
		String Renewalon = "Fri, 30 Dec 2022";
		softAssert.assertEquals(dashboardPage.getRenewalOn(),Renewalon );
		
	}
		
}
