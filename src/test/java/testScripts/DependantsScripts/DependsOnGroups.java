package testScripts.DependantsScripts;

import org.testng.annotations.Test;

public class DependsOnGroups {

	@Test(groups = { "smoke" })
	public void openBrowser1() {
		System.out.println("Open Browser 1");
	}

	@Test(groups = { "smoke" })
	public void openBrowser2() {
		System.out.println("Open Browser 2");
	}

	@Test(groups = { "smoke" })
	public void openBrowser3() {
		System.out.println("Open Browser 3");
	}

	@Test(groups = { "smoke" }, dependsOnMethods = "openBrowser3")
	public void openBrowser4() {
		System.out.println("Open Browser 4");
	}

	@Test(dependsOnGroups = { "smoke" })
	public void signIn() {
		System.out.println("Sign In");
	}

	@Test(groups = { "regression" })
	public void signout() {
		System.out.println("Sign Out");
	}

}
