package testScripts;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import base.PredefinedActions;
import constant_Av.ConstantValues_AV;
import pages.LoginPage;
import utility.PropertyFileOpeartions;

public class TestBase {

	@BeforeSuite
	public void intiLog4j() {
		PropertyConfigurator.configure("src/main/resources/Config_AV/lolog4j.properties");
	}
	
	@BeforeMethod
	public void setUP() throws IOException {
		PropertyFileOpeartions fileOperations = new PropertyFileOpeartions(ConstantValues_AV.CONFIGFILEPATH);
	
		String url = fileOperations.getKeyValue("url");
		PredefinedActions.start(url);
		
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(fileOperations.getKeyValue("userName"),fileOperations.getKeyValue("password"));
	}

	@AfterMethod
	public void close(ITestResult result) {
		int status =result.getStatus();
			if(ITestResult.FAILURE==status) {
				PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
			}else if(ITestResult.SUCCESS==status){
				PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
			}
		
		PredefinedActions.closeBrowser();
	}
	
}

