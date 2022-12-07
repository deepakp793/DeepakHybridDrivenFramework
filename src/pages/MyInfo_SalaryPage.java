package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfo_SalaryPage extends PredefinedActions{
	
	@FindBy(css=".summary-cards-container div[translate*='Cost to the']+div")
	private WebElement costToCompany;
	
	public MyInfo_SalaryPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getCostToComany() {
		return getElementText(costToCompany, false);
	}

}
