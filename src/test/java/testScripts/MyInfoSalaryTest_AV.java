package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MyInfoPage_AV;
import pages.MyInfo_SalaryPage_AV;
import pages.DashBoardPage_AV.Menu;
import pages.DashBoardPage_AV;
import pages.MyInfoPage_AV.MyInfoMenu;

public class MyInfoSalaryTest_AV extends TestBase{

	@Test
	public void verifyCtc() {
		DashBoardPage_AV dashboardPage = DashBoardPage_AV.getObject();
		dashboardPage.gotoMenu(Menu.MYINFO);
		MyInfoPage_AV myInfoPage = MyInfoPage_AV.getObject();
		myInfoPage.gotoMenu(MyInfoMenu.SALARY);
		MyInfo_SalaryPage_AV salaryPage = MyInfo_SalaryPage_AV.getObject();
		String ctc = salaryPage.getCostToCompany();
		Assert.assertTrue(ctc.startsWith("$"), "Actual ctc displayed as : " + ctc);
		ctc = ctc.replace("$", "").replace(",", "");
		System.out.println(ctc);
		double d = Double.parseDouble(ctc);
		Assert.assertTrue(d>0, "ctc value was : " + d);
	}
}
	
