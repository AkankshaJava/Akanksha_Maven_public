package testScripts.DependantsScripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

	@Parameters({ "browserName", "env", "os" })
	@Test
	public void openBrowser(String browser, String env, String os) {
		System.out.println("Browser " + browser + " : " + env + " : " + os);
		// Page Object MOdel
	}

	@Parameters({ "browserName", "env" })
	@Test
	public void openBrowser1(String browser, String env) {
		System.out.println("Browser " + browser + " : " + env);
		// Page Object MOdel
	}

	@Parameters({ "browserName" })
	@Test
	public void openBrowser2(String browser) {
		System.out.println("Browser " + browser + " : ");
		// Page Object MOdel
	}
}
