package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;
import pages.MyInfoPage_AV.MyInfoMenu;

public class MyInfoPage_AV extends PredefinedActions {

	private static MyInfoPage_AV myInfoPage;

	private String menuPagemenu = "//a[contains(text(),'%s')]";

	private MyInfoPage_AV() {

	}

	public static MyInfoPage_AV getObject() {
		if (myInfoPage == null)
			myInfoPage = new MyInfoPage_AV();
		PageFactory.initElements(driver, myInfoPage);
		return myInfoPage;
	}

	public enum MyInfoMenu {
		PERSONALDETAILS("Personal Details"), JOB("Job"), SALARY("Salary"), CONTACTDETAILS("Contact Details");

		public String value;

		private MyInfoMenu(String value) {
			this.value = value;
		}
	}

	public void gotoMenu(MyInfoMenu myInfoMenu) {
		String menuText = myInfoMenu.value;
		String locatorValue = String.format(menuPagemenu, menuText);
		clickOnElement(getElement("xpath", locatorValue, true), false);
	}

}
