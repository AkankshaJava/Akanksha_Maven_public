package testScripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.PredefinedActions;

public class ScreenShot extends PredefinedActions {

	void m1() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(srcFile, new File("./FailedTest/1.jpg"));
		}catch (IOException e){
			e.getStackTrace();
		}
	}
}
