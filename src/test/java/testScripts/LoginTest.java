package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import constant_Av.ConstantValues_AV;
import pages.LoginPage;
import utility.ExcelSheetOperations;

public class LoginTest {

	@Test(dataProvider="LoginCredentialsDataProvider")
	public void tc1(String url, String uname, String password, boolean isLoginSuccessful) {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start(url);
		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(uname, password);
		
		if(isLoginSuccessful) {
		System.out.println("VERIFY - home page is displayed");
		String expetedTitle = "Employee Management";
		String actualTitle = loginPage.getPageTitle();
		
		Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
		}else {
			
			System.out.println("VERIFY - home page is displayed");
			String expetedTitle = "OrangeHRM";
			String actualTitle = loginPage.getPageTitle();
			
			Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
			
			System.out.println("VERIFY - Retry login page is loaded");
			String expectedUrlContent = "retryLogin";
			String actualCurrentURL = loginPage.getPageURL();
			Assert.assertTrue(actualCurrentURL.endsWith(expectedUrlContent));
		}
		PredefinedActions.closeBrowser();
	}
	
	@DataProvider(name="LoginCredentialsDataProvider")
	public Object[][] loginData() throws IOException{
		Object[][] obj;
	//tring file=".\\TestData\\\\Data.xlsx";
		try {
			obj=ExcelSheetOperations.excelReadData(ConstantValues_AV.LOGINDATA,"Login");
		}catch(IOException ie) {
		obj=ExcelSheetOperations.excelReadData(".\\src\\test\\resources\\TestData\\Data.xlsx","Login");
		}
		
		return obj;
	}
	
	@DataProvider(name="LoginCredentialsDataProvider1")
	public Object[][] loginData1(){
		
		Object[][] obj = new Object[2][4];
		
		obj [0][0] = "https://avyas-trials77.orangehrmlive.com/auth/seamlessLogin";
		obj [0][1] = "admin";
		obj [0][2] = "@Zk08fKYPd";
		obj [0][3] = true;
		
		obj [1][0] = "https://avyas-trials77.orangehrmlive.com/auth/seamlessLogin";
		obj [1][1] = "admin";
		obj [1][2] = "@Zk08fKYPdlk";
		obj [1][3] = false;
		
		
		return obj;
		}
	
	@Test(enabled=false)
	public void testCasec1() {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start("https://avyas-trials77.orangehrmlive.com/auth/seamlessLogin");
		
		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login("admin", "MPCs0K@ce111");
		
		System.out.println("VERIFY - home page is displayed");
		String expetedTitle = "Employee Management";
		String actualTitle = loginPage.getPageTitle();
		
		Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title was " + actualTitle);
		
		PredefinedActions.closeBrowser();
	}
}
