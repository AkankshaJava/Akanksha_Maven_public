package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfo_SalaryPage_AV extends PredefinedActions{

private static MyInfo_SalaryPage_AV myInfo_SalaryPage;
	
	@FindBy(xpath="//div[@translate='Cost to the Company']/following-sibling::div")
	private WebElement costToCmp;
	
	private MyInfo_SalaryPage_AV(){
		
	}
	
	public static MyInfo_SalaryPage_AV getObject() {
		if(myInfo_SalaryPage == null)
			myInfo_SalaryPage = new MyInfo_SalaryPage_AV();
		PageFactory.initElements(driver, myInfo_SalaryPage);
		return myInfo_SalaryPage;
	}
	
	public String getCostToCompany() {
		waitForVisibilityOfElement(costToCmp);
		return costToCmp.getText();
	}
	
}
