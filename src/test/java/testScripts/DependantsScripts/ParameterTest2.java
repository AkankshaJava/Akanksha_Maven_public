package testScripts.DependantsScripts;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest2 {

	@Parameters({ "browserName", "env" })
	@Test
	public void openBrowser(@Optional("Chrome") String browser, @Optional("QA") String env) {
		System.out.println("Browser " + browser + " : " + env);
		// Page Object MOdel
	}

}
