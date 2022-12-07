package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfoPage extends PredefinedActions {
	
	private String MenuPage ="//a[contains(text(),'%s')]";
	
	public MyInfoPage() {
		PageFactory.initElements(driver, this);
	}
	
	enum myInfoMenu{
		PERSONALDETAILS("Personal Details"),
		JOB("Job"),
		SALARY("Salary");
		
		public String value;
		private myInfoMenu(String value){
			this.value=value;
		}		
	}
	
	public void goToMenu(myInfoMenu myInfoMenu) {
		String menuText = myInfoMenu.value;
		String locatorValue=String.format(MenuPage, menuText);
		clickOnElement(getElement("xpath", locatorValue, true),true);
	}
	
}
