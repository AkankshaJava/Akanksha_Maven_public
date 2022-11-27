package testScripts.DependantsScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependsOnMethods {

	@Test
	public void openBrowser() {
		System.out.println("Open browser");
	}

	@Test(dependsOnMethods = { "openBrowser" })
	public void signIn() {
		Assert.fail();
	}

	@Test(dependsOnMethods = { "signIn" })
	public void signOut() {
		System.out.println("sign Out");
	}
	

}
